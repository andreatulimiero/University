import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner in = new Scanner(System.in);
        boolean corretto = false;
        int numCorridori = 0;

        while (!corretto) {

            try {
                System.out.println("Inserire il numero di corridori (n>=1): ");
                numCorridori = in.nextInt();
                if (numCorridori > 0)
                    corretto = true;
                else
                    System.err.println("Inserire un numero maggiore di 0!");
            } catch (NumberFormatException ex) {
                System.err.println("Inserire un numero intero!");
            }
        }
        Barriera barriera = new Barriera(numCorridori);
        for (int i = 0; i < numCorridori; i++) {
            Thread thread = new Thread(new Corridore("corridore " + i, barriera));
            thread.start();
        }
    }
}
