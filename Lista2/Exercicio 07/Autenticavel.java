public interface Autenticavel{

    boolean autenticar(String senha);

    default void exibirBoasVindas(){
        System.out.println("Bem-vindo ao sistema seguro!");
    }

    static boolean validarTamanhoSenha(String senha){
        return senha != null && senha.length() >= 8;
    }
}