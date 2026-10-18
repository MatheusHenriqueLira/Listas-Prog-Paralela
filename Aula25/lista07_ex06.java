import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class DownloadTask implements Callable<String> {
    private final String nomeArquivo;
    private final int tempoSimuladoMs;

    public DownloadTask(String nomeArquivo, int tempoSimuladoMs) {
        this.nomeArquivo = nomeArquivo;
        this.tempoSimuladoMs = tempoSimuladoMs;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Iniciando download: " + nomeArquivo + "...");
        
        // Simula a pausa de download
        Thread.sleep(tempoSimuladoMs);
        
        return "Download de '" + nomeArquivo + "' concluído com sucesso em " + (tempoSimuladoMs / 1000.0) + " segundos.";
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando o pool de threads para gerenciar as tarefas simultâneas
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // CompletionService permite processar os resultados conforme cada tarefa é concluída
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        Random random = new Random();

        // Criando 3 tarefas com tempos aleatórios de download (entre 1s e 4s)
        DownloadTask t1 = new DownloadTask("musica_mp3.mp3", 1000 + random.nextInt(3000));
        DownloadTask t2 = new DownloadTask("imagem_hd.png", 1000 + random.nextInt(3000));
        DownloadTask t3 = new DownloadTask("video_4k.mp4", 1000 + random.nextInt(3000));

        // Submetendo as tarefas para execução
        completionService.submit(t1);
        completionService.submit(t2);
        completionService.submit(t3);

        System.out.println("--- DOWNLOADS EM ANDAMENTO ---\n");

        // Recupera e exibe o relatório de cada download à medida que terminam
        try {
            for (int i = 0; i < 3; i++) {
                // take() bloqueia até que O PRÓXIMO download termine
                Future<String> resultadoTerminado = completionService.take();
                System.out.println("[RELATÓRIO] " + resultadoTerminado.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Encerra o ExecutorService após a conclusão
            executor.shutdown();
        }

        System.out.println("\nTodos os downloads foram finalizados.");
    }
}