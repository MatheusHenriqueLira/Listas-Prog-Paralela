public class Matematica {
    
    public static int dobro(int n) {
        resposta = n*2;
        return resposta;
    };
    
    public static int quadrado(int n) {
        resposta = n*n;
        return resposta;
    };
    
    public static double media(double a, double b) {
        resposta = (a + b)/2;
        return resposta;
    };
}

public class Main {
    public static void main(String[] args) {
        
        Matematica.dobro(10);
        Matematica.quadrado(10);
        Matematica.media(8.0, 7.0);
    }
}

/*
Métodos estáticos de uma classe são métodos que dispensam
chamada através de um objeto, ou seja, apenas executam as
linhas de código do método, sendo chamados através do nome
da classe.
*/