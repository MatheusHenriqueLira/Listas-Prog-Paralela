public class Main {
    public static void main(String[] args) {
        Estudante[] estudantes = new Estudante[3];

        estudantes[0] = new Estudante(1001, "José Antonio");
        estudantes[1] = new Estudante(1002, "Manuela Antonelli");
        estudantes[2] = new Estudante(1003, "Matheus Henrique");

        for( int i = 0; i < estudantes.length; i ++){
            System.out.println("RA: " + estudantes[i].getRA() + " | Nome: " + estudantes[i].getNome());
        }

    }
}