public class Usuario implements Autenticavel {
    private String senhaCorreta;

    public Usuario (String senhaCorreta){
        this.senhaCorreta = senhaCorreta;
    }

    @Override
    public boolean autenticar(String senha){
        return this.senhaCorreta.equals(senha);
    }
}
