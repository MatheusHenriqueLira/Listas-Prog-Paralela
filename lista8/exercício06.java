import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static void executarCargaDeTrabalho(int numThreads, int totalTarefas) {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < totalTarefas; i++) {
            executor.submit(() -> {
                double val = 0;
                for (int j = 0; j < 500_000; j++) {
                    val += Math.sin(j) * Math.cos(j);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        int[] numThreadsArray = {1, 2, 4, 8};
        int totalTarefas = 200;

        double tempoT1 = 0;

        System.out.println("=====================================================================");
        System.out.printf("%-10s | %-12s | %-12s | %-12s%n", "Threads", "Tempo (ms)", "Speedup", "Eficiência");
        System.out.println("=====================================================================");

        for (int p : numThreadsArray) {
            long inicio = System.nanoTime();
            executarCargaDeTrabalho(p, totalTarefas);
            long fim = System.nanoTime();

            double tempoMs = (fim - inicio) / 1_000_000.0;

            // Armazena T1 (tempo com 1 thread) como referência
            if (p == 1) {
                tempoT1 = tempoMs;
            }

            double speedup = tempoT1 / tempoMs;
            double eficiencia = speedup / p;

            System.out.printf("%-10d | %-12.2f | %-12.2f | %-12.2f (%.1f%%)%n", 
                    p, tempoMs, speedup, eficiencia, eficiencia * 100);
        }

        System.out.println("=====================================================================");
    }
}

