public class SomadorThreads extends Thread{
    private int[] vetor; 
    private int inicio;
    private int fim;
    private long somaParcial = 0;

    public SomadorThreads (int[] vetor, int inicio, int fim){
        this.vetor = vetor;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override 
    public void run(){
        for (int i = inicio; i < fim; i++){
            somaParcial += vetor[i];
        }
    }

    public long getSomaParcial(){
        return somaParcial;
    }
    

    
}
