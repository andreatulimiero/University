package controller;

import view.text.ConsoleInputOutput;

import java.util.List;

public class TextController extends Controller {

    @Override
    public int selezionaPartita(List<String> partite) {

        stampa("\nPartite:\n");
        int k = 1;
        for (String tlp : partite)
            stampa(k++ + ") " + tlp + "\n");

        stampa(k++ + ") Nessuna partita\n");

        while (true) {
            stampa("Scegliere una partita [1-" + (k - 1) + "]: ");
            int n = ConsoleInputOutput.getInt();
            if (n > 0 && n <= partite.size())
                return n;
            if (n == partite.size() + 1)
                System.exit(0);
        }
    }

    @Override
    public String qualeSquadra() {

        stampa("\nSquadra da processare:\n");
        stampa("1) Casa\n");
        stampa("2) Transferta\n");

        while (true) {
            stampa("Scegliere una delle due opzioni [1-2]: ");
            int n = ConsoleInputOutput.getInt();
            if (n == 1)
                return "casa";
            if (n == 2)
                return "transferta";
        }
    }

    @Override
    public boolean altraSquadra() {

        stampa("\nVuoi procedere con l'altra squadra?\n");
        stampa("1) Sì\n");
        stampa("2) No\n");

        while (true) {
            stampa("Scegliere una delle due opzioni [1-2]: ");
            int n = ConsoleInputOutput.getInt();
            if (n == 1)
                return true;
            if (n == 2)
                return false;
        }
    }

    @Override
    protected void stampa(String msg) {
        ConsoleInputOutput.stampa(msg);
    }


}
