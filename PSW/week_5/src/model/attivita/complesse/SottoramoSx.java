package model.attivita.complesse;

import controller.Controller;
import model._framework.Task;
import model._framework.TaskExecutor;
import model.attivita.atomiche.CalcolaEtaMedia;
import model.lega.TipoLinkPartita;
import model.lega.squadra.Squadra;

public class SottoramoSx implements Runnable {

    private TipoLinkPartita partita;
    private Controller controller;

    private float etaMediaCasa, etaMediaTrasferta;

    public SottoramoSx(TipoLinkPartita partita, Controller controller) {
        this.partita = partita;
        this.controller = controller;
    }

    public float getEtaMediaCasa() {
        return etaMediaCasa;
    }

    public float getEtaMediaTrasferta() {
        return etaMediaTrasferta;
    }

    @Override
    public void run() {
        String qualeSquadra = controller.qualeSquadra();

        Squadra squadra = qualeSquadra.equals("casa") ? partita.getCasa() : partita.getTrasferta();

        CalcolaEtaMedia calcolaEtaMedia = new CalcolaEtaMedia(squadra);
        TaskExecutor.getInstance().perform(calcolaEtaMedia);
        while (!calcolaEtaMedia.isFinished()) continue;
        float etaMedia = calcolaEtaMedia.getEtaMedia();

        if (qualeSquadra.equals("casa"))
            etaMediaCasa = etaMedia;
        else
            etaMediaTrasferta = etaMedia;

        if (controller.altraSquadra()) {
            squadra = !qualeSquadra.equals("casa") ? partita.getCasa() : partita.getTrasferta();

            calcolaEtaMedia = new CalcolaEtaMedia(squadra);
            TaskExecutor.getInstance().perform(calcolaEtaMedia);
            while (!calcolaEtaMedia.isFinished()) continue;
            etaMedia = calcolaEtaMedia.getEtaMedia();

            if (!qualeSquadra.equals("casa"))
                etaMediaCasa = etaMedia;
            else
                etaMediaTrasferta = etaMedia;
        }

    }

}
