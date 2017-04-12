package costruzioni;

/**
 * Created by Tuly on 4/11/2017.
 */
public abstract class Costruzione {

    private String instructions;

    public Costruzione(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }
}
