import java.util.Date;

/**
 * Created by Tuly on 2/12/2017.
 */
public class Main {

    public static void main(String[] args){

        Dipendente mario = new Dipendente("mario", "rossi");
        Dipendente luigi = new Dipendente("luigi", "bianchi");
        Dipendente riccardo = new Direttore("riccardo", "verdi", new Date());

        Office office = new Office("rome", "ffff00");
        office.addDipendente(mario);
        office.addDipendente(luigi);
        office.addDipendente(riccardo);

        Office cloned_office = (Office) office.clone();
        cloned_office.city = "milan";

        System.out.println(office.city);
        System.out.println(cloned_office.city);

//        System.out.println(office.getDipendenti().get(0).getName());
//        System.out.println(cloned_office.getDipendenti().size());
    }

}
