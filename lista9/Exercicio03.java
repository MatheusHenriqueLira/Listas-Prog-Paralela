import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Exercicio03 {

    private static final int LIMIAR_LINHAS = 5;

    public static class MaiorElementoMatrizTask extends RecursiveTask<Integer> {
        private final int[][] matriz;
        private final int linhaInicio;
        private final int linhaFim; // Limite superior exclusivo

        public MaiorElementoMatrizTask(int[][] matriz, int linhaInicio, int linhaFim) {
            this.matriz = matriz;
            this.linhaInicio = linhaInicio;
            this.linhaFim = linhaFim;
        }

        @Override
        protected Integer compute() {
            int qtdLinhas = linhaFim - linhaInicio;

            if (qtdLinhas <= LIMIAR_LINHAS) {
                int maior = Integer.MIN_VALUE;
                for (int i = linhaInicio; i < linhaFim; i++) {
                    for (int j = 0; j < matriz[i].length; j++) {
                        if (matriz[i][j] > maior) {
                            maior = matriz[i][j];
                        }
                    }
                }
                return maior;
            }

            int meio = linhaInicio + (qtdLinhas / 2);
            MaiorElementoMatrizTask tarefaA = new MaiorElementoMatrizTask(matriz, linhaInicio, meio);
            MaiorElementoMatrizTask tarefaB = new MaiorElementoMatrizTask(matriz, meio, linhaFim);

            tarefaA.fork();                  
            int resB = tarefaB.compute();       
            int resA = tarefaA.join();          

            return Math.max(resA, resB);
        }
    }

    private static int buscarMaiorSequencial(int[][] matriz) {
        int maior = Integer.MIN_VALUE;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] > maior) {
                    maior = matriz[i][j];
                }
            }
        }
        return maior;
    }

    public static void main(String[] args) {


        int totalLinhas = 3000;
        int totalColunas = 3000;
        System.out.printf("Dimensões da Matriz: %d linhas x %d colunas (%,d elementos)\n",
                totalLinhas, totalColunas, ((long) totalLinhas * totalColunas));
        System.out.println("Limiar de paralelismo (threshold): " + LIMIAR_LINHAS + " linhas");
        System.out.println("Processadores disponíveis: " + Runtime.getRuntime().availableProcessors());
        System.out.println("-------------------------------------------------------------------------------\n");

        System.out.println("Preenchendo matriz com valores aleatórios...");
        int[][] matriz = new int[totalLinhas][totalColunas];
        Random random = new Random(12345);
        for (int i = 0; i < totalLinhas; i++) {
            for (int j = 0; j < totalColunas; j++) {
                matriz[i][j] = random.nextInt(1_000_000);
            }
        }

        // Posiciona um valor máximo conhecido em uma célula arbitrária
        int linhaMaxEsperado = 1845;
        int colunaMaxEsperado = 1234;
        int valorMaxEsperado = 98_765_432;
        matriz[linhaMaxEsperado][colunaMaxEsperado] = valorMaxEsperado;
        System.out.printf("Maior valor inserido para teste: %d na posição [%d, %d]\n\n",
                valorMaxEsperado, linhaMaxEsperado, colunaMaxEsperado);

        // --- 1. Execução Paralela com ForkJoinPool ---
        ForkJoinPool pool = new ForkJoinPool();
        MaiorElementoMatrizTask tarefaPrincipal = new MaiorElementoMatrizTask(matriz, 0, totalLinhas);

        long t0Paralelo = System.currentTimeMillis();
        int maiorParalelo = pool.invoke(tarefaPrincipal);
        long t1Paralelo = System.currentTimeMillis();
        long tempoParalelo = t1Paralelo - t0Paralelo;

        // --- 2. Execução Sequencial ---
        long t0Seq = System.currentTimeMillis();
        int maiorSequencial = buscarMaiorSequencial(matriz);
        long t1Seq = System.currentTimeMillis();
        long tempoSequencial = t1Seq - t0Seq;

        // --- 3. Resultados e Validação ---
        System.out.printf("Maior valor encontrado (Fork/Join): %d (Tempo: %d ms)\n", maiorParalelo, tempoParalelo);
        System.out.printf("Maior valor encontrado (Sequencial): %d (Tempo: %d ms)\n", maiorSequencial, tempoSequencial);
        
        boolean correto = (maiorParalelo == valorMaxEsperado && maiorSequencial == valorMaxEsperado);
        System.out.println("Validação de Corretude: " + (correto ? "SUCESSO (Resultado Correto!)" : "FALHA"));
        
        if (tempoParalelo > 0) {
            double speedup = (double) tempoSequencial / tempoParalelo;
            System.out.printf("Speedup obtido: %.2fx\n", speedup);
        }
        System.out.println("===============================================================================");

        pool.close();
    }
}
