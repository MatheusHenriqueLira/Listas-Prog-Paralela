public class BufferMonitor {
    private int dado;
    private boolean cheio = false;

    public synchronized void produzir(int valor){
       
        while (cheio){
            try{
                wait();
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        this.dado= valor;
        this.cheio = true;
        System.out.println("Produtor gravou: " + valor);
        
        notifyAll();
    }

    public synchronized int consumir(){

        while(!cheio){
            try{
                wait();
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        this.cheio = false;
        System.out.println("Consumidor leu: " + dado);

        notifyAll();

        return dado;
    } 
}

