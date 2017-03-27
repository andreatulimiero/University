package it.uniroma1.dis.pila;

/*
 * Realizzazione di una pila con side-effect.
 * Operazione della struttura alterarano la struttura dati.
 */
public class PilaSideEffect<T> implements Cloneable {

	private Nodo<T> nodeInit;

	// pilaVuota
	public PilaSideEffect() {
		nodeInit = null;
	}

	public boolean estVuota() {
		return nodeInit == null;
	}

	public void push(T o) {
		Nodo<T> aux = new Nodo<T>();
		aux.info = o;
		aux.next = nodeInit;
		nodeInit = aux;
	}

	public void pop() {
		// verifica precondizioni
		if (estVuota()) {
			throw new RuntimeException("Empty stack. Invalid operation!");
		} else {
			nodeInit = nodeInit.next;
		}
	}

	public Object top() {

		// verifica precondizioni
		if (estVuota()) {
			throw new RuntimeException("Pila: top applicato ad una pila vuota");
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

		PilaSideEffect<T> p = (PilaSideEffect<T>) o;
		Nodo<T> n1 = nodeInit;
		Nodo<T> n2 = p.nodeInit;
		while (n1 != null && n2 != null) {

			// verifico che le informazioni contenute nei due elementi
			// siano efftivamente uguali. Non confronto i loro indirizzi.
			if (!n1.info.equals(n2.info)) {
				return false;
			}

			n1 = n1.next;
			n2 = n2.next;
		}

		// verifico che tutti gli elementi
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

	@Override
	public Object clone() {
		try {
			PilaSideEffect<T> pilaClonata = (PilaSideEffect<T>) super.clone();
			if (!estVuota()) {
				Nodo<T> iterator = nodeInit;
				Nodo<T> nodoClone = new Nodo<T>();
				pilaClonata.nodeInit = nodoClone;
				while (iterator != null) {
					nodoClone.info = iterator.info;
					iterator = iterator.next;
					if (iterator != null) {
						nodoClone.next = new Nodo<T>();
						nodoClone = nodoClone.next;
					} else {
						nodoClone.next = null;
					}
				}
			}
			return pilaClonata;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}
