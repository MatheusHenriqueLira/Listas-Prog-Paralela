public class Professor {
    String nome;

    public Professor(String nome) {
        this.nome = nome;
    }
}

public class Disciplina {
    String nome;
    Professor professor;

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
    }
}

public class Main {
    public static void main(String[] args) {
        Professor professor = new Professor("João");
        Disciplina disciplina = new Disciplina("Programação", professor);

        System.out.println("Disciplina: " + disciplina.nome);
        System.out.println("Professor: " + disciplina.professor.nome);
    }
}

//Essa relação é de associação pois disciplina tem uma referência ao objeto professor
//porém ambas classes são independentes uma da outra