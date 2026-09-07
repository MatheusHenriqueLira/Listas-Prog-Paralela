public class Exercicio08 {
    public static void main(String[] args) {
        int a = 20;
        int b = 0;

        try {
            System.out.println("Tentando realizar a divisão...");
            int resultado = a / b;
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Não é permitida a divisão por zero!");
        } finally {
            System.out.println("Operação finalizada");
        }
    }
}
