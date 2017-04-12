package mattoncini;

/**
 * Created by Tuly on 4/11/2017.
 */
public abstract class Mattoncino {

    protected String dimension;
    protected String color;

    public Mattoncino(String dimension, String color) {
        this.dimension = dimension;
        this.color = color;
    }
}
