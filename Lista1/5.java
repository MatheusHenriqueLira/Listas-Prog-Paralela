public class Exercicio05 {
    public static void main(String[] args) {
        int[] numeros = {10, 20, 30, 40, 50};

        System.out.println("Todos os elementos:");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(numeros[i] + " ");
        }
        System.out.println();

        System.out.println("Primeiro elemento: " + numeros[0]);

        System.out.println("Último elemento: " + numeros[numeros.length - 1]);

        int soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i];
        }
        System.out.println("Soma dos elementos: " + soma);

        double media = (double) soma / numeros.length;
        System.out.println("Média dos elementos: " + media);
    }
}