import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CotacaoPrecos {

    static class Loja implements Callable<Double> {
        private final String nome;
        private final Random random = new Random();

        Loja(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public Double call() throws Exception {
            Thread.sleep(300 + random.nextInt(1700));

            double preco = 500 + random.nextDouble() * 1000;
            preco = Math.round(preco * 100.0) / 100.0;

            System.out.printf("%s respondeu: R$ %.2f%n", nome, preco);
            return preco;
        }
    }

    public static void main(String[] args) throws Exception {
        String[] nomes = {"Loja A", "Loja B", "Loja C", "Loja D",
                          "Loja E", "Loja F", "Loja G", "Loja H"};

        ExecutorService executor = Executors.newFixedThreadPool(nomes.length);

        List<Loja> lojas = new ArrayList<>();
        List<Future<Double>> futuros = new ArrayList<>();

        System.out.println("Pesquisando\n");

        for (String nome : nomes) {
            Loja loja = new Loja(nome);
            lojas.add(loja);
            futuros.add(executor.submit(loja));
        }

        double menorPreco = Double.MAX_VALUE;
        String melhorLoja = "";

        for (int i = 0; i < futuros.size(); i++) {
            double preco = futuros.get(i).get();
            if (preco < menorPreco) {
                menorPreco = preco;
                melhorLoja = lojas.get(i).getNome();
            }
        }

        executor.shutdown();

        System.out.printf("%nMenor preço encontrado: R$ %.2f na %s%n", menorPreco, melhorLoja);
    }
}
