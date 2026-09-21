public class ThreadUm extends Thread{
    @Override
    public void run(){
        synchronized (Recursos.recurso1) {
            System.out.println("Thread 1: Bloqueou o recurso 1.");
        }

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Thread 1: Aguardando liberação do recurso 2...");

        synchronized (Recursos.recurso2) {
            System.out.println("Thread 1: Conseguiu bloquear ambos os recursos!");
        }

    }
}