import java.util.ArrayList;

public class ListaDeCompras {

    public static void main(String[] args) {
        ArrayList<String> listaDeCompras = new ArrayList<>();

        listaDeCompras.add("Arroz");
        listaDeCompras.add("Leite");
        listaDeCompras.add("Pão");
        listaDeCompras.add("Ovos");

        System.out.println("Lista original: " + listaDeCompras);

        listaDeCompras.remove(1);
        System.out.println("Lista após remover o segundo item: " + listaDeCompras);

        boolean contemLeite = listaDeCompras.contains("Leite");
        System.out.println("A lista contém 'Leite'? " + contemLeite);

        System.out.println("\nItens da lista de compras:");
        for (String item : listaDeCompras) {
            System.out.println("- " + item);
        }
    }
}