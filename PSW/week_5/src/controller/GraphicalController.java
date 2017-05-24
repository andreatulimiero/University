package controller;

import view.graphical.FinestraAltraSquadra;
import view.graphical.FinestraQualeSquadra;
import view.graphical.FinestraSelezionaPartita;
import view.graphical.StampaASchermo;

import java.util.HashMap;
import java.util.List;

public class GraphicalController extends Controller {

    @Override
    public int selezionaPartita(List<String> partite) {

        HashMap<Integer, String> partite_show = new HashMap<Integer, String>();
        int i = 0;
        for (String tlp : partite) {
            partite_show.put(i, tlp);
            i++;
        }

        FinestraSelezionaPartita f = new FinestraSelezionaPartita(partite_show);
        int risultato = f.attendiSelezione(); // variabile risultato necessaria per chiudere la finestra
        f.dispose(); // chiude la finestra

        return risultato;
    }

    @Override
    public String qualeSquadra() {

        FinestraQualeSquadra f = new FinestraQualeSquadra();
        QualeSquadra res = f.attendiSelezione();
        if (res == QualeSquadra.IN_CASA)
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
    protected void stampa(String msg) {
        StampaASchermo.stampa(msg);
    }
}
