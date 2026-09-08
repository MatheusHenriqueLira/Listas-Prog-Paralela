import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {

    public void lerArquivo(String nomeArquivo) throws IOException {
        FileReader leitor = new FileReader(nomeArquivo);
        System.out.println("Arquivo aberto com sucesso: " + nomeArquivo);

        int caractere;
        while ((caractere = leitor.read()) != -1) {
            System.out.print((char) caractere);
        }

        leitor.close();
    }

    public static void main(String[] args) {
        LeitorArquivo leitorArquivo = new LeitorArquivo();

        try {
            leitorArquivo.lerArquivo("arquivo.txt");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}