package view.text;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleInputOutput {

    private static Scanner scanner = new Scanner(System.in);

    public static int getInt() {

        int v = -1;
        try {
            v = scanner.nextInt();
        } catch (NoSuchElementException e) {
            stampa("Input non ammesso.\n");
            scanner.next(); // ignora
        }

        return v;
    }

    public static void stampa(String msg) {
        System.out.print(msg);
    }

}
