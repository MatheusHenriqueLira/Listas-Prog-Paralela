public class operadores{
  public static void main(string[] args){
    int a = 10;
    int b = 3;
    
    System.out.println("Valores: a = " + a + ", b = " + b);
    System.out.println("Soma (a + b): " + (a + b));
    System.out.println("Subtração (a - b): " + (a - b));
    System.out.println("Multiplicação (a * b): " + (a * b));
    System.out.println("Divisão (a / b): " + (a / b));
    System.out.println("Resto da divisão (a % b): " + (a % b));

    double x = 10.0;
    double y = 3.0;

    System.out.println("Valores: x = " + x + ", y = " + y);
    System.out.println("Soma (x + y): " + (x + y));
    System.out.println("Subtração (x - y): " + (x - y));
    System.out.println("Multiplicação (x * y): " + (x * y));
    System.out.println("Divisão (x / y): " + (x / y));
    System.out.println("Resto da divisão (x % y): " + (x % y));
  }
}

//No valor int ele ignora as casas quebradas e por isso ele tem um valor de resto, já no double ele
//considera as casas decimais.