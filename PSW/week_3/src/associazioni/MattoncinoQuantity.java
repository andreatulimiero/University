package associazioni;

import mattoncini.Mattoncino;

/**
 * Created by Tuly on 4/11/2017.
 */
public class MattoncinoQuantity {

    private Mattoncino mattoncino;
    private int quantity;

    public MattoncinoQuantity(Mattoncino mattoncino, int quantity) {
        this.mattoncino = mattoncino;
        this.quantity = quantity;
    }

    public Mattoncino getMattoncino() {
        return mattoncino;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MattoncinoQuantity that = (MattoncinoQuantity) o;

        return mattoncino.equals(that.mattoncino);

    }

    @Override
    public int hashCode() {
        return mattoncino.hashCode();
    }
}
