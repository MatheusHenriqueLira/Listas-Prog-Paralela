import java.util.Scanner;

public class Exercicio14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[5];

        System.out.println("Digite 5 números inteiros:");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        int soma = 0;
        int maior = numeros[0];

        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i];

            if (numeros[i] > maior) {
                maior = numeros[i];
            }
        }

        double media = (double) soma / numeros.length;

        System.out.println("Soma dos elementos: " + soma);
        System.out.println("Média dos elementos: " + media);
        System.out.println("Maior valor presente no array: " + maior);

        scanner.close();
    }
}
