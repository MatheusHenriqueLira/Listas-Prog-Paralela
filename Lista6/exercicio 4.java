public class Questao04 {

    static class Recurso {
        private boolean disponivel = false;

        public synchronized void aguardar(int id) throws InterruptedException {
            System.out.println(id + ": Aguardando recurso");
            while (!disponivel) {         
                wait();
            }
            System.out.println("Thread " + id + " foi liberada");
        }

        public synchronized void disponibilizar() {
            disponivel = true;
            System.out.println("recurso disponível.");
            notify();                     
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Recurso recurso = new Recurso();

        for (int i = 1; i <= 5; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    recurso.aguardar(id);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Consumidora-" + id).start();
        }

        Thread.sleep(2000);

        Thread produtora = new Thread(recurso::disponibilizar, "Produtora");
        produtora.start();
        produtora.join();

        System.out.println("Fim");
    }
}
