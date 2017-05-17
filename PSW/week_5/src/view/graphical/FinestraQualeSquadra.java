package view.graphical;

import controller.Controller;

import javax.swing.JOptionPane;

public class FinestraQualeSquadra {

    private Controller.QualeSquadra quale;

    public FinestraQualeSquadra() {

        // Task QualeSquadra
        // Imposta vettore di Object contenente le stringhe con le opzioni che si vogliono visualizzare
        Object[] options = {"In casa", "In trasferta"};

        // Visualizza la finestra (OptionDialog) e memorizza in n l'opzione selezionata dall'utente
        Object quale = JOptionPane.showInputDialog(
                null, // frame padre della finestra
                "Quale squadra vuoi processare?",// messaggio per l'utente
                "Selezione squadra", // titolo della finestra
                JOptionPane.QUESTION_MESSAGE, // tipo di messaggio (in questo caso una domanda)
                null, // icona da visualizzare
                options, // array delle opzioni
                options[0] // opzione di default
        );
        if (quale == options[0]) {
            this.quale = Controller.QualeSquadra.IN_CASA;
        } else {
            this.quale = Controller.QualeSquadra.FUORI_CASA;
        }
    }

    public Controller.QualeSquadra attendiSelezione() {
        return quale;
    }
}
