package model.lega;

public class ManagerPartita {
    private TipoLinkPartita link;

    private ManagerPartita(TipoLinkPartita link) {
        this.link = link;
    }

    public TipoLinkPartita getLink() {
        return link;
    }

    public static void inserisci(TipoLinkPartita l) {
        if (l != null) {
            ManagerPartita m = new ManagerPartita(l);
            l.getCasa().inserisciCasaPerManagerPartita(m);
            l.getTrasferta().inserisciTrasfertaPerManagerPartita(m);
        }
    }

    public static void elimina(TipoLinkPartita l) {
        if (l != null) {
            ManagerPartita m = new ManagerPartita(l);
            l.getCasa().eliminaCasaPerManagerPartita(m);
            l.getTrasferta().eliminaTrasfertaPerManagerPartita(m);
        }
    }
}
