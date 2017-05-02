/**
 * Created by Tuly on 5/2/2017.
 */
public class Barriera {

    private int corridori;
    private int passed = 0;

    public Barriera(int corridori) {
        this.corridori = corridori;
    }

    public synchronized void notifyReady() {
        --corridori;
        while (corridori > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
    }

    public boolean isEveryoneReady() {
        return corridori == 0;
    }
}
