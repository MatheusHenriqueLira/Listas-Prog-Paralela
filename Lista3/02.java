import java.util.Random;

public class Exercicio02 {
    public static void main(String[] args) {
        final int TAMANHO = 50000000;
        int[] vetor = new int[TAMANHO];
        Random random = new Random();

        for (int i = 0; i < TAMANHO; i++) {
            vetor[i] = random.nextInt();
        }

        long inicio = System.currentTimeMillis();

        int maior = vetor[0];
        int menor = vetor[0];
        int posMaior = 0;
        int posMenor = 0;

        for (int i = 1; i < TAMANHO; i++) {
            if (vetor[i] > maior) {
                maior = vetor[i];
                posMaior = i;
            }
            if (vetor[i] < menor) {
                menor = vetor[i];
                posMenor = i;
            }
        }

        long fim = System.currentTimeMillis();
        long tempoTotal = fim - inicio;

        System.out.println("Maior valor: " + maior);
        System.out.println("Posicao do maior valor: " + posMaior);
        System.out.println("Menor valor: " + menor);
        System.out.println("Posicao do menor valor: " + posMenor);
        System.out.println("Tempo de execucao: " + tempoTotal + " ms");
    }
}
