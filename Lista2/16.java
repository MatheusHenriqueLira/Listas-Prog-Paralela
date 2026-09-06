public class main{
    public static void main(String[] arg){
        int[][] matriz = {
            {10,11,12},
            {1,2,3},
            {10,20,30}
        };

        int soma_diagonal = 0;

        for (int i = 0; i < 3; i++) {
            
            for (int j = 0; j < 3; j++) {

                System.out.print(matriz[i][j] + " ");

                if (i == j) {
                    soma_diagonal = soma_diagonal + matriz[i][j]; 
                }
            }

            System.out.println();
        }

        System.out.println(" soma dos elementos é: " + soma_diagonal);
    }
}
