package it.uniroma1.dis.pila;

/*
 * Realizzazione di una pila con approaccio funzionale.
 * Ogni operazione della struttura dati
 * non effettua side-effect sulla struttura dati.
 */
public class PilaFunzionale<T> {

    // riferimento al primo node nella pila
    private Nodo<T> nodoInit;

    // PilaVuota
    public PilaFunzionale() {
        nodoInit = null;
    }

    public boolean estVuota() {
        return nodoInit == null;
    }

    public PilaFunzionale<T> push(T o) {

        // crea elemento
        Nodo<T> aux = new Nodo<T>();
        aux.info = o;
        aux.next = nodoInit;

        // ritorno una nuovo oggetto PilaFunzionale
        // sfruttando construttore privato
        return new PilaFunzionale<T>(aux);
    }

    public PilaFunzionale<T> pop() {

        // verifica precondizioni
        if (estVuota()) {
            throw new RuntimeException("Empty stack. Invalid operation!");
        }

        // ritorno una nuovo oggetto PilaFunzionale
        // sfruttando construttore privato
        return new PilaFunzionale<T>(nodoInit.next);
    }

    public T top() {

        // verifica precondizioni
        if (estVuota()) {
            throw new RuntimeException("PilaF: top applicato ad una pila vuota");
        } else {
            return nodoInit.info;
        }
    }

    @Override
    public boolean equals(Object o) {

        // Due pila sono uguale se contegono le stesse informationi

        // test necessario per evitare Exceptions nei successivi test
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        PilaFunzionale<T> p = (PilaFunzionale<T>) o;
        Nodo<T> n1 = nodoInit;
        Nodo<T> n2 = p.nodoInit;
        while (n1 != null && n2 != null) {

            // verifico che le informazioni contenute nei due elementi
            // siano efftivamente uguali. Non confronto i loro indirizzi.
            if (!n1.info.equals(n2.info)) {
                return false;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        // verifico  che tutti gli elementi
        // delle due pile sono stati controllati
        return (n1 == null && n2 == null);
    }

    @Override
    public int hashCode() {

        if (nodoInit != null || nodoInit.info != null) {
            // implementazione non ottimale
            return nodoInit.info.hashCode();
        } else {
            return 0;
        }
    }


    // costruttore privato
    private PilaFunzionale(Nodo<T> n) {
        nodoInit = n;
    }
    
}
