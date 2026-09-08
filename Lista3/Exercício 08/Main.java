public class Main {
    public static void main(String[] args) throws InterruptedException {
        int linhas = 4000;
        int colunas = 4000;
        int [][] matriz = new int[linhas][colunas];

        for (int i = 0; i< linhas; i++){
            for (int j = 0; j< colunas; j++){
                matriz[i][j] = 1;
            }
        }

        int totalThreads = 4;
        MultiplicadorLinhasThread[] threads = new MultiplicadorLinhasThread[totalThreads];

        int linhasThread = linhas / totalThreads;

        long tempoInicio = System.currentTimeMillis();

        for ( int t = 0; t < totalThreads; t++){
            int inicio = t * linhasThread;
            int fim = inicio + linhasThread;

            threads [t] = new MultiplicadorLinhasThread(matriz, inicio, fim);
            threads[t].join();
        }

        long tempoFim = System.currentTimeMillis();

        System.out.println("Processamento concorrente concluído!");
        System.out.println("Tempo de execução: " + (tempoFim - tempoInicio) + " ms");

        System.out.println("Amostra matriz[0][0] (Thread 1): " + matriz[0][0]);
        System.out.println("Amostra matriz[1500][1500] (Thread 2): " + matriz[1500][1500]);
        System.out.println("Amostra matriz[2500][2500] (Thread 3): " + matriz[2500][2500]);
        System.out.println("Amostra matriz[3999][3999] (Thread 4): " + matriz[3999][3999]);
    }
}
