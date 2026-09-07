public class Main {
  public static void main(String args[]){
    int[][][] matriz = new int[2][2][2];   

    matriz[0][0][0] = 1;
    matriz[1][0][0] = 2;
    matriz[1][1][0] = 3;
    matriz[1][1][1] = 4;
    matriz[0][1][0] = 5;
    matriz[0][0][1] = 6;
    matriz[0][1][1] = 7;
    matriz[1][0][1] = 1;

    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++) {
        for (int k = 0; k < matriz[i][j].length; k++) {
          System.out.println("matriz[" + i + "][" + j + "][" + k + "] = " + matriz[i][j][k]);
          }
        }
      }
    }
  }  
