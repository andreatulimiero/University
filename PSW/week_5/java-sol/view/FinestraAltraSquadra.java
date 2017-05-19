package view;

import javax.swing.JOptionPane;

public class FinestraAltraSquadra {
    private int n;

    public FinestraAltraSquadra() {
        n = JOptionPane.showConfirmDialog(
                null,
                "Vuoi procedere con l'altra squadra?",
                "",
                JOptionPane.YES_NO_OPTION);
    }

    public boolean attendiSelezione() {
        if (n == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }
}
