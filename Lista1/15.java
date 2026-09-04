import java.util.ArrayList;
import java.util.List;

public class Comodo {
  String nome;

  void oComodo {
    System.out.println('este é o cômodo: ' + this.nome);
  }
}

public class Casa {
  List<comodo> comodos;

  public Casa() {
    this.comodos = new ArrayList<>();
  }

  void adicionarComodo(String nome){
    comodo NovoComodo = new comodo(nome);
    this.comodos.add(NovoComodo);
  }

  void listarComodos() {
    for (int i = 0; i < this.comodos.size(); i++) {
      System.out.println(this.comodos.get(i).nome());
    }
  }
}

public class Main {
  public static void Main (String args[]) {
    Casa casinha = new Casa();
    casinha.adicionarComodo('quarto');
    casinha.adicionarComodo('sala');
    casinha.adicionarComodo('ateliê');
    casinha.adicionarComodo('banheiro');

    casinha.listarComodos();

  }
}

// AGREGAÇÃO: As classes são independentes, pouco acopladas, de modo que continuam existindo mesmo se não houver um relacionamento entre as mesmas.
// COMPOSIÇÃO: Fortemente acopladas, uma classe terá a inicialização de outra dentro do seu construtor, de modo que ela só irá existir se a outra classe também existir.
