import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalculadoraFatorialConcorrente {

    public static long calcularFatorial(int n) {
        long fat = 1;
        for (int i = 2; i <= n; i++) {
            fat *= i;
        }
        return fat;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Calculadora Fatorial Concorrente (Aula 25 - Ex 01) ===");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Callable<Long> t1 = () -> {
            System.out.println("Calculando 5! na thread: " + Thread.currentThread().getName());
            return calcularFatorial(5);
        };

        Callable<Long> t2 = () -> {
            System.out.println("Calculando 7! na thread: " + Thread.currentThread().getName());
            return calcularFatorial(7);
        };

        Callable<Long> t3 = () -> {
            System.out.println("Calculando 9! na thread: " + Thread.currentThread().getName());
            return calcularFatorial(9);
        };

        Callable<Long> t4 = () -> {
            System.out.println("Calculando 11! na thread: " + Thread.currentThread().getName());
            return calcularFatorial(11);
        };

        Future<Long> f1 = executor.submit(t1);
        Future<Long> f2 = executor.submit(t2);
        Future<Long> f3 = executor.submit(t3);
        Future<Long> f4 = executor.submit(t4);

        System.out.println("\n--- Resultados Obtidos ---");
        System.out.println("5!  = " + f1.get());
        System.out.println("7!  = " + f2.get());
        System.out.println("9!  = " + f3.get());
        System.out.println("11! = " + f4.get());

        executor.shutdown();
    }
}
