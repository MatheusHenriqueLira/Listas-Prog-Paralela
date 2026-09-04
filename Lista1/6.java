public class Aluno {
  String nome;
  int idade;
  double nota;

  void exibirDados(){
    System.out.println("Oi, eu sou " + this.nome);
    System.out.println("Tenho " + this.idade + "anos");
    System.out.println("Minha nota é: " + this.nota);
  }
}

public class Main {
  public static void main(String[] args) {
    Aluno a1 = new Aluno();
    a1.nome = "isabella";
    a1.idade = 20;
    a1.nota = '10.00';
    
    Aluno a2 = new Aluno();
    a2.nome = "fabricio";
    a2.idade = 20;
    a2.nota = 9.90;

    a1.exibirDados();
    a2.exibirDados();
  }
}
