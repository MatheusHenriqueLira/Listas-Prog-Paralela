class Animal {
    String nome;

    public Animal(String nome) {
        this.nome = nome;
    }

    public void emitirSom() {
        System.out.println("O animal emite um som.");
    }
}

class Cachorro extends Animal {

    public Cachorro(String nome) {
        super(nome);
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: Au Au!");
    }
}

class Gato extends Animal {

    public Gato(String nome) {
        super(nome);
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: Miau!");
    }
}

public class Main {
    public static void main(String[] args) {
        Cachorro meuCachorro = new Cachorro("Rex");
        Gato meuGato = new Gato("Felix");

        meuCachorro.emitirSom();
        meuGato.emitirSom();
    }
}