import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int tamanhoVetor = 50_000_000;
        int[] vetor = new int[tamanhoVetor];

        for (int i = 0; i < tamanhoVetor; i++) {
            vetor[i] = 1;
        }

        System.out.print("Quantidade de threads: ");
        int numThreads = scanner.nextInt();

        SomadorThreads[] threads = new SomadorThreads[numThreads];
        int bloco = tamanhoVetor / numThreads;

        long inicioTempo = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            int inicio = i * bloco;
            int fim = (i == numThreads - 1) ? tamanhoVetor : inicio + bloco;

            threads[i] = new SomadorThreads(vetor, inicio, fim);
            threads[i].start();
        }

        long somaTotal = 0;
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            somaTotal += threads[i].getSomaParcial();
        }

        long fimTempo = System.currentTimeMillis();
        long tempoTotalMs = fimTempo - inicioTempo;

        System.out.println("Soma total: " + somaTotal);
        System.out.println("Tempo (ms): " + tempoTotalMs);

        scanner.close();
    }
}