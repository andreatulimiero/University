import java.util.Random;

public class Corridore implements Runnable {

    private String name;
    private Barriera barriera;

    public Corridore(String name, Barriera barriera) {
        this.name = name;
        this.barriera = barriera;
    }

    @Override
    public void run() {
        barriera.notifyReady();
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " finished");
    }

}
