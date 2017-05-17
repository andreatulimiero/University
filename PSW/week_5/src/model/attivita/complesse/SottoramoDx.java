package model.attivita.complesse;

import model.lega.TipoLinkPartita;
import model.attivita.atomiche.CalcolaPartiteVinte;
import model._framework.TaskExecutor;

public class SottoramoDx implements Runnable {

    private TipoLinkPartita tipoLinkPartita;

    private int partiteVinteCasa, partiteVinteTrasferta;

    public SottoramoDx(TipoLinkPartita tipoLinkPartita) {
        this.tipoLinkPartita = tipoLinkPartita;
    }

    public int getPartiteVinteCasa() {
        return partiteVinteCasa;
    }

    public int getPartiteVinteTrasferta() {
        return partiteVinteTrasferta;
    }

    @Override
    public void run() {
        CalcolaPartiteVinte calcolaPartiteVinte = new CalcolaPartiteVinte(tipoLinkPartita);
        TaskExecutor.getInstance().perform(calcolaPartiteVinte);
        while (!calcolaPartiteVinte.estEseguita()) continue;
        partiteVinteCasa = calcolaPartiteVinte.getVinteCasa();
        partiteVinteTrasferta = calcolaPartiteVinte.getVinteTrasferta();
    }

}
