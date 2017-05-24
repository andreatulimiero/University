package model.attivita.complesse;

<<<<<<< HEAD
import model.lega.TipoLinkPartita;
import model.attivita.atomiche.SegnaliIO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class AttivitaPrincipale implements Runnable {
=======
import controller.Controller;
import model.lega.TipoLinkPartita;

import java.util.*;

public class AttivitaPrincipale implements Runnable {

    private Controller controller;
    private Set<TipoLinkPartita> model;

    public AttivitaPrincipale(Controller controller, Set<TipoLinkPartita> model) {
        this.controller = controller;
        this.model = model;
    }

    private ArrayList<String> getPartitePerNome(ArrayList<TipoLinkPartita> partiteList) {
        ArrayList<String> partitePerNomeList = new ArrayList<>();
        for (int i = 0; i < partiteList.size(); i++)
            partitePerNomeList.add(partiteList.get(i).getCasa().getNome() + " - " + partiteList.get(i).getTrasferta().getNome());
        return partitePerNomeList;
    }

    @Override
    public void run() {
        ArrayList<TipoLinkPartita> partiteList = new ArrayList<>(model);
        List<String> nomiPartite = getPartitePerNome(partiteList);
        int partitaScelta = controller.selezionaPartita(nomiPartite);
        if (partitaScelta == -1) return;
        SottoramoSx sottoramoSx = new SottoramoSx(partiteList.get(partitaScelta), controller);
        SottoramoDx sottoramoDx = new SottoramoDx(partiteList.get(partitaScelta));

        Thread sottoramoSxThread = new Thread(sottoramoSx);
        Thread sottoramoDxThread = new Thread(sottoramoDx);

        sottoramoSxThread.start();
        sottoramoDxThread.start();

        try {
            sottoramoSxThread.join();
            sottoramoDxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        controller.stampaReport(sottoramoDx.getPartiteVinteCasa(), sottoramoDx.getPartiteVinteTrasferta(),
                sottoramoSx.getEtaMediaCasa(), sottoramoSx.getEtaMediaTrasferta());
    }

>>>>>>> 47e16e0c86b7e440abff24e4249635d80eb0911b
}
