@FunctionalInterface
interface Operacao {
    int executar(int a, int b);
}

public class Main {

    public static int executar(int a, int b, Operacao op) {
        return op.executar(a, b);
    }

    public static void main(String[] args) {
        java.io.Console console = System.console();

        Operacao soma = (a, b) -> a + b;
        Operacao subtracao = (a, b) -> a - b;
        Operacao multiplicacao = (a, b) -> a * b;
        Operacao divisao = (a, b) -> a / b;
        Operacao resto = (a, b) -> a % b;

        int num1 = Integer.parseInt(console.readLine("Digite o primeiro número: "));
        int num2 = Integer.parseInt(console.readLine("Digite o segundo número: "));

        System.out.println("Escolha a operação: 1-Soma | 2-Subtração | 3-Multiplicação | 4-Divisão | 5-Resto");
        String escolha = console.readLine("Opção: ");

        Operacao opEscolhida = switch (escolha) {
            case "1" -> soma;
            case "2" -> subtracao;
            case "3" -> multiplicacao;
            case "4" -> divisao;
            case "5" -> resto;
            default -> null;
        };

        if (opEscolhida == null) {
            System.out.println("Opção inválida.");
            return;
        }

        if ((escolha.equals("4") || escolha.equals("5")) && num2 == 0) {
            System.out.println("Erro: Divisão por zero.");
            return;
        }

        int resultado = executar(num1, num2, opEscolhida);
        System.out.println("Resultado: " + resultado);
    }
}
