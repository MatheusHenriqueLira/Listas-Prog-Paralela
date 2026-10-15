import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SomaArray extends RecursiveTask<Long> {
    private static final long serialVersionUID = 1L;
    static final int LIMIAR = 10_000;
    long[] array;
    int inicio, fim;

    public SomaArray(long[] array, int inicio, int fim) {
        this.array = array;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    protected Long compute() {
        if (fim - inicio <= LIMIAR) {
            long soma = 0;
            for (int i = inicio; i < fim; i++) {
                soma += array[i];
            }
            return soma;
        }

        int meio = (inicio + fim) / 2;
        SomaArray esquerda = new SomaArray(array, inicio, meio);
        SomaArray direita = new SomaArray(array, meio, fim);

        esquerda.fork();
        return direita.compute() + esquerda.join();
    }

    public static void main(String[] args) {
        long[] array = new long[50_000_000];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextLong(100);
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();
        long total = pool.invoke(new SomaArray(array, 0, array.length));

        System.out.println("Soma total: " + total);
    }
}
