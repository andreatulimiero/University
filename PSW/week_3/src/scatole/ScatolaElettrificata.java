package scatole;

import mattoncini.MattoncinoElettrificato;

import java.util.ArrayList;

/**
 * Created by Tuly on 4/11/2017.
 */
public class ScatolaElettrificata extends Scatola {

    private int difficulty;
    private ArrayList<MattoncinoElettrificato> mattonciniList;

    public ScatolaElettrificata(String desc, int quant, int difficulty) {
        super(desc, quant);

        this.difficulty = difficulty;
        mattonciniList = new ArrayList<>();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void addMattoncinoElettrificato(MattoncinoElettrificato mattoncinoElettrificato) {
        if (mattoncinoElettrificato == null) return;
        mattonciniList.add(mattoncinoElettrificato);
    }

    public void removeMattoncinoElettrificato(MattoncinoElettrificato mattoncinoElettrificato) {
        if (mattoncinoElettrificato == null) return;
        mattonciniList.remove(mattoncinoElettrificato);
    }

    public ArrayList<MattoncinoElettrificato> getMattonciniList() {
        return (ArrayList<MattoncinoElettrificato>) mattonciniList.clone();
    }
}
