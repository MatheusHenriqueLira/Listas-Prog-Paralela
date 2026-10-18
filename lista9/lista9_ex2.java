import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FiltroBrilho extends RecursiveAction {

    private static final int LIMIAR = 50000;
    
    private int[] pixels;
    private int inicio;
    private int fim;
    private int aumentoBrilho;

    public FiltroBrilho(int[] pixels, int inicio, int fim, int aumentoBrilho) {
        this.pixels = pixels;
        this.inicio = inicio;
        this.fim = fim;
        this.aumentoBrilho = aumentoBrilho;
    }

    @Override
    protected void compute() {
        if ((fim - inicio) <= LIMIAR) {
            for (int i = inicio; i < fim; i++) {
                int novoValor = pixels[i] + aumentoBrilho;
                pixels[i] = Math.min(novoValor, 255);
            }
        } 
        else {
            int meio = (inicio + fim) / 2;
            
            FiltroBrilho metadeEsquerda = new FiltroBrilho(pixels, inicio, meio, aumentoBrilho);
            FiltroBrilho metadeDireita = new FiltroBrilho(pixels, meio, fim, aumentoBrilho);
            
            invokeAll(metadeEsquerda, metadeDireita);
        }
    }

    public static void main(String[] args) {
        int tamanhoImagem = 2_000_000;
        int[] imagem = new int[tamanhoImagem];
        for (int i = 0; i < tamanhoImagem; i++) {
            imagem[i] = 100;
        }

        System.out.println("Pixel 0 antes do filtro: " + imagem[0]);

        ForkJoinPool pool = new ForkJoinPool();
        
        FiltroBrilho tarefaPrincipal = new FiltroBrilho(imagem, 0, imagem.length, 50);
        
        System.out.println("A aplicar o filtro de brilho...");
        
        pool.invoke(tarefaPrincipal);
        
        System.out.println("Filtro aplicado com sucesso!");
        System.out.println("Pixel 0 depois do filtro: " + imagem[0]); 
    }
}