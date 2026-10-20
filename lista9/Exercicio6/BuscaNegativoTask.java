package Exercicio6;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class BuscaNegativoTask extends RecursiveTask<Boolean> {
    private static final int LIMNIAR = 5000;
    private final int[] vetor;
    private final int inicio;
    private final int fim;

    public BuscarNegativoTask(int[] vetor, int inicio, int fim){
        this.vetor = vetor;
        this.incio = inicio;
        this.fim = fim;
    }

    @Override 
    protected Boolean compute(){
        int tamanho = fim - inicio;

        if(tamanho <= LIMIAR){
            for(int i = inicio; i < fim; i++){
                if(vetor[i] <0) {
                    return true;
                } 
            }
            return false;
        }

        int meio = inicio + (tamanho / 2);

        BuscaNegativoTask subtarefaEsquerda = new BuscaNegativoTask(vetor, inicio, meio);
        BuscaNegativoTask subtarefaDireita = new BuscaNegativoTask(vetor, inicio, fim);

        subtarefaEsquerda.fork();

        boolean resultadoDireita = subtarefaDireita.compute();

        if (resultadoDireita) {
            return true;
        }

        boolean resultadoEsquerda = subtarefaEsquerda.join();

        return resultadoEsquerda;

        public static void main(String[] args) {
            final int TAMANHO_VETOR = 10_000_000;
        int[] dados = new int[TAMANHO_VETOR];
        Random random = new Random();

        for (int i = 0; i < TAMANHO_VETOR; i++) {
            dados[i] = random.nextInt(1000) + 1;
        }

        dados[7_543_210] = -42;

        ForkJoinPool pool = new ForkJoinPool();
        BuscaNegativoTask tarefaPrincipal = new BuscaNegativoTask(dados, 0, dados.length);

        long inicioTempo = System.currentTimeMillis();
        boolean contemNegativo = pool.invoke(tarefaPrincipal);
        long fimTempo = System.currentTimeMillis();

        System.out.println("Existe número negativo no array? " + contemNegativo);
        System.out.println("Tempo de execução: " + (fimTempo - inicioTempo) + " ms");

        pool.shutdown();
        }

    }
}
