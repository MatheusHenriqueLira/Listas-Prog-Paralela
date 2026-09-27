public class CaixaMensagem {
    private String mensagem;
    private boolean disponivel = false;

    public synchronized void enviar(String mensagem) {
        while (disponivel) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        this.mensagem = mensagem;
        this.disponivel = true;
        notifyAll();
    }

    public synchronized String receber() {
        while (!disponivel) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        String msgRecuperada = this.mensagem;
        this.disponivel = false;
        notifyAll();

        return msgRecuperada;
    }
}
