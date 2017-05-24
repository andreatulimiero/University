package model.attivita.complesse;

import model.lega.squadra.Squadra;
import model.lega.TipoLinkPartita;
import model.attivita.atomiche.SegnaliIO;
import model.attivita.atomiche.CalcolaEtaMedia;
import model._framework.TaskExecutor;

public class SottoramoSx implements Runnable {
    private boolean eseguita = false;
    private float mediaEtaCasa = -1;
    private float mediaEtaTrasferta = -1;
    private TipoLinkPartita partita;

    private SegnaliIO segnaliIO;

    public SottoramoSx(TipoLinkPartita partita, SegnaliIO segnaliIO) {
        this.partita = partita;
        this.segnaliIO = segnaliIO;
    }

    public synchronized void run() {

        if (eseguita)
            return;

        eseguita = true;

        Squadra squadraSelezionata = null;
        boolean altra = false;

        String scelta = segnaliIO.qualeSquadra();
        if (scelta.equals("casa"))
            squadraSelezionata = partita.getCasa();
        else
            squadraSelezionata = partita.getTrasferta();

        CalcolaEtaMedia cem = new CalcolaEtaMedia(squadraSelezionata);
        TaskExecutor.getInstance().perform(cem);
        if (squadraSelezionata == partita.getCasa()) {// E' stata selezionata la squadra in casa
            mediaEtaCasa = cem.getRisultato();
        } else {// E' stata selezionata la squadra in trasferta
            mediaEtaTrasferta = cem.getRisultato();
        }

        altra = segnaliIO.altraSquadra();
        if (altra) {
            if (squadraSelezionata == partita.getCasa()) {// era stata selezionata la squadra di casa
                // dobbiamo calcolare l'eta' media della squadra in trasferta
                CalcolaEtaMedia cem2 = new CalcolaEtaMedia(partita.getTrasferta());
                TaskExecutor.getInstance().perform(cem2);
                mediaEtaTrasferta = cem2.getRisultato();
            } else {// era stata selezionata la squadra in trasferta
                // dobbiamo calcolare l'eta' media della squadra in casa
                CalcolaEtaMedia cem2 = new CalcolaEtaMedia(partita.getCasa());
                TaskExecutor.getInstance().perform(cem2);
                mediaEtaCasa = cem2.getRisultato();
            }
        }

    }

    public synchronized float getMediaEtaCasa() {
        if (!eseguita)
            throw new RuntimeException("Il risultato non e' ancora pronto!");
        return mediaEtaCasa;
    }

    public synchronized float getMediaEtaTrasferta() {
        if (!eseguita)
            throw new RuntimeException("Il risultato non e' ancora pronto!");
        return mediaEtaTrasferta;
    }

    public synchronized boolean estEseguita() {
        return eseguita;
    }
}
