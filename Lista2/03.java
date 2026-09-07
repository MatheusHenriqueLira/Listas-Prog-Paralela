public class Funcionario {
  double salarioBase;

  double calcularSalario() {
    return this.salarioBase;
  }
}

public class Gerente extends Funcionario {
  double bonificacao;

  @Override
  double calcularSalario(){
    return this.salarioBase + this.bonificacao;
  }
}

public class Desenvolvedor extends Funcionario {
  double adicionalProjetos;

  @Override
  double calcularSalario(){
    return this.salarioBase + adicionalProjetos;
  }
}

public class Main {
  public static void main(String args[]){
    Gerente Jonas = new Gerente();
    Jonas.salarioBase = 5600.00;
    Jonas.bonificacao = 2000.00;
    Desenvolvedor Marina = new Desenvolvedor();
    Marina.salarioBase = 4500.00;
    Marina.adicionalProjetos = 3000.00;

    System.out.println(Jonas.calcularSalario());
    System.out.println(Marina.calcularSalario());
  } 
}
