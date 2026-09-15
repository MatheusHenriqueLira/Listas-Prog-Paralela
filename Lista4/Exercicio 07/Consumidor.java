public class Consumidor extends Thread{
    private BufferMonitor buffer;

    public Consumidor(BufferMonitor buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for(int i = 1; i<-5; i++){
            
            buffer.consumir();

            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}