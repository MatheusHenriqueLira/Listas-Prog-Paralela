public class Exercicio01 {
    private static volatile boolean pronto = false;

    public static void main(String[] args) {
        System.out.println("=== Questão 1: Polling ===");

        Thread produtora = new Thread(() -> {
            try {
                System.out.println("[Produtora] Iniciando processamento (duração de 5 segundos)");
                Thread.sleep(5000);
                pronto = true;
                System.out.println("[Produtora] Processamento concluído! 'pronto' definido como true.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumidora = new Thread(() -> {
            System.out.println("[Consumidora] Iniciando verificação por polling");
            while (!pronto) {
                System.out.println("[Consumidora] pronto == false: continuo aguardando");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("[Consumidora] pronto == true! O processamento pode continuar.");
        });

        produtora.start();
        consumidora.start();

        try {
            produtora.join();
            consumidora.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("=== Fim da Execução ===");
    }
}
