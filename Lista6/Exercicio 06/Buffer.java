import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final int CAPACIDADE = 5;
    private final Queue<Integer> fila = new LinkedList<>();
    private boolean producaoFinalizada = false;

    public synchronized void produzir(int item, String nomeProdutor) throws InterruptedException {
        while (fila.size() == CAPACIDADE) {
            wait();
        }

        fila.add(item);
        System.out.println(nomeProdutor + " produziu: " + item + " | Buffer: " + fila.size() + "/" + CAPACIDADE);

        notifyAll();
    }

    public synchronized Integer consumir(String nomeConsumidor) throws InterruptedException {
        while (fila.isEmpty() && !producaoFinalizada) {
            wait();
        }

        if (fila.isEmpty() && producaoFinalizada) {
            return null;
        }

        int item = fila.poll();
        System.out.println(nomeConsumidor + " consumiu: " + item + " | Buffer: " + fila.size() + "/" + CAPACIDADE);

        notifyAll();

        return item;
    }

    public synchronized void finalizarProducao() {
        this.producaoFinalizada = true;
        notifyAll();
    }
}