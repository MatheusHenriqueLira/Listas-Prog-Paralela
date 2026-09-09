import java.util.Random;

class SumTask extends Thread {
    private final int[] vector;
    private final int startIndex;
    private final int endIndex;
    private long partialSum;

    public SumTask(int[] vector, int startIndex, int endIndex) {
        this.vector = vector;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.partialSum = 0;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            partialSum += vector[i];
        }
    }

    public long getPartialSum() {
        return partialSum;
    }
}

public class Main {

    public static long sumParallel(int[] vector, int numThreads) throws InterruptedException {
        SumTask[] threads = new SumTask[numThreads];
        int length = vector.length;
        int chunkSize = length / numThreads;

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? length : (i + 1) * chunkSize;

            threads[i] = new SumTask(vector, start, end);
            threads[i].start();
        }

        long totalSum = 0;
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            totalSum += threads[i].getPartialSum();
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Soma com %d thread(s): %d | Tempo: %.3f ms%n", numThreads, totalSum, durationMs);
        return totalSum;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 5_000_000;
        int[] vector = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            vector[i] = rand.nextInt(10);
        }

        System.out.println("--- Comparativo de Desempenho (Soma de Vetor) ---\n");

        sumParallel(vector, 1);
        sumParallel(vector, 2);
        sumParallel(vector, 4);
    }
}