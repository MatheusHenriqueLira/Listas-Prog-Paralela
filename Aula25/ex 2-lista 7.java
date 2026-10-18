import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SimuladorLeitura implements Callable<Integer> {
    private String nomeFicheiro;

    public SimuladorLeitura(String nomeFicheiro) {
        this.nomeFicheiro = nomeFicheiro;
    }

    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        Thread.sleep(random.nextInt(1000) + 500);

        int palavras = random.nextInt(1000) + 1;
        System.out.println("O processamento de " + nomeFicheiro + " foi concluído.");
        
        return palavras; 
    }
}
public class ContadorPalavras {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Integer>> resultadosFutures = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Callable<Integer> tarefa = new SimuladorLeitura("arquivo " + i);
            resultadosFutures.add(executor.submit(tarefa));
        }
        int totalPalavras = 0;
        for (int i = 0; i < resultadosFutures.size(); i++) {
            try {
                int palavrasContadas = resultadosFutures.get(i).get();
                System.out.println("-> Arquivo " + (i + 1) + " tem " + palavrasContadas + " palavras.");
                totalPalavras += palavrasContadas;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total de palavras geradas em todos os ficheiros: " + totalPalavras);
        executor.shutdown();
    }
}