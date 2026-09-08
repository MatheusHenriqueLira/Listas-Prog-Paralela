class Aluno {
  String nome;
  int idade;
  double nota;

  Aluno(String nome, int idade, double nota) {
    this.nome = nome;
    this.idade = idade;
    this.nota = nota;
  }

  void exibirDados(){
    System.out.println("Oi, eu sou " + this.nome);
    System.out.println("Tenho " + this.idade + " anos");
    System.out.println("Minha nota é: " + this.nota);
  }
}

public class Main {
  public static void main(String[] args) {
    Aluno a1 = new Aluno("isabella", 20, 10.00);
    Aluno a2 = new Aluno("fabricio", 20, 9.90);
    Aluno a3 = new Aluno("carlos", 22, 7.50);

    a1.exibirDados();
    a2.exibirDados();
    a3.exibirDados();
  }
}