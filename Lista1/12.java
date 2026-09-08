public abstract class Forma {
    
    public abstract double calcularArea() {}
}

public class Circulo implements Forma {
    
    public Circulo() {};
    
    public double calcularArea(double umRaio) {
        double resposta = 2*3.14*umRaio*umRaio;
        return resposta;
    };
};

public class Retangulo implements Forma {
    
    public Retangulo() {};
    
    public double calcularArea(umLado,outroLado) {
        double resposta = umLado*outroLado;
        return resposta;
    };
};

public class Main {
    public static void main(String[] args) {
        
        Forma circulo1 = new Circulo();
        double area_circulo = circulo1.calcularArea(10.0);
        
        Forma retangulo1 = new Retangulo();
        double area_retangulo = retangulo1.calcularArea(10.0,15.0);

        
        
    	System.out.println("Area circulo: " + area_circulo);
    	System.out.println("Area retangulo: " + area_retangulo);
	
    }
}
