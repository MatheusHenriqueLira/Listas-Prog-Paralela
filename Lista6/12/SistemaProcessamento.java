/*

- uma thread percorre lista e soma pares
- outra thread percorre lista e soma impares
- terceira thread percorre lista e calcula maior/menor/media

*/
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


public class SistemaProcessamento {
    public static void main(String[] args) {

        List<Integer> listaNum = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= 100; i++) {
            int intAleatorio = rand.nextInt(1000);
            listaNum.add(intAleatorio);
        }

        Thread t_pares = new Thread(() -> {
            int soma = 0;
            for (int i = 0; i < listaNum.size(); i++) {
                if (listaNum.get(i)%2 == 0) {
                    soma += listaNum.get(i);
                };
            };
            System.out.println("Soma dos pares: " + soma + ".");
        });

        Thread t_impares = new Thread(() -> {
            int soma2 = 0;
            for (int i = 0; i < listaNum.size(); i++) {
                if (listaNum.get(i)%2 != 0) {
                    soma2 += listaNum.get(i);
                };
            };
            System.out.println("Soma dos impares: " + soma2 + ".");
        });

        Thread t_stats = new Thread(() -> {

            int atual = listaNum.get(0);
            int menor = listaNum.get(i+1);
            for (int i = 0; i < listaNum.size(); i++) {
                
                if (atual < menor) {
                    menor = atual;
                };
                atual = listaNum.get(i);
            };
            System.out.println("Menor numero da lista: " + menor + ".");

            int maior = listaNum.get(0);
            int atual2 = listaNum.get(0);
            for (int i = 0; i < listaNum.size(); i++) {
                maior = listaNum.get(i+1);
                if (atual2 > maior) {
                    maior = atual2;
                };
                atual2 = listaNum.get(i);
            };
            System.out.println("Maior numero da lista: " + maior + ".");

            //int somaTudo = t_pares::soma + t_impares::soma2;
            //System.out.println("Soma dos itens da lista: " + somaTudo + ".");


        });

        t_pares.start();
        t_impares.start();
        t_stats.start();
    }
}

