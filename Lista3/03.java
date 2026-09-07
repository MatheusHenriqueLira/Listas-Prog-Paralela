public class Contagem{
    public static void main(String[] args) {
        int tamanho = 50_000_000;

        long pares = 0;
        long impares = 0;
        long multiplosDeCinco = 0;
        long tempoInicio = System.currentTimeMillis();

        for (int i = 0; i < tamanho; i++) {
            int valor = i * 3; 

            if (valor % 2 == 0) {
                pares++;
            } else {
                impares++;
            }

            if (valor % 5 == 0) {
                multiplosDeCinco++;
            }
        }

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;

        System.out.println("Pares: " + pares);
        System.out.println("Ímpares: " + impares);
        System.out.println("Múltiplos de 5: " + multiplosDeCinco);
        System.out.println("Tempo de execução: " + tempoTotal + " ms");
    }
}
