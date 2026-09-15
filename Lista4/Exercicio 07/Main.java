public class Main{
    public static void main(String[] args) {

        BufferMonitor buffer = new BufferMonitor();
        Produtor produtor = new Produtor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        produtor.start();
        consumidor.start();


    }
}