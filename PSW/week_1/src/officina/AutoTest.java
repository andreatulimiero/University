package officina;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tulim on 3/8/2017.
 */
public class AutoTest {
    @Test
    public void getNumeroRuote() throws Exception {
        Mezzo auto = new Auto("Renault", "Clio", "grigio antracite", "CY868YS");
        assertEquals(4, auto.getNumeroRuote());
    }

}