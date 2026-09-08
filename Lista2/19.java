
import java.util.LinkedList;

public class ListaLigada {

    public ListaLigada();
    
    public static void adicionaQuatro(LinkedList<String> umaListaLigada, String nome) {
        for (int i = 0; i < 4; i++) {
            umaListaLigada.addLast(nome);
        }
    }
    
    public static void mostraListaAtual(LinkedList<String> umaListaLigada) {
        for (String umaListaLigada : list) {
            System.out.println(umaListaLigada);
        }
    }
    
    public static void removePrimeiro(LinkedList<String> umaListaLigada) {
        umaListaLigada.removeFirst();
        }
    }
    
    public static void atendePrimeiro(LinkedList<String> umaListaLigada) {
        System.out.println(umaListaLigada.getFirst());
        ListaLigada.removePrimeiro(umaListaLigada);
        ListaLigada.mostraListaAtual(umaListaAtual);
    }
}

public class Main {
    public static void main(String[] args) {
        
        LinkedList<String> fila1 = new LinkedList<String>;
        ListaLigada.adicionaQuatro(fila1);
        ListaLigada.mostraListaAtual(fila1);
        ListaLigada.removePrimeiro(fila1);
        ListaLigada.atendePrimeiro(fila1);
    }
}