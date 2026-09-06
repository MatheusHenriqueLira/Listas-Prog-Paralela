import java.util.ArrayList;
import java.util.List;

class Funcionario {
    private String nome;
    private String cargo;

    public Funcionario(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return "Nome: " nome;
        return "Cargo:" cargo;
    }
}

class Empresa {
    private String nome;
    private List<Funcionario> funcionarios; 

    public Empresa(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }

    public void listarFuncionarios() {
        System.out.println("Funcionários da Empresa " this.nome );
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado");
        } else {
            for (Funcionario f : funcionarios) {
                System.out.println(f.toString());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        
        Funcionario func1 = new Funcionario("Jose", "Desenvolvedora");
        Funcionario func2 = new Funcionario("Manu", "Analista de Dados");

        Empresa empresa = new Empresa("Papuguinho");

        empresa.adicionarFuncionario(func1);
        empresa.adicionarFuncionario(func2);

        empresa.listarFuncionarios();

        System.out.println("Fechando.");
        empresa = null; 

        System.out.println("A empresa deixou de existir, mas os funcionários continuam na memória:");
        System.out.println("Funcionário 1: " + func1.getNome());
        System.out.println("Funcionário 2: " + func2.getNome());
    }
}