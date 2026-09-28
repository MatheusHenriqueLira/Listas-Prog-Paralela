public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        // Criação de 2 threads produtoras (cada uma produzindo 9 itens)
        Produtor p1 = new Produtor(buffer, "Produtor-1", 9);
        Produtor p2 = new Produtor(buffer, "Produtor-2", 9);

        // Criação de 3 threads consumidoras
        Consumidor c1 = new Consumidor(buffer, "Consumidor-1");
        Consumidor c2 = new Consumidor(buffer, "Consumidor-2");
        Consumidor c3 = new Consumidor(buffer, "Consumidor-3");

        // Disparo das threads
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();

        // Aguarda as duas produtoras terminarem de produzir seus itens
        p1.join();
        p2.join();

        // Avisa ao buffer que a produção acabou para destravar as consumidoras
        buffer.finalizarProducao();

        // Aguarda as três consumidoras esvaziarem o restante e encerrarem
        c1.join();
        c2.join();
        c3.join();

        System.out.println("Programa finalizado com sucesso!");
    }
}