public class Main {
    public static void main(String[] args) {

        Thread t1 = new ThreadUm();

        Thread t2 = new ThreadDois();

        t1.start();

        t2.start();
    }

}