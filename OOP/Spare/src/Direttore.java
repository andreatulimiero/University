import java.util.Date;

/**
 * Created by Tuly on 2/12/2017.
 */
public class Direttore extends Dipendente{

    private Date nomination;


    public Direttore(String name, String surname, Date nomination) {
        super(name, surname);
        this.nomination = nomination;
    }
}
