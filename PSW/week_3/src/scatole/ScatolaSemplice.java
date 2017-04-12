package scatole;

import mattoncini.MattoncinoSemplice;

import java.util.ArrayList;

/**
 * Created by Tuly on 4/11/2017.
 */
public class ScatolaSemplice extends Scatola {

    private ArrayList<MattoncinoSemplice> mattonciniList;

    public ScatolaSemplice(String desc, int quant) {
        super(desc, quant);

        mattonciniList = new ArrayList<>();
    }

    public void addMattoncinoSemplice(MattoncinoSemplice mattoncinoSemplice) {
        if (mattoncinoSemplice == null) return;
        mattonciniList.add(mattoncinoSemplice);
    }

    public void removeMattoncinoSemplice(MattoncinoSemplice mattoncinoSemplice){
        if (mattoncinoSemplice == null) return;
        mattonciniList.remove(mattoncinoSemplice);
    }

    public ArrayList<MattoncinoSemplice> getMattonciniList() {
        return (ArrayList<MattoncinoSemplice>) mattonciniList.clone();
    }
}
