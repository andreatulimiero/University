package it.uniroma1.dis.pila.test;

import it.uniroma1.dis.pila.PilaSideEffect;

public class PilaSideEffectTest {

    public static void main(String[] args) {

        // crea una pila vuota
        PilaSideEffect<String> p1 = new PilaSideEffect<String>();
        // inserisce elementi nella pila
        p1.push("A");
        p1.push("B");
        p1.push("C");
        // stampa la pila
        while (!p1.estVuota()) {
            System.out.println("Rimosso in testa: " + p1.top());
            p1.pop(); // fa side-effect su p1
        }

        // creo altra pila
        PilaSideEffect<String> p3 = new PilaSideEffect<String>();
        p3.push("A");
        p3.push("B");
        p3.push("C");
        if (p3.equals(p1))
            System.out.println("Le due pile sono uguali");
        else
            System.out.println("Le due pile non sono uguali");
    }
}
