public class ContadorSimples {
    public static void main(String[] args) throws InterruptedException {
        int totalRegistros = 1_000_000;
        int particao = 250_000;
        
        int[] vetor = new int[totalRegistros];
        for (int i = 0; i < totalRegistros; i++) {
            vetor[i] = 1;
        }

        long[] somas = new long[4];
        Thread[] threads = new Thread[4];

        for (int i = 0; i < 4; i++) {
            int indice = i;
            threads[i] = new Thread(() -> {
                int inicio = indice * particao;
                int fim = inicio + particao;
                for (int j = inicio; j < fim; j++) {
                    somas[indice] += vetor[j];
                }
            });
            threads[i].start();
        }

        long somaTotal = 0;
        for (int i = 0; i < 4; i++) {
            threads[i].join();
            somaTotal += somas[i];
        }

        System.out.println("Soma total: " + somaTotal);
    }
}
