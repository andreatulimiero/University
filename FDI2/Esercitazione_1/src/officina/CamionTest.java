package officina;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tulim on 3/8/2017.
 */
public class CamionTest {

    @Test
    public void getNumeroRuote() throws Exception {
        Mezzo camion = new Camion("IVECO", "Stralis", "rosso", true);
        assertEquals(8, camion.getNumeroRuote());
        Mezzo furgone = new Camion("IVECO", "Daily", "blue", false);
        assertEquals(4, furgone.getNumeroRuote());
    }

}