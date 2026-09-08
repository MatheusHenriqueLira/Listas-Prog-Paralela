public class MultiplicadorLinhasThread extends Thread {
    private int[][] matriz;
    private int linhaInicio;
    private int linhaFim;

    public MultiplicadorLinhasThread(int[][] matriz, int linhaInicio, int linhaFim) {
        this.matriz = matriz;
        this.linhaInicio = linhaInicio;
        this.linhaFim = linhaFim;
    }

    @Override
    public void run() {
        for (int i = linhaInicio; i < linhaFim; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] *= 2;
            }
        }
    }
}