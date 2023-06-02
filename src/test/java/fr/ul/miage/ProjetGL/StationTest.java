package fr.ul.miage.ProjetGL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class StationTest {
	
	@Test
    public void testEquals() {
        Station station1 = new Station("Station A", 48.8596, 2.3477, 1, 2);
        Station station2 = new Station("Station A", 48.8596, 2.3477, 1, 2);
        Station station3 = new Station("Station B", 48.8596, 2.3477, 1, 2);

        assertEquals(station1, station1);
        assertNotEquals(station1, station2);
        assertNotEquals(station1, station3);
    }

}
