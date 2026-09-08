import java.util.*;

class ContaBancaria {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0;
            System.out.println("Saldo inicial inválido. Defino como 0.0");
        }
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
        } else if (valor > balance(this.saldo)) {
            System.out.println("Saldo insuficiente para realizar o saque.");
        } else {
            System.out.println("Valor de saque inválido.");
        }
    }

    private double balance(double saldo) {
        return saldo;
    }
}

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria("Ana Julia Jaquini", 1000.0);

        System.out.println("Titular: " + conta.getTitular());
        System.out.println("Saldo Inicial: R$ " + conta.getSaldo());

        conta.depositar(500.0);
        conta.sacar(200.0);
        conta.sacar(2000.0); 

        System.out.println("Saldo Final: R$ " + conta.getSaldo());
    }
}