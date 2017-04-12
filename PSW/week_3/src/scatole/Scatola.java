package scatole;

/**
 * Created by Tuly on 4/11/2017.
 */
public abstract class Scatola {

    protected String description;
    protected int quantity;

    public Scatola(String description, int quantity) {
        this.description = description;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

}
