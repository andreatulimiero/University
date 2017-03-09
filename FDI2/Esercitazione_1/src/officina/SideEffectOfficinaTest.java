package officina;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tulim on 3/8/2017.
 */
public class SideEffectOfficinaTest {

    Officina officina;
    Mezzo auto;
    Mezzo camion;

    @Before
    public void setUp() throws Exception {
        officina = new SideEffectOfficina("039874623910387");
        auto = new Auto("Renault", "Clio", "grigio", "AA000AA");
        camion = new Camion("IVECO", "Stralis", "rosso", true);
    }

    @Test
    public void partitaIva() throws Exception {
        assertEquals("039874623910387", officina.partitaIva());
    }

    @Test
    public void arrivaMezzo() throws Exception {
        officina.arrivaMezzo(auto);

        assertEquals(true, officina.estInRiparazione(auto));
    }

    @Test
    public void approntaMezzo() throws Exception {
        officina.arrivaMezzo(auto);
        officina.approntaMezzo(auto);

        assertEquals(true, officina.estPronto(auto));
    }

    @Test
    public void parteMezzo() throws Exception {
        officina.arrivaMezzo(auto);
        officina.parteMezzo(auto);

        assertTrue(officina.estInRiparazione(auto));

        officina.approntaMezzo(auto);
        officina.parteMezzo(auto);

        assertFalse(officina.estInRiparazione(auto));
    }

    @Test
    public void mezziInOfficina() throws Exception {
        officina.arrivaMezzo(auto);
        officina.arrivaMezzo(camion);
        List<SideEffectOfficina.MezzoInOfficina> mezziOfficina = new ArrayList<>();
        List<SideEffectOfficina.MezzoInOfficina> mezziOfficinaAfter = new ArrayList<>();

        ProtectedIterator<SideEffectOfficina.MezzoInOfficina> iterator = officina.mezziInOfficina();
        while (iterator.hasNext()){
            mezziOfficina.add(iterator.next());
            iterator.remove();
        }
        iterator = officina.mezziInOfficina();
        while (iterator.hasNext()){
            mezziOfficinaAfter.add(iterator.next());
        }

        assertArrayEquals(mezziOfficina.toArray(), mezziOfficinaAfter.toArray());
    }

}