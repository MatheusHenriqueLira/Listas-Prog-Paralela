public abstract class Animal {
    
    public abstract void emitirSom();

public class Cachorro implements Animal {
    
    public Cachorro() {};
    
    public void emitirSom() {
        System.out.println("Au Au");
    };
};

public class Gato implements Animal {
    
    public Gato() {};
    
    public void emitirSom() {
        System.out.println("Miau Miau");
    };
};

public class Main {
    public static void main(String[] args) {
        
        Animal cachorro1 = new Cachorro();
        Animal gato1 = new Gato();

        cachorro1.emitirSom();
        gato1.emitirSom();
	
    }
}
