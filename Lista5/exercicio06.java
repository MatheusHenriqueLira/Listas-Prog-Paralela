class PistaCorrida {
    private boolean largadaDada = false;

    public synchronized void aguardarLargada(int idCorredor) {
        System.out.println("Corredor " + idCorredor + " aguardando a largada...");
        
        while (!largadaDada) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("Corredor " + idCorredor + " correu!");
    }

    public synchronized void darLargada() {
        System.out.println("\n--- BANDERADA DE LARGADA! ---\n");
        this.largadaDada = true;
        notifyAll(); 
    }
}

class Corredor extends Thread {
    private final int id;
    private final PistaCorrida pista;

    public Corredor(int id, PistaCorrida pista) {
        this.id = id;
        this.pista = pista;
    }

    @Override
    public void run() {
        pista.aguardarLargada(id);
    }
}

public class Main {
    public static void main(String[] args) {
        PistaCorrida pista = new PistaCorrida();
        int totalCorredores = 10;

        for (int i = 1; i <= totalCorredores; i++) {
            new Corredor(i, pista).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        pista.darLargada();
    }
}



