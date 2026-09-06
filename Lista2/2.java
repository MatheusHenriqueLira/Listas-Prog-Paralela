class veiculo{
    public veiculo(){
        System.out.println("veiculo criado");
    }
}

class veiculo_quatro_rodas extends veiculo{
    public veiculo_quatro_rodas(){
        System.out.println("veiculo de quatro rodas criado");
    }
}

class CarroEletrico extends veiculo_quatro_rodas {
    private double capacidadeBateria;

    public CarroEletrico(double capacidade_bateria) {
        this.capacidadeBateria = capacidadeBateria;
        System.out.println("carro eletrico criado");
    }

    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("fabricando carro");
        
        CarroEletrico meuCarro = new CarroEletrico(75.5);
        
        System.out.println("Bateria do carro: " + meuCarro.getCapacidadeBateria() +" kWh");
    }
}
