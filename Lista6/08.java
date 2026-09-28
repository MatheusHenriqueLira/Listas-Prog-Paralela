public class Exercicio08 {
    public static void main(String[] args) {
        System.out.println("=== Questão 8: Threads de Usuário ===");

        Thread threadProcessamento = new Thread(() -> {
            try {
                System.out.println("[Processamento] Tarefa iniciada (duração: 5s)");
                Thread.sleep(5000);
                System.out.println("[Processamento] Tarefa concluída com sucesso.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Processamento");

        Thread threadGravacao = new Thread(() -> {
            try {
                System.out.println("[Gravação] Tarefa iniciada (duração: 5s)");
                Thread.sleep(5000);
                System.out.println("[Gravação] Tarefa concluída com sucesso.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Gravação");

        Thread threadRelatorio = new Thread(() -> {
            try {
                System.out.println("[Relatório] Tarefa iniciada (duração: 5s)");
                Thread.sleep(5000);
                System.out.println("[Relatório] Tarefa concluída com sucesso.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Relatório");

        threadProcessamento.start();
        threadGravacao.start();
        threadRelatorio.start();

        System.out.println("[Main] Três threads de usuário iniciadas.");
        System.out.println("[Main] Thread principal aguardando o término das threads usando join().");

        try {
            threadProcessamento.join();
            threadGravacao.join();
            threadRelatorio.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread principal interrompida.");
        }

        System.out.println("[Main] Todas as threads foram finalizadas. Encerrando aplicação.");
    }
}
