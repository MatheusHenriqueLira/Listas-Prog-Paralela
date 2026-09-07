public class Main {
    public static void validarIdade(int idade) {
        if (idade < 18) {
            throw new IllegalArgumentException("Acesso negado: idade minima e 18 anos");
        }
        System.out.println("Acesso liberado! Idade válida.");
    }

    public static void main(String[] args) {
        try {
            validarIdade(15);
        } catch (IllegalArgumentException erro) {
            System.out.println("Problema: " + erro.getMessage());
        }
    }
}
