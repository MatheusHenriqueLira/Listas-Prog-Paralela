public class Produtor extends Thread {
    private BufferMonitor buffer;

    public Produtor(BufferMonitor buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            buffer.produzir(i);
            
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}