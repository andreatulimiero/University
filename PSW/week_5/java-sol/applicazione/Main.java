package applicazione;

import model.lega.squadra.Squadra;
import model.lega.giocatore.Giocatore;
import model.lega.TipoLinkPartita;
import model.lega.EccezionePrecondizioni;
import model.attivita.complesse.AttivitaPrincipale;
import controller.Controller;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // pattern MVC naive con singolo controller e view

        Set<TipoLinkPartita> model = initModel();
        Controller controller = new Controller();
        AttivitaPrincipale attivitaPrincipale = new AttivitaPrincipale(controller, model);

        Thread t = new Thread(attivitaPrincipale);
        t.start();
        try {
            t.join(); // attende il completamento dell'attivita' principale
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Set<TipoLinkPartita> initModel() {

        // Inizializzazione del diagramma UML con dati di test

        Set<TipoLinkPartita> partite = new HashSet<TipoLinkPartita>();

        Squadra verdi = new Squadra("Verdi");
        Squadra rossi = new Squadra("Rossi");
        Squadra gialli = new Squadra("Gialli");
        Squadra blu = new Squadra("Blu");

        try {

            TipoLinkPartita p = new TipoLinkPartita(verdi, 2, rossi, 0);
            verdi.inserisciLinkPartita(p);
            partite.add(p);

            p = new TipoLinkPartita(verdi, 3, blu, 1);
            verdi.inserisciLinkPartita(p);
            partite.add(p);

            p = new TipoLinkPartita(blu, 2, rossi, 0);
            blu.inserisciLinkPartita(p);
            partite.add(p);

            p = new TipoLinkPartita(gialli, 1, rossi, 3);
            rossi.inserisciLinkPartita(p);
            partite.add(p);

        } catch (EccezionePrecondizioni e) {
            e.printStackTrace();
        }

        // Crea ed aggiunge giocatori alle squadre
        // eta' casuale tra 1970 e 1980
        // (Math.random() resituisce un double casuale tra 0.0 e 1.0)
        for (int i = 0; i < 20; i++) {

            Giocatore g = new Giocatore("verde-" + i, 1970 + (int) (10 * Math.random()));
            verdi.insericiLinkGioca(g);

            g = new Giocatore("rosso-" + i, 1970 + (int) (10 * Math.random()));
            rossi.insericiLinkGioca(g);

            g = new Giocatore("giallo-" + i, 1970 + (int) (10 * Math.random()));
            gialli.insericiLinkGioca(g);

            g = new Giocatore("blu-" + i, 1970 + (int) (10 * Math.random()));
            blu.insericiLinkGioca(g);
        }

        return partite;
    }
}
