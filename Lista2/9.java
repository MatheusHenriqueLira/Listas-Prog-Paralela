public class main{
    public static void main(String[] arg){
        int[] numeros = {10,11,12};

        try {

            System.out.println("conversão de String");
            int numero_convertido = Integer.parseInt("abc"); 

            System.out.println("acessar o array");
            int valor = numeros[5]; 

            System.out.println("Fim do try, se não tiver dado erro");

        } catch (NumberFormatException e) {
            System.out.println("Erro 1");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro 2");
        }

        System.out.println("Foi certinho");
    }
}
