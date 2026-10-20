import java.util.concurrent.RecursiveTask;

public class ContaPalavras extends RecursiveTask<Long> {
    private static final int LIMITE = 500;

    private String[] linhas;
    private int inicio;
    private int fim;
    private String palavraAlvo;

    public ContaPalavras(String[] linhas, String palavraAlvo) {
        this(linhas, 0, linhas.length, palavraAlvo);
    }

    private ContaPalavras(String[] linhas, int inicio, int fim, String palavraAlvo) {
        this.linhas = linhas;
        this.inicio = inicio;
        this.fim = fim;
        this.palavraAlvo = palavraAlvo;
    }

    @Override
    protected Long compute() {
        if (fim - inicio <= LIMITE) {
            return contaSequencial();
        }
        int meio = inicio + (fim - inicio)/2;

        ContaPalavras tarefaEsquerda = new ContaPalavras(linhas, inicio, meio, palavraAlvo);
        ContaPalavras tarefaDireita = new ContaPalavras(linhas, meio, fim, palavraAlvo);

        tarefaEsquerda.fork();

        long resultadoDireita = tarefaDireita.compute();
        long resultadoEsquerda = tarefaEsquerda.join();

        return resultadoEsquerda + resultadoDireita;
    }

    private long contaSequencial() {
        long contador = 0;
        for (int i = inicio; i < fim; i++) {
            contador += contaOcorrencias(linhas[i], palavraAlvo);
        }
        return contador;
    }

    private static long contaOcorrencias(String linha, String palavra) {
        if (linha == null || linha.isEmpty() || palavra.isEmpty()) return 0;

        long contador = 0;
        int indice = 0;

        while ((indice = linha.indexOf(palavra, indice)) != -1) {
            boolean startOk = indice == 0
                || !Character.isLetterOrDigit(linha.charAt(indice -1));
            int endPos = indice + palavra.lenght();
            boolean endOk = endPos == linha.length()
                || !Character.isLetterOrDigit(linha.charAt(endPos));
            
            if (startOk && endOk) { contador++; }

            indice = endPos;
        }
        return contador;
    }
}
