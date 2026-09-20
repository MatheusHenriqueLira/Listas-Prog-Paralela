import java.util.concurrent.atomic.AtomicReference;

public class TesteAtomicReference {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<String> ref = new AtomicReference<>("Inicial");

        Thread t1 = new Thread(() -> ref.compareAndSet("Inicial", "Thread 1"));
        Thread t2 = new Thread(() -> ref.compareAndSet("Inicial", "Thread 2"));

        t1.start();
        t2.start();
        
        t1.join();
        t2.join();

        System.out.println("Valor armazenado: " + ref.get());
    }
}

// O método faz uma operação atômica, assim a thread que realizar a operação mais rápido é aa que terá 
//o valor guardado.