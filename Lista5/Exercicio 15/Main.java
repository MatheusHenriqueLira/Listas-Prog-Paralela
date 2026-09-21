import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static AtomicBoolean executado = new AtomicBoolean(false);

    public static void main(String[] args) {
        int totalThreads = 5;

        for (int i = 1; i <= totalThreads; i++) {
            final String nomeThread = "Thread-" + i;

            new Thread(() -> {
                if (executado.compareAndSet(false, true)) {
                    System.out.println(nomeThread + ": executou a operação");
                } else {
                    System.out.println(nomeThread + ": a operação já havia sido executada");
                }
            }).start();
        }
    }
}