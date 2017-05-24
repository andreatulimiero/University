package controller;

import java.util.*;

public abstract class Controller implements model.attivita.atomiche.SegnaliIO {

    public enum QualeSquadra {
        IN_CASA,
        FUORI_CASA
    };

    @Override
    public abstract int selezionaPartita(List<String> partite);

    @Override
    public abstract String qualeSquadra();

    @Override
    public abstract boolean altraSquadra();

    @Override
    public void stampaReport(int vinteCasa, int vinteTrasferta, float mediaEtaCasa, float mediaEtaTrasferta) {

        String msg = "La squadra di casa ";
        if (mediaEtaCasa > -1) {
            msg += "ha un'eta' media di: " + mediaEtaCasa + " anni ed ";
        }
        msg += "ha vinto: " + vinteCasa + " partite\n";
        msg += "La squadra in trasferta ";
        if (mediaEtaTrasferta > -1) {
            msg += "ha un'eta' media di: " + mediaEtaTrasferta + " anni ed ";
        }
        msg += "ha vinto: " + vinteTrasferta + " partite\n";

        this.stampa(msg);
    }

    protected abstract void stampa(String msg);
}


