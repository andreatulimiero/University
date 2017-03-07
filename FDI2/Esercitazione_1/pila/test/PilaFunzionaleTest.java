package it.uniroma1.dis.pila.test;

import it.uniroma1.dis.pila.PilaFunzionale;

public class PilaFunzionaleTest {

    public static void main(String[] args) {

        // crea una pila vuota
        PilaFunzionale<String> p1 = new PilaFunzionale<String>();
        // inserisce elementi nella pila
        PilaFunzionale<String> p2 = p1.push("A").push("B").push("C");
        // stampa la pila p2;
        PilaFunzionale<String> q = p2;
        while (!q.estVuota()) {
            System.out.println("Rimosso in testa: " + q.top());
            q = q.pop(); // non fa side-effect su p2
        }

        // creo altra pila
        PilaFunzionale<String> p3 = new PilaFunzionale<String>().push("A").push("B").push("C");
        if (p3.equals(p2))
            System.out.println("Le due pile sono uguali");
        else
            System.out.println("Le due pile non sono uguali");
    }

}
