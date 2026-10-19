import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortParalelo {

    private static final int LIMIAR = 1_000;
    private static final int TAMANHO = 10_000_000;

    static class MergeSortTask extends RecursiveAction {

        private final int[] array;
        private final int[] temp; 
        private final int inicio;
        private final int fim;

        MergeSortTask(int[] array, int[] temp, int inicio, int fim) {
            this.array = array;
            this.temp = temp;
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        protected void compute() {
            int tamanho = fim - inicio;

            if (tamanho < LIMIAR) {
                Arrays.sort(array, inicio, fim);
                return;
            }

            int meio = inicio + tamanho / 2;

            MergeSortTask esquerda = new MergeSortTask(array, temp, inicio, meio);
            MergeSortTask direita  = new MergeSortTask(array, temp, meio, fim);

            esquerda.fork();
            direita.compute();
            esquerda.join();

            merge(inicio, meio, fim);
        }

        private void merge(int inicio, int meio, int fim) {
            int i = inicio, j = meio, k = inicio;

            while (i < meio && j < fim) {
                temp[k++] = (array[i] <= array[j]) ? array[i++] : array[j++];
            }
            while (i < meio) temp[k++] = array[i++];
            while (j < fim)  temp[k++] = array[j++];

            System.arraycopy(temp, inicio, array, inicio, fim - inicio);
        }
    }

    public static void main(String[] args) {
        Random random = new Random(42);
        int[] original = new int[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            original[i] = random.nextInt();
        }

        int[] paraleloArr   = original.clone();
        int[] sequencialArr = original.clone();

        System.out.println("Elementos: " + TAMANHO);
        System.out.println("Núcleos disponíveis: " + Runtime.getRuntime().availableProcessors());

        ForkJoinPool pool = new ForkJoinPool();
        int[] temp = new int[TAMANHO];

        long t0 = System.nanoTime();
        pool.invoke(new MergeSortTask(paraleloArr, temp, 0, TAMANHO));
        double tPar = (System.nanoTime() - t0) / 1e9;
        pool.shutdown();

        t0 = System.nanoTime();
        Arrays.sort(sequencialArr);
        double tSeq = (System.nanoTime() - t0) / 1e9;

        System.out.printf("Merge Sort paralelo : %.3f s%n", tPar);
        System.out.printf("Arrays.sort (seq.)  : %.3f s%n", tSeq);
        System.out.println("Resultado correto?  : " + Arrays.equals(paraleloArr, sequencialArr));
    }
}
