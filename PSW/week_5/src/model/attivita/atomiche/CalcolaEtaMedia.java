package model.attivita.atomiche;

import model.lega.EccezioneMolteplicita;
import model.lega.squadra.Squadra;
import model.lega.giocatore.Giocatore;
import model._framework.Task;

import java.util.*;

public class CalcolaEtaMedia implements Task {

    private Squadra squadra;
    private float media;
    private boolean finished;

    public CalcolaEtaMedia(Squadra squadra) {
        this.squadra = squadra;
    }

    public float getEtaMedia() {
        if (!finished) throw new RuntimeException("Not yet finished operation");
        return media;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void esegui() {
        if (finished) return;
        Set<Giocatore> linkGioca = null;
        try {
            linkGioca = squadra.getLinkGioca();
            for (Giocatore giocatore : linkGioca) {
                media += GregorianCalendar.getInstance().get(Calendar.YEAR) - giocatore.getAnnoNascita();
            }
            media /= linkGioca.size();
            finished = true;
        } catch (EccezioneMolteplicita eccezioneMolteplicita) {
            eccezioneMolteplicita.printStackTrace();
        }
    }

}