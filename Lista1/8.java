public class Calculadora {

    int somar(int a, int b) {
        return a + b;
    }

    int subtrair(int a, int b) {
        return a - b;
    }

    int multiplicar(int a, int b) {
        return a * b;
    }

    double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Não é possível dividir por zero.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        int a = 10;
        int b = 5;

        System.out.println("Soma: " + calc.somar(a, b));
        System.out.println("Subtração: " + calc.subtrair(a, b));
        System.out.println("Multiplicação: " + calc.multiplicar(a, b));
        System.out.println("Divisão: " + calc.dividir(a, b));
    }
}