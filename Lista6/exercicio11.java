import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Questao11 {

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(
                3, 12, 45, 58, 7, 90, 21, 64, 33, 100,
                18, 51, 76, 5, 82, 27, 49, 60, 15, 71, 8, 99);

        System.out.println("1) Todos os números:");
        numeros.forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        System.out.println("2) Números pares:");
        numeros.forEach(n -> {
            if (n % 2 == 0) {
                System.out.print(n + " ");
            }
        });
        System.out.println("\n");

        System.out.println("3) Números ímpares:");
        List<Integer> impares = new ArrayList<>(numeros);
        impares.removeIf(n -> n % 2 == 0);
        impares.forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        System.out.println("4) Números maiores que 50:");
        Predicate<Integer> maiorQue50 = n -> n > 50;
        numeros.stream().filter(maiorQue50).forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        System.out.println("5) Dobro de cada número:");
        Consumer<Integer> imprimeDobro = n -> System.out.print(n * 2 + " ");
        numeros.forEach(imprimeDobro);
        System.out.println();
    }
}
