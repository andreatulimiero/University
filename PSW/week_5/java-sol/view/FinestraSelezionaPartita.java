package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public final class FinestraSelezionaPartita extends JFrame implements ActionListener {

    private final String titolo = "Partite";

    private Hashtable<JButton, Integer> tabella = new Hashtable<JButton, Integer>();

    private boolean fatto = false;
    private int partitaSelezionata;

    public FinestraSelezionaPartita(Map<Integer, String> infoPartite) {
        super();
        setTitle(titolo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        for (Map.Entry<Integer, String> c : infoPartite.entrySet()) {
            JButton b = new JButton(c.getValue());
            b.addActionListener(this);// Associa la classe corrente come ActionListener del pulsante
            getContentPane().add(b);
            tabella.put(b, c.getKey());
        }
        //Bottone per uscire dal ciclo
        JButton b = new JButton("Nessuna partita");
        tabella.put(b, -1);
        b.addActionListener(this);// Associa la classe corrente come ActionListener del pulsante
        getContentPane().add(b);
        pack();
        setVisible(true);
    }

    public int attendiSelezione() {
        synchronized (this) {
            while (!fatto) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Nasconde il JFrame prima di restituire la selezione dell'utente
                setVisible(false);
                return partitaSelezionata;
            }
        }
        return -1; // non e' mai eseguita
    }

    // Realizzazione dell'ActionListener
    public void actionPerformed(ActionEvent actionEvent) {// Implementa il listener della finestra
        synchronized (this) {
            fatto = true;
            partitaSelezionata = tabella.get(actionEvent.getSource());
            this.notify();
        }
    }
}



