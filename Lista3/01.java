import java.util.Arrays;
import java.util.Random;

public class Exercicio01 {
    public static void main(String[] args) {
        final int TAMANHO = 50000000;
        long[] vetor = new long[TAMANHO];

        System.out.println("Vetor preenchido com 1");
        
        Arrays.fill(vetor, 1L);

        long inicio = System.currentTimeMillis();
        long soma = 0;
        for (int i = 0; i < TAMANHO; i++) {
            soma += vetor[i];
        }
        long fim = System.currentTimeMillis();
        long tempoTotal = fim - inicio;

        System.out.println("Soma dos elementos: " + soma);
        System.out.println("Tempo de execução: " + tempoTotal + " ms (" + (tempoTotal / 1000.0) + " s)");
    }
}
