import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      
        System.out.println("--- Testando Exceção Verificada (Checked) ---");
        try {
            File arquivo = new File("arquivo_inexistente.txt");
            Scanner scanner = new Scanner(arquivo); 
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tratamento do Checked Exception: Arquivo não encontrado no sistema.");
        }

        System.out.println();


        System.out.println("--- Testando Exceção Não Verificada (Unchecked) ---");
        try {
            String texto = null;
            System.out.println(texto.length()); 
        } catch (NullPointerException e) {
            System.out.println("Tratamento do Unchecked Exception: Tentativa de acessar método em uma referência nula.");
        }
    }
}