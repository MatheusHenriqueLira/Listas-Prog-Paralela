public class CalculadoraArea {

    //quadrado
    public static double calcularArea(double lado) {
        return lado * lado;
    }

    //retângulo
    public static double calcularArea(double base, double altura) {
        return base * altura;
    }

    //círculo
    public static double calcularArea(double raio, boolean isCirculo) {
        return Math.PI * raio * raio;
    }

    public static void main(String[] args) {
        double areaQuadrado = calcularArea(5);
        System.out.println("Área do quadrado (lado 5): " + areaQuadrado);

        double areaRetangulo = calcularArea(4, 6);
        System.out.println("Área do retângulo (base 4, altura 6): " + areaRetangulo);

        double areaCirculo = calcularArea(3, true);
        System.out.println("Área do círculo (raio 3): " + areaCirculo);
    }
}