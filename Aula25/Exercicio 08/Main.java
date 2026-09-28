import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ValidadorLote implements Callable<Integer> {
    private final int idLote;
    private final Random random = new Random();

    public ValidadorLote(int idLote) {
        this.idLote = idLote;
    }

    @Override
    public Integer call() throws Exception {
        int tempoEspera = 100 + random.nextInt(201);
        Thread.sleep(tempoEspera);

        int transacoesAprovadas = 50 + random.nextInt(101);

        System.out.println(Thread.currentThread().getName() + 
                " -> Lote #" + idLote + " validado em " + tempoEspera + 
                " ms. Aprovadas: " + transacoesAprovadas);

        return transacoesAprovadas;
    }
}

public class Main {
    public static void main(String[] args) {
        final int TOTAL_LOTES = 30;

        ExecutorService executor = Executors.newFixedThreadPool(6);
        List<Future<Integer>> resultadosFuturos = new ArrayList<>();

        System.out.println("A submeter " + TOTAL_LOTES + " lotes para processamento...\n");
        long inicioTempo = System.currentTimeMillis();

        for (int i = 1; i <= TOTAL_LOTES; i++) {
            Callable<Integer> tarefa = new ValidadorLote(i);
            Future<Integer> futuro = executor.submit(tarefa);
            resultadosFuturos.add(futuro);
        }

        int totalTransacoesAprovadas = 0;
        try {
            for (int i = 0; i < resultadosFuturos.size(); i++) {
                int aprovadasLote = resultadosFuturos.get(i).get();
                totalTransacoesAprovadas += aprovadasLote;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        long fimTempo = System.currentTimeMillis();

        System.out.println("\n-------------------------------------------");
        System.out.println("Processamento de todos os lotes finalizado!");
        System.out.println("Total de transações bancárias aprovadas: " + totalTransacoesAprovadas);
        System.out.println("Tempo total de execução: " + (fimTempo - inicioTempo) + " ms");
        System.out.println("-------------------------------------------");
    }
}