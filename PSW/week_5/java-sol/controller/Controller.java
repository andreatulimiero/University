package controller;

import model.lega.squadra.Squadra;
import model.lega.TipoLinkPartita;

import java.util.*;

import javax.swing.*;

import view.FinestraAltraSquadra;
import view.FinestraQualeSquadra;
import view.FinestraSelezionaPartita;
import view.StampaASchermo;

public class Controller implements model.attivita.atomiche.SegnaliIO {

    @Override
    public int selezionaPartita(List<String> partite) {

        HashMap<Integer, String> partite_show = new HashMap<Integer, String>();
        int i = 0;
        for (String tlp : partite) {
            partite_show.put(i, tlp);
            i++;
        }

        // Task LeggiPartita
        FinestraSelezionaPartita f = new FinestraSelezionaPartita(partite_show);
        int risultato = f.attendiSelezione(); // variabile risultato necessaria per chiudere la finestra
        f.dispose();// chiude la finestra
        return risultato;
    }

    @Override
    public String qualeSquadra() {

        FinestraQualeSquadra f = new FinestraQualeSquadra();
        FinestraQualeSquadra.QualeSquadra res = f.attendiSelezione();
        if (res == FinestraQualeSquadra.QualeSquadra.IN_CASA)
            return "casa";
        else
            return "transferta";
    }

    @Override
    public boolean altraSquadra() {
        FinestraAltraSquadra f = new FinestraAltraSquadra();
        return f.attendiSelezione();
    }

    @Override
    public void stampa(int vinteCasa, int vinteTrasferta, float mediaEtaCasa, float mediaEtaTrasferta) {

        StampaASchermo.stampa("La squadra di casa ");
        if (mediaEtaCasa > -1) {
            StampaASchermo.stampa("ha un'eta' media di: " + mediaEtaCasa + " anni ed ");
        }
        StampaASchermo.stampaLinea("ha vinto: " + vinteCasa + " partite");
        StampaASchermo.stampa("La squadra in trasferta ");
        if (mediaEtaTrasferta > -1) {
            StampaASchermo.stampa("ha un'eta' media di: " + mediaEtaTrasferta + " anni ed ");
        }
        StampaASchermo.stampaLinea("ha vinto: " + vinteTrasferta + " partite");
    }
}


