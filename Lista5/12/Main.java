import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static void main(String[] args) {
        private static final AtomicInteger num = new AtomicInteger(0);
        
        Runnable soma = () -> {
            for (int i = 0; i < 10; i++) {
                num.incrementAndGet();
            }
        };

        Thread thread1 = new Thread(soma);
        Thread thread2 = new Thread(soma);

        thread1.start();
        thread2.start();
        
        System.out.println("Valor final: " + num.get());
    
    }
}