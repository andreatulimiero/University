import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tuly on 2/12/2017.
 */
public class Office implements Cloneable{

    public String city, code;
    private List<Dipendente> dipendenti;


    public Office(String city, String code) {
        this.city = city;
        this.code = code;
        dipendenti = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public List<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void addDipendente(Dipendente dipendente){
        dipendenti.add(dipendente);
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
