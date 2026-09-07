interface VeiculoEletrico {
    void carregarBateria();
}

class Veiculo {
    private String marca;
    private String modelo;
    private int ano;

    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public void exibirDados() {
        System.out.println("Marca: " + marca + " | Modelo: " + modelo + " | Ano: " + ano);
    }
}

class Carro extends Veiculo implements VeiculoEletrico {
    private int quantidadePortas;

    public Carro(String marca, String modelo, int ano, int quantidadePortas) {
        super(marca, modelo, ano);
        this.quantidadePortas = quantidadePortas;
    }

    public int getQuantidadePortas() { return quantidadePortas; }
    public void setQuantidadePortas(int quantidadePortas) { this.quantidadePortas = quantidadePortas; }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Quantidade de Portas: " + quantidadePortas);
    }

    @Override
    public void carregarBateria() {
        System.out.println(">> Carregando a bateria do carro " + getModelo() + "... 100% concluído!");
    }
}

class Moto extends Veiculo {
    private int cilindradas;

    public Moto(String marca, String modelo, int ano, int cilindradas) {
        super(marca, modelo, ano);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas() { return cilindradas; }
    public void setCilindradas(int cilindradas) { this.cilindradas = cilindradas; }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Cilindradas: " + cilindradas + "cc");
    }
}

public class Exercicio20 {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE VEÍCULOS ===");

        Veiculo v1 = new Carro("BYD", "Seal", 2024, 4);
        Veiculo v2 = new Moto("Yamaha", "MT-07", 2023, 689);

        System.out.println("\n--- Dados do Veículo 1 (Carro) ---");
        v1.exibirDados();

        System.out.println("\n--- Dados do Veículo 2 (Moto) ---");
        v2.exibirDados();

        System.out.println("\n--- Teste de Recarga Elétrica ---");
        if (v1 instanceof VeiculoEletrico) {
            ((VeiculoEletrico) v1).carregarBateria();
        }
    }
}
