class Processo {
    private boolean pronto = false;

    public synchronized void aguardar() {
        while (!pronto) {
            System.out.println("Thread Consumidora: aguardando");
            try {
                wait(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("A thread foi interrompida.");
            }
        }
        System.out.println("Thread Consumidora: O processamento pode continuar!");
    }

    public synchronized void concluir() {
        pronto = true;
        System.out.println("Thread Produtora: Tarefa concluída. A notificar a thread em espera...");
        
        notify(); 
    }
}

public class ExercicioWaitNotify {
    public static void main(String[] args) {
        Processo processo = new Processo();
        Thread consumidora = new Thread(() -> {
            processo.aguardar();
        });

        Thread produtora = new Thread(() -> {
            try {

                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            processo.concluir();
        });
        consumidora.start();
        produtora.start();
    }
}