import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Random;

public class Concorrente {
    public static void main(String[] args) {

        Random rand = new Random();
        int[][] matriz = new int[4000][4000];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = rand.nextInt();
            }
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> somaLinha1000 = () -> {
            int soma1 = 0;
            for (int m = 0; m < matriz.length; m++) {
                for (int n = 0; n < 1000; n++ )
                    soma1 += matriz[m][n];
            }
            return soma1;
        };

        Callable<Integer> somaLinha2000 = () -> {
            int soma2 = 0;
            for (int m = 0; m < matriz.length; m++) {
                for (int n = 1000; n < 2000; n++ )
                    soma2 += matriz[m][n];
            }
            return soma2;
        };

        Callable<Integer> somaLinha3000 = () -> {
            int soma3 = 0;
            for (int m = 0; m < matriz.length; m++) {
                for (int n = 2000; n < 3000; n++ )
                    soma3 += matriz[m][n];
            }
            return soma3;
        };

        Callable<Integer> somaLinha4000 = () -> {
            int soma4 = 0;
            for (int m = 0; m < matriz.length; m++) {
                for (int n = 3000; n < 4000; n++ )
                    soma4 += matriz[m][n];
            }
            return soma4;
        };
        
        Future<Integer> futuro = executor.submit(somaLinha1000);
        try {
            Integer resultado1 = futuro.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        Future<Integer> futuro2 = executor.submit(somaLinha2000);
        try {
            Integer resultado2 = futuro2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        Future<Integer> futuro3 = executor.submit(somaLinha3000);
        try {
            Integer resultado3 = futuro3.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        Future<Integer> futuro4 = executor.submit(somaLinha4000);
        try {
            Integer resultado4 = futuro4.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        //Integer somaTudo = futuro.get() + futuro2.get() + futuro3.get() + futuro4.get();

    }
}
