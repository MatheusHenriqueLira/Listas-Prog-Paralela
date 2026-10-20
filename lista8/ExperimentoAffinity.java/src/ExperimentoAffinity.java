
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import net.openhft.affinity.Affinity;

public class ExperimentoAffinity {

    private static final int TAMANHO = 1_000_000_000;
    

    public static void main(String[] args) throws Exception {

        System.out.println("==========================================");
        System.out.println(" EXPERIMENTO COM CPU AFFINITY - EXERCÍCIO 2");
        System.out.println("==========================================");

        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("CPUs lógicos disponíveis: " + cpus);
        System.out.println("O programa testará de 1 até " + cpus + " núcleos.\n");

        System.out.println("Criando e preenchendo o vetor (aguarde)...");
        int[] vetor = new int[TAMANHO];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 1;
        }

        List<String> dadosGrafico = new ArrayList<>();

    
        for (int numThreads = 1; numThreads <= cpus; numThreads++) {
            System.out.print("Testando com " + numThreads + " núcleo(s)... ");

            ThreadFactory factory = new AffinityThreadFactory();
            ExecutorService executor = Executors.newFixedThreadPool(numThreads, factory);
            List<Future<Long>> futures = new ArrayList<>();

            int bloco = vetor.length / numThreads;
            long inicio = System.nanoTime();

            for (int i = 0; i < numThreads; i++) {
                int inicioBloco = i * bloco;
                int fimBloco;

                if (i == numThreads - 1) {
                    fimBloco = vetor.length;
                } else {
                    fimBloco = inicioBloco + bloco;
                }

                final int inicioFinal = inicioBloco;
                final int fimFinal = fimBloco;

                Future<Long> future = executor.submit(() -> {

                    return somar(vetor, inicioFinal, fimFinal);
                });

                futures.add(future);
            }

            long somaTotal = 0;
            for (Future<Long> future : futures) {
                somaTotal += future.get();
            }

            executor.shutdown();
            long fim = System.nanoTime();
            double tempo = (fim - inicio) / 1_000_000_000.0;

            System.out.printf("Concluído em %.3f segundos. (Resultado: %d)%n", tempo, somaTotal);
            
            dadosGrafico.add(numThreads + "\t" + String.format("%.3f", tempo).replace(".", ","));
        }


        System.out.println("\n==========================================");
        System.out.println("DADOS PARA O GRÁFICO (Copie e cole no Excel)");
        System.out.println("Núcleos\tTempo(s)");
        for (String linha : dadosGrafico) {
            System.out.println(linha);
        }
    }

    private static long somar(int[] vetor, int inicio, int fim) {
        long soma = 0;
        for (int i = inicio; i < fim; i++) {
            // retardo para ver o uso de cpu
            for (int z = 0; z < 10000; z++) {}
            soma += vetor[i];
        }
        return soma;
    }

    static class AffinityThreadFactory implements ThreadFactory {
        private final AtomicInteger contador = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable runnable) {
            int cpu = contador.getAndIncrement();
            Thread thread = new Thread(() -> {
                Affinity.setAffinity(cpu);
                runnable.run();
            });
            thread.setName("Worker-" + cpu);
            return thread;
        }
    }
}