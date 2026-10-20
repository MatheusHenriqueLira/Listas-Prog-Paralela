import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


public class Exercicio03 {

    private static final int TAMANHO = 50_000_000;
    private static final int NUM_THREADS = 4;

    private static long trabalhoPesado(long inicio, long fim) {
        long resultado = 0;
        for (long i = inicio; i < fim; i++) {
            double x = i;
            x = Math.sin(x);
            x = Math.cos(x);
            x = Math.sqrt(Math.abs(x));
            x = Math.log(x + 1);
            resultado += (long) (x * 1000);
        }
        return resultado;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("Tamanho total de elementos (TAMANHO): " + TAMANHO);
        System.out.println("Número de threads no pool (NUM_THREADS): " + NUM_THREADS);
        System.out.println("Processadores disponíveis na máquina: " + Runtime.getRuntime().availableProcessors());
        System.out.println("\n");

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        System.out.println(">>> 1. Executando Tarefa Original: Soma de Vetor (Memory-bound)...");
        System.out.println("Alocando e inicializando vetor com " + TAMANHO + " elementos...");
        int[] vetor = new int[TAMANHO];
        Arrays.fill(vetor, 1);

        long tInicioMem = System.currentTimeMillis();

        List<Callable<Long>> tarefasMem = new ArrayList<>();
        int chunkMem = TAMANHO / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            final int ini = i * chunkMem;
            final int fim = (i == NUM_THREADS - 1) ? TAMANHO : (i + 1) * chunkMem;
            tarefasMem.add(() -> {
                long somaParcial = 0;
                for (int j = ini; j < fim; j++) {
                    somaParcial += vetor[j];
                }
                return somaParcial;
            });
        }

        List<Future<Long>> futuresMem = executor.invokeAll(tarefasMem);
        long somaTotal = 0;
        for (Future<Long> f : futuresMem) {
            somaTotal += f.get();
        }

        long tFimMem = System.currentTimeMillis();
        long tempoTotalMem = tFimMem - tInicioMem;

        System.out.println("Resultado da soma: " + somaTotal);
        System.out.printf("Tempo de processamento (Memory-bound): %d ms (%.3f s)\n\n", tempoTotalMem, tempoTotalMem / 1000.0);


        System.out.println(">>> 2. Executando Nova Tarefa: trabalhoPesado (CPU-bound)...");
        System.out.println("Processando cálculos matemáticos intensivos para " + TAMANHO + " iterações...");

        long tInicioCpu = System.currentTimeMillis();

        List<Callable<Long>> tarefasCpu = new ArrayList<>();
        long chunkCpu = (long) TAMANHO / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            final long ini = i * chunkCpu;
            final long fim = (i == NUM_THREADS - 1) ? TAMANHO : (i + 1) * chunkCpu;
            tarefasCpu.add(() -> trabalhoPesado(ini, fim));
        }

        List<Future<Long>> futuresCpu = executor.invokeAll(tarefasCpu);
        long resultadoCpu = 0;
        for (Future<Long> f : futuresCpu) {
            resultadoCpu += f.get();
        }

        long tFimCpu = System.currentTimeMillis();
        long tempoTotalCpu = tFimCpu - tInicioCpu;

        System.out.println("Resultado do trabalho pesado: " + resultadoCpu);
        System.out.printf("Tempo de processamento (CPU-bound): %d ms (%.3f s)\n\n", tempoTotalCpu, tempoTotalCpu / 1000.0);

        System.out.printf("Tempo Tarefa Original (Memory-bound): %6d ms (%.3f s)\n", tempoTotalMem, tempoTotalMem / 1000.0);
        System.out.printf("Tempo Nova Tarefa (CPU-bound)       : %6d ms (%.3f s)\n", tempoTotalCpu, tempoTotalCpu / 1000.0);
        System.out.printf("Diferença percentual de tempo       : %.2fx mais demorada na CPU\n", (double) tempoTotalCpu / Math.max(1, tempoTotalMem));
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("1. Tarefa Memory-bound (Soma de Vetor):");
        System.out.println("   - Operações aritméticas muito simples por elemento (apenas adições).");
        System.out.println("   - A CPU gasta a maior parte do tempo aguardando a transferência de dados");
        System.out.println("     da memória RAM para os registradores (gargalo de largura de banda de memória).");
        System.out.println("2. Tarefa CPU-bound (trabalhoPesado):");
        System.out.println("   - Realiza múltiplos cálculos de ponto flutuante (seno, cosseno, raiz e log).");
        System.out.println("   - Quase não acessa a memória principal, mantendo as unidades funcionais da CPU");
        System.out.println("     (ALU / FPU) sob alta carga durante todo o processamento, permitindo avaliar");
        System.out.println("     com muito mais precisão o paralelismo real e ganho de múltiplos núcleos.");
        System.out.println("===============================================================================");

        executor.shutdown();
    }
}
