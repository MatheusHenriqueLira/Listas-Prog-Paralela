public class Main{

    public static void main(String[] args){
        String senhaTeste = "12345678";
        boolean tamanhoValido = Autenticavel.validarTamanhoSenha(senhaTeste);
        System.out.println("A senha tem 8 ou mais caracteres? " + tamanhoValido);   

        Usuario usuario = new Usuario("12345678");

        usuario.exibirBoasVindas();

        boolean autenticado = usuario.autenticar("12345678");
        System.out.println("Usuário autenticado: " + autenticado);

    }
}