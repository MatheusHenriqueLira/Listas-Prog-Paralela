import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>();
        numeros.add(42);
        numeros.add(15);
        numeros.add(88);
        numeros.add(4);
        numeros.add(23);
        numeros.add(16);

        System.out.println("Lista original: " + numeros);

        Collections.sort(numeros);
        System.out.println("Após Collections.sort(): " + numeros);

        int elementoProcurado = 23;
        int indice = Collections.binarySearch(numeros, elementoProcurado);
        System.out.println("Índice do elemento " + elementoProcurado + " (busca binária): " + indice);

        Collections.reverse(numeros);
        System.out.println("Após Collections.reverse(): " + numeros);
    }
}