
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String aviso) {
        super(aviso);
    }
}

public class ContaBancaria {
    double saldo = 0;
    
    public ContaBancaria(double saldo){
        this.saldo = saldo;
    }
    
    public adicionaValor(double umValor) {
        this.saldo = this.saldo + umValor;
        System.out.println("Saldo atual: " + this.saldo);
    }
    
    public sacaValor(double outroValor) {
        if (outroValor > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo atual: " + this.saldo);
        }
        this.saldo = this.saldo - outroValor;
        System.out.println("Saque realizado: Novo saldo: " + this.saldo)
    }
}

public class Main {
    public static void main(String[] args) {
        
        ContaBancaria conta1 = new ContaBancaria(100.0);
        
        try {
            conta.sacaValor(150.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}