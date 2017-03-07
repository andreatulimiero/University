package list;

/*
 * Realizzazione di una pila con approaccio funzionale.
 * Ogni operazione della struttura dati
 * non effettua side-effect sulla struttura dati.
 */
public class PilaFunzionale<T> {

    // riferimento al primo node nella pila
    private Node<T> nodeInit;

    // PilaVuota
    public PilaFunzionale() {
        nodeInit = null;
    }

    public boolean estVuota() {
        return nodeInit == null;
    }

    public PilaFunzionale<T> push(T o) {

        // crea elemento
        Node<T> aux = new Node<T>();
        aux.info = o;
        aux.next = nodeInit;

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
        return new PilaFunzionale<T>(nodeInit.next);
    }

    public T top() {

        // verifica precondizioni
        if (estVuota()) {
            throw new RuntimeException("PilaF: top applicato ad una pila vuota");
        } else {
            return nodeInit.info;
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
        Node<T> n1 = nodeInit;
        Node<T> n2 = p.nodeInit;
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

        if (nodeInit != null || nodeInit.info != null) {
            // implementazione non ottimale
            return nodeInit.info.hashCode();
        } else {
            return 0;
        }
    }


    // costruttore privato
    private PilaFunzionale(Node<T> n) {
        nodeInit = n;
    }
    
}
