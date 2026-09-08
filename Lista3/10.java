import java.util.Random;

class ContadorThread extends Thread {
    private int[][] matriz;
    private int linhaInicio;
    private int linhaFim;

    private long maiores = 0;
    private long menores = 0;
    private long iguais = 0;

    public ContadorThread(int[][] matriz, int linhaInicio, int linhaFim) {
        this.matriz = matriz;
        this.linhaInicio = linhaInicio;
        this.linhaFim = linhaFim;
    }

    @Override
    public void run() {
        for (int i = linhaInicio; i < linhaFim; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                int valor = matriz[i][j];
                if (valor > 100) {
                    maiores++;
                } else if (valor < 100) {
                    menores++;
                } else {
                    iguais++;
                }
            }
        }
    }

    public long getMaiores() { return maiores; }
    public long getMenores() { return menores; }
    public long getIguais() { return iguais; }
}

public class Exercicio10 {
    public static void main(String[] args) throws InterruptedException {
        final int LINHAS = 4000;
        final int COLUNAS = 4000;
        final int NUM_THREADS = 4;

        int[][] matriz = new int[LINHAS][COLUNAS];
        Random random = new Random(42);

        System.out.println("Inicializando a matriz de 4000x4000 com números aleatórios...");
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                matriz[i][j] = random.nextInt(201);
            }
        }

        System.out.println("\nExecutando Versão Sequencial");
        long inicioSeq = System.currentTimeMillis();

        long maioresSeq = 0;
        long menoresSeq = 0;
        long iguaisSeq = 0;

        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                int valor = matriz[i][j];
                if (valor > 100) {
                    maioresSeq++;
                } else if (valor < 100) {
                    menoresSeq++;
                } else {
                    iguaisSeq++;
                }
            }
        }

        long fimSeq = System.currentTimeMillis();
        long tempoSeq = fimSeq - inicioSeq;

        System.out.println("Maiores que 100: " + maioresSeq);
        System.out.println("Menores que 100: " + menoresSeq);
        System.out.println("Iguais a 100:    " + iguaisSeq);
        System.out.println("Tempo Sequencial: " + tempoSeq + " ms");

        System.out.println("\nExecutando Versão Concorrente (4 Threads)");
        long inicioPar = System.currentTimeMillis();

        ContadorThread[] threads = new ContadorThread[NUM_THREADS];
        int linhasPorThread = LINHAS / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int inicioLinha = i * linhasPorThread;
            int fimLinha = (i == NUM_THREADS - 1) ? LINHAS : inicioLinha + linhasPorThread;
            
            threads[i] = new ContadorThread(matriz, inicioLinha, fimLinha);
            threads[i].start();
        }

        long maioresPar = 0;
        long menoresPar = 0;
        long iguaisPar = 0;

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
            maioresPar += threads[i].getMaiores();
            menoresPar += threads[i].getMenores();
            iguaisPar += threads[i].getIguais();
        }

        long fimPar = System.currentTimeMillis();
        long tempoPar = fimPar - inicioPar;

        System.out.println("Maiores que 100: " + maioresPar);
        System.out.println("Menores que 100: " + menoresPar);
        System.out.println("Iguais a 100:    " + iguaisPar);
        System.out.println("Tempo Concorrente: " + tempoPar + " ms");

        System.out.println("\n=== COMPARAÇÃO ===");
        System.out.println("Resultados coincidem? " + 
            (maioresSeq == maioresPar && menoresSeq == menoresPar && iguaisSeq == iguaisPar ? "SIM" : "NÃO"));
        
        if (tempoPar > 0) {
            double speedup = (double) tempoSeq / tempoPar;
            System.out.printf("Speedup (T_seq / T_par): %.2fx mais rápido%n", speedup);
        }
    }
}