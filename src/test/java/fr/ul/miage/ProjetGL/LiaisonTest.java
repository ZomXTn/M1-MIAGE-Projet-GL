package fr.ul.miage.ProjetGL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LiaisonTest {

	@Test
	public void testEquals() {
		Station destination = new Station("Station B", 1, 2, 3, 4);
		Liaison liaison1 = new Liaison(destination, 5, 2);
		Liaison liaison2 = new Liaison(destination, 5, 2);
		Liaison liaison3 = new Liaison(new Station("Station C", 0, 3, 4, 5), 10, 3);

		assertEquals(liaison1, liaison1);
		assertNotEquals(liaison1, liaison2);
		assertNotEquals(liaison1, liaison3);
	}

	@Test
	public void testCompareTo() {
		Station destination = new Station("Station B", 1, 2, 3, 4);
		Liaison liaison1 = new Liaison(destination, 5, 2);
		Liaison liaison2 = new Liaison(destination, 10, 3);

		assertTrue(liaison1.compareTo(liaison2) < 0);
		assertTrue(liaison2.compareTo(liaison1) > 0);
		assertEquals(0, liaison1.compareTo(liaison1));
	}

}
