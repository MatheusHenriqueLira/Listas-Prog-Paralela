interface VeiculoTerrestre {
    void dirigir();
}

interface VeiculoAquatico {
    void navegar();
}

class CarroAnfibio implements VeiculoTerrestre, VeiculoAquatico {

    @Override
    public void dirigir() {
        System.out.println("O carro anfíbio está rodando em terra firme.");
    }

    @Override
    public void navegar() {
        System.out.println("O carro anfíbio está navegando na água.");
    }
}

public class Main {
    public static void main(String[] args) {
        CarroAnfibio anfibio = new CarroAnfibio();

        anfibio.dirigir();
        anfibio.navegar();
    }
}