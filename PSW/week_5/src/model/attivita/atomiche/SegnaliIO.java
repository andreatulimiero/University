package model.attivita.atomiche;

import java.util.List;

public interface SegnaliIO {

    public int selezionaPartita(List<String> partite);

    public String qualeSquadra();

    public boolean altraSquadra();

    public void stampaReport(int vinteCasa, int vinteTrasferta, float mediaEtaCasa, float mediaEtaTrasferta);
}
