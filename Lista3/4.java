public class MatrizTeste {

    public static void main(String[] args) {
        final int TAMANHO = 4000;
        int[][] matriz = new int[TAMANHO][TAMANHO];

        long inicio = System.nanoTime();

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                matriz[i][j] = 10;
            }
        }

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                matriz[i][j] = matriz[i][j] * 2;
            }
        }

        long fim = System.nanoTime();
        long tempoNano = fim - inicio;
        double tempoMs = tempoNano / 1_000_000.0;

        System.out.println("Verificação de alguns elementos da matriz:");
        System.out.println("matriz[0][0]       = " + matriz[0][0]);
        System.out.println("matriz[0][3999]     = " + matriz[0][3999]);
        System.out.println("matriz[1999][1999]  = " + matriz[1999][1999]);
        System.out.println("matriz[3999][0]     = " + matriz[3999][0]);
        System.out.println("matriz[3999][3999]  = " + matriz[3999][3999]);

        System.out.println();
        System.out.println("Tempo de execução: " + tempoNano + " ns (" 
                + String.format("%.3f", tempoMs) + " ms)");
    }
}