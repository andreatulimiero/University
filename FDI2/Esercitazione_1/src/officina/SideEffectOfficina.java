package officina;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tulim on 3/8/2017.
 */
public class SideEffectOfficina implements Officina {

    private static final int RIPARAZIONE = 0, PRONTO = 1;

    private String pi;
    private Set<MezzoInOfficina> mezzi;

    public SideEffectOfficina(String pi) {
        this.pi = pi;
        this.mezzi = new HashSet<>();
    }

    @Override
    public String partitaIva() {
        return pi;
    }

    @Override
    public void arrivaMezzo(Mezzo mezzo) {
        mezzi.add(new MezzoInOfficina(mezzo));
    }

    @Override
    public void approntaMezzo(Mezzo mezzo) {
        Iterator<MezzoInOfficina> i = mezzi.iterator();
        while (i.hasNext()) {
            MezzoInOfficina m = i.next();
            if (m.equals(mezzo)) m.setState(PRONTO);
        }
    }

    @Override
    public void parteMezzo(Mezzo mezzo) {
        Iterator<MezzoInOfficina> i = mezzi.iterator();
        while (i.hasNext()) {
            MezzoInOfficina m = i.next();
            if (m.equals(mezzo) && m.getState() == PRONTO) mezzi.remove(m);
        }
    }

    @Override
    public boolean estInRiparazione(Mezzo mezzo) {
        Iterator<MezzoInOfficina> i = mezzi.iterator();
        while (i.hasNext()) {
            MezzoInOfficina m = i.next();
            if (m.equals(mezzo) && m.getState() == RIPARAZIONE) return true;
        }
        return false;
    }

    @Override
    public boolean estPronto(Mezzo mezzo) {
        Iterator<MezzoInOfficina> i = mezzi.iterator();
        while (i.hasNext()) {
            MezzoInOfficina m = i.next();
            if (m.equals(mezzo) && m.getState() == PRONTO) return true;
        }
        return false;
    }

    @Override
    public ProtectedIterator<MezzoInOfficina> mezziInOfficina() {
        return new ProtectedIterator<>(mezzi.iterator());
    }

    class MezzoInOfficina {

        private int state;
        private Mezzo mezzo;

        public MezzoInOfficina(Mezzo mezzo) {
            this.state = RIPARAZIONE;
            this.mezzo = mezzo;
        }

        public int getState() {
            return state;
        }

        public Mezzo getMezzo() {
            return mezzo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;

            if (getClass() == o.getClass()) {
                MezzoInOfficina that = (MezzoInOfficina) o;

                return this == that;
            } else if (mezzo.getClass() == o.getClass()) {
                Mezzo that = (Mezzo) o;
                return mezzo.equals(that);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return mezzo.hashCode();
        }

        @Override
        public String toString() {
            return "MezzoInOfficina{" +
                    "state=" + state +
                    ", mezzo=" + mezzo +
                    '}';
        }

        public void setState(int state) {
            this.state = state;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SideEffectOfficina that = (SideEffectOfficina) o;

        return mezzi != null ? mezzi.equals(that.mezzi) : that.mezzi == null;
    }

    @Override
    public int hashCode() {
        return mezzi != null ? mezzi.hashCode() : 0;
    }
}
