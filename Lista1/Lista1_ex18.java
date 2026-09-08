interface Voador {
    void voar();
}

interface Nadador {
    void nadar();
}

class Pato implements Voador, Nadador {

    @Override
    public void voar() {
        System.out.println("O pato está voando.");
    }

    @Override
    public void nadar() {
        System.out.println("O pato está nadando.");
    }
}

public class Main {
    public static void main(String[] args) {
        Pato pato = new Pato();

        pato.voar();
        pato.nadar();
    }
}