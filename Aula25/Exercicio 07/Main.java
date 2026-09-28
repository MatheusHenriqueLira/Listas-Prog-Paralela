import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Tarefa Callable responsável por processar uma faixa exata de pixels
class ProcessadorFaixa implements Callable<Integer> {
    private final int[] pixels;
    private final int inicio;
    private final int fim;
    private final int criterioCor;

    public ProcessadorFaixa(int[] pixels, int inicio, int fim, int criterioCor) {
        this.pixels = pixels;
        this.inicio = inicio;
        this.fim = fim;
        this.criterioCor = criterioCor;
    }

    @Override
    public Integer call() {
        int contadorPixelsAtendidos = 0;

        // Processa a partição de 500.000 pixels atribuída à thread
        for (int i = inicio; i < fim; i++) {
            // Critério de simulação: pixel com valor maior ou igual ao critério
            if (pixels[i] >= criterioCor) {
                contadorPixelsAtendidos++;
            }
        }

        System.out.println(Thread.currentThread().getName() 
                + " processou faixa [" + inicio + " - " + fim + "] -> Encontrados: " + contadorPixelsAtendidos);

        return contadorPixelsAtendidos;
    }
}

public class Main {
    public static void main(String[] args) {
        final int TOTAL_PIXELS = 2_000_000;
        final int TOTAL_THREADS = 4;
        final int TAMANHO_FAIXA = TOTAL_PIXELS / TOTAL_THREADS; // 500.000 por thread
        final int CRITERIO_COR = 128; // Limiar de tom (ex: escala de cinza 0 a 255)

        System.out.println("Gerando vetor com " + TOTAL_PIXELS + " pixels...");
        int[] imagemPixels = new int[TOTAL_PIXELS];
        Random random = new Random();

        // Preenche a imagem simulada com valores aleatórios de 0 a 255
        for (int i = 0; i < TOTAL_PIXELS; i++) {
            imagemPixels[i] = random.nextInt(256);
        }

        // Cria o pool fixo com 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(TOTAL_THREADS);
        List<Future<Integer>> resultadosFuturos = new ArrayList<>();

        long inicioTempo = System.currentTimeMillis();

        // Submete as 4 tarefas particionadas (Callable<Integer>)
        for (int t = 0; t < TOTAL_THREADS; t++) {
            int inicio = t * TAMANHO_FAIXA;
            int fim = inicio + TAMANHO_FAIXA;

            Callable<Integer> tarefa = new ProcessadorFaixa(imagemPixels, inicio, fim, CRITERIO_COR);
            Future<Integer> futuro = executor.submit(tarefa);
            resultadosFuturos.add(futuro);
        }

        // Consolida o total somando o retorno de cada Future
        int totalPixelsModificados = 0;
        try {
            for (Future<Integer> futuro : resultadosFuturos) {
                // futuro.get() bloqueia até que a respectiva thread conclua o cálculo
                totalPixelsModificados += futuro.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Encerra o executor adequadamente
            executor.shutdown();
        }

        long fimTempo = System.currentTimeMillis();

        System.out.println("\n--- Resultado Final ---");
        System.out.println("Critério de cor adotado: valor >= " + CRITERIO_COR);
        System.out.println("Total de pixels que atendem ao critério: " + totalPixelsModificados);
        System.out.println("Tempo total de execução: " + (fimTempo - inicioTempo) + " ms");
    }
}