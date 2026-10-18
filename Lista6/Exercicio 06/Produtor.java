public class Produtor extends Thread {
    private final Buffer buffer;
    private final int quantidade;

    public Produtor(Buffer buffer, String nome, int quantidade) {
        super(nome);
        this.buffer = buffer;
        this.quantidade = quantidade;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= quantidade; i++) {
                buffer.produzir(i, getName());
                Thread.sleep(150); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}