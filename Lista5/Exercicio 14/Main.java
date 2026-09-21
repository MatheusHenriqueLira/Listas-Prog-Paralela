import java.util.concurrent.atomic.AtomicLong;

public class Main {

    private static AtomicLong operacoes = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        int totalThreads = 4;
        int incrementosPorThread = 50_000;

        Thread[] threads = new Thread[totalThreads];

        for (int i = 0; i < totalThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementosPorThread; j++) {
                    operacoes.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < totalThreads; i++) {
            threads[i].join();
        }

        System.out.println("Operações realizadas: " + operacoes.get());
    }
}