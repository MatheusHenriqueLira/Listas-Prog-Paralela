public class ThreadDois extends Thread {
    @Override
    public void run() {
        synchronized (Recursos.recurso2) {

            System.out.println("Thread 2: Bloqueou o recurso 2.");

            try {
                Thread.sleep(100);;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Thread 2: Aguardando liberação do recurso 1...");

            synchronized (Recursos.recurso1) {
                System.out.println("Thread 2: Conseguiu bloquear ambos os recursos!");
            }
        }
    }
}