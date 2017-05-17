package model.lega;

import model.lega.squadra.Squadra;

public class TipoLinkPartita {
    private final int goalCasa;
    private final int goalTrasferta;
    private final Squadra casa;
    private final Squadra trasferta;

    public TipoLinkPartita(Squadra casa, int goalCasa, Squadra trasferta, int goalTrasferta)
            throws EccezionePrecondizioni {

        if (casa == null || trasferta == null || casa.equals(trasferta)) {
            throw new EccezionePrecondizioni("Oggetti non inizializzati!");
        }

        this.casa = casa;
        this.trasferta = trasferta;
        this.goalCasa = goalCasa;
        this.goalTrasferta = goalTrasferta;
    }

    // Astrazione di tipo: ridefiniamo equals
    public boolean equals(Object o) {
        if (o != null && getClass().equals(o.getClass())) {
            TipoLinkPartita l = (TipoLinkPartita) o;
            return (casa == l.casa && trasferta == l.trasferta);
        }
        return false;
    }

    // equals() ridefinito -> dobbiamo ridefinire hashCode()
    public int hashCode() {
        return casa.hashCode() + trasferta.hashCode();
    }

    public Squadra getCasa() {
        return casa;
    }

    public Squadra getTrasferta() {
        return trasferta;
    }

    public int getGoalCasa() {
        return goalCasa;
    }

    public int getGoalTrasferta() {
        return goalTrasferta;
    }

}
