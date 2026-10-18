public class ExercicioDaemon {
    public static void main(String[] args) {
        
        Thread threadDaemon = new Thread(() -> {
            while (true) {
                System.out.println("Monitorando sistema");
                try {
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    System.out.println("Thread de monitoramento interrompida.");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        threadDaemon.setDaemon(true);

        Thread threadUsuario = new Thread(() -> {
            System.out.println("Thread de Usuário: Iniciando processamento de 5 segundos.");
            try {
                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread de Usuário: Processamento concluído. Encerrando thread de usuário.");
        });

        threadDaemon.start();
        threadUsuario.start();
        
        System.out.println("Thread Principal concluída.");
    }
}