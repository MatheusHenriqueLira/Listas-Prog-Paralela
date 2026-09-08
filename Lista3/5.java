import java.util.Arrays;
import java.util.Random;

public class SomaVetor {

  public static void int somaVetor(long[] umVetor)
    Arrays.fill(vetor, 1L);
    for (int i = 0; i < TAMANHO; i++) {
      soma += vetor[i];
    }
}

public class MyThread extends Thread {
  @Override
  public void run(umVetor) {
    long inicio = System.currentTimeMillis();
    System.out.println("Começo da soma: " + inicio);

    SomaVetor.somaVetor(umVetor);

    long fim = System.currentTimeMillis();
    System.out.println("Fim da soma: " + fim);
    diferenca = fim - inicio;

    System.out.println("Tempo de execução: " + diferenca);
  }
}

public class Exercicio5 {
    public static void main(String[] args) {

      final int TAMANHO = 50000000;
      long[] vetor = new long[TAMANHO];
      
      MyThread thread1 = new MyThread();
      MyThread thread2 = new MyThread();

      thread1.run(vetor);
      thread2.run(vetor);
    }
}