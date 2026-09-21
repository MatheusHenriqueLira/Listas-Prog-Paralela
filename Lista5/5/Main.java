public class Main {
    public static void main(String[] args) {

        MetodoGuardaNum num1 = new MetodoGuardaNum();
        System.out.println("Inicio metodo estatico");
        num1.somaNum(5);
        System.out.println("Fim metodo estatico");
    
        BlocoGuardaNum num2 = new MetodoGuardaNum();
        System.out.println("Inicio bloco estatico");
        num2.somaNum(5);
        System.out.println("Fim bloco estatico");
    
    }
}

/*
A diferença entre as duas formas de controlar o acesso
à thread é que, no método, caso muitas operações complexas
estejam sendo feitas, pode ocorrer de gerar escassez de
recurso, dado que uma thread poderá estar monopolizando-os.
Já no bloco sincronizado, apenas uma operação do método pode
monopolizar recurso, ou seja, após essa operação, a trava já
será removida, evitando escassez de recurso. 
*/