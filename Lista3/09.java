import java.util.Random;

class SomadorLinhasThread extends Thread {
    private int[][] matriz;
    private int linhaInicio;
    private int linhaFim;
    private long somaParcial = 0;

    public SomadorLinhasThread(int[][] matriz, int linhaInicio, int linhaFim) {
        this.matriz = matriz;
        this.linhaInicio = linhaInicio;
        this.linhaFim = linhaFim;
    }

    @Override
    public void run() {
        for (int i = linhaInicio; i < linhaFim; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                somaParcial += matriz[i][j];
            }
        }
    }

    public long getSomaParcial() {
        return somaParcial;
    }
}

public class Exercicio09 {
    public static void main(String[] args) throws InterruptedException {
        final int LINHAS = 5000;
        final int COLUNAS = 5000;
        final int NUM_THREADS = 4;

        int[][] matriz = new int[LINHAS][COLUNAS];
        Random random = new Random();

        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                matriz[i][j] = random.nextInt(100);
            }
        }

        long inicioSeq = System.currentTimeMillis();
        long somaSeq = 0;
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                somaSeq += matriz[i][j];
            }
        }
        long fimSeq = System.currentTimeMillis();
        long tempoSeq = fimSeq - inicioSeq;

        long inicioPar = System.currentTimeMillis();
        SomadorLinhasThread[] threads = new SomadorLinhasThread[NUM_THREADS];
        int linhasPorThread = LINHAS / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int inicioLinha = i * linhasPorThread;
            int fimLinha = (i == NUM_THREADS - 1) ? LINHAS : inicioLinha + linhasPorThread;
            threads[i] = new SomadorLinhasThread(matriz, inicioLinha, fimLinha);
            threads[i].start();
        }

        long somaPar = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
            somaPar += threads[i].getSomaParcial();
        }
        long fimPar = System.currentTimeMillis();
        long tempoPar = fimPar - inicioPar;

        System.out.println("Soma Sequencial: " + somaSeq);
        System.out.println("Tempo Sequencial: " + tempoSeq + " ms");
        System.out.println("\nSoma Concorrente (4 threads): " + somaPar);
        System.out.println("Tempo Concorrente: " + tempoPar + " ms");

        System.out.println("\nResultados iguais: " + (somaSeq == somaPar ? "Sim" : "Nao"));
        if (tempoPar > 0) {
            System.out.printf("Speedup: %.2fx%n", (double) tempoSeq / tempoPar);
        }
    }
}
