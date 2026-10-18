public class Consumidor extends Thread {
    private final Buffer buffer;

    public Consumidor(Buffer buffer, String nome) {
        super(nome);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer item = buffer.consumir(getName());
                // Se retornar null, a produção acabou e o buffer esvaziou
                if (item == null) {
                    break;
                }
                Thread.sleep(250); // Simula tempo de processamento do item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}