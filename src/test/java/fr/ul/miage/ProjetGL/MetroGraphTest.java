package fr.ul.miage.ProjetGL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class MetroGraphTest {

	@Test
	public void testAjouterStation() {
		MetroGraph metroGraph = new MetroGraph();
		Station station = new Station("Station A", 0.0, 0.0, 1, 5);
		metroGraph.ajouterStation(station);
		assertTrue(metroGraph.getListeAdjacence().containsKey(station));
	}

	@Test
	public void testAjouterLiaison() {
		MetroGraph metroGraph = new MetroGraph();
		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterLiaison(stationA, stationB, 10);
		List<Liaison> liaisons = metroGraph.getListeAdjacence().get(stationA);
		assertEquals(1, liaisons.size());
		Liaison liaison = liaisons.get(0);
		assertEquals(stationB, liaison.getDestination());
		assertEquals(10, liaison.getTempsParcours());
	}

	@Test
	public void testAjouterPerturbationParLiaison() {
		MetroGraph metroGraph = new MetroGraph();
		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterLiaison(stationA, stationB, 10);

		Liaison liaison = metroGraph.getListeAdjacence().get(stationA).get(0);
		metroGraph.ajouterPerturbationParLiaison(stationA, liaison);

		assertTrue(liaison.isEstPerturbee());
		assertEquals(1, metroGraph.getPerturbationsEnCours().size());
	}

	@Test
	public void testAjouterPerturbationParStation() {
		MetroGraph metroGraph = new MetroGraph();
		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 2, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterLiaison(stationA, stationB, 10);
		metroGraph.ajouterLiaison(stationA, stationC, 15);

		metroGraph.ajouterPerturbationParStation(stationA);

		assertTrue(metroGraph.getPerturbationsEnCours().size() > 0);
		for (Perturabtion perturbation : metroGraph.getPerturbationsEnCours()) {
			assertTrue(perturbation.getListeLiaisonPerturbes().stream().allMatch(Liaison::isEstPerturbee));
		}
	}

	@Test
	public void testFindShortestPath() {
		MetroGraph metroGraph = new MetroGraph();
		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 1, 5);
		Station stationD = new Station("Station D", 0.0, 0.0, 1, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterStation(stationD);
		metroGraph.ajouterLiaison(stationA, stationB, 10);
		metroGraph.ajouterLiaison(stationB, stationC, 5);
		metroGraph.ajouterLiaison(stationC, stationD, 8);

		List<Station> shortestPath = metroGraph.findShortestPath(stationA, stationD);
		assertEquals(4, shortestPath.size());
		assertEquals(stationA, shortestPath.get(0));
		assertEquals(stationB, shortestPath.get(1));
		assertEquals(stationC, shortestPath.get(2));
		assertEquals(stationD, shortestPath.get(3));
	}

	@Test
	public void testFindShortestPath_NoPath() {
		MetroGraph metroGraph = new MetroGraph();
		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 1, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterLiaison(stationA, stationB, 10);

		List<Station> shortestPath = metroGraph.findShortestPath(stationA, stationC);
		assertNull(shortestPath);
	}

	@Test
	public void testFindShortestPathWithLeastTransfers() {
		MetroGraph metroGraph = new MetroGraph();

		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 2, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 1, 5);
		Station stationD = new Station("Station D", 0.0, 0.0, 2, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterStation(stationD);
		metroGraph.ajouterLiaison(stationA, stationB, 10);
		metroGraph.ajouterLiaison(stationB, stationC, 5);
		metroGraph.ajouterLiaison(stationC, stationD, 8);

		List<Station> shortestPath = metroGraph.findShortestPathWithLeastTransfers(stationA, stationD);
		assertEquals(4, shortestPath.size());
		assertEquals(stationA, shortestPath.get(0));
		assertEquals(stationB, shortestPath.get(1));
		assertEquals(stationC, shortestPath.get(2));
		assertEquals(stationD, shortestPath.get(3));
	}

	@Test
	public void testFindShortestPathWithLeastTransfers_NoPath() {
		MetroGraph metroGraph = new MetroGraph();

		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 2, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 1, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterLiaison(stationA, stationB, 10);

		List<Station> shortestPath = metroGraph.findShortestPathWithLeastTransfers(stationA, stationC);
		assertNull(shortestPath);
	}

	@Test
	public void testFindPathDFS() {
		MetroGraph metroGraph = new MetroGraph();

		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 2, 5);
		Station stationD = new Station("Station D", 0.0, 0.0, 2, 5);
		Station stationE = new Station("Station E", 0.0, 0.0, 3, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterStation(stationD);
		metroGraph.ajouterStation(stationE);
		metroGraph.ajouterLiaison(stationA, stationB, 10);
		metroGraph.ajouterLiaison(stationB, stationC, 5);
		metroGraph.ajouterLiaison(stationC, stationD, 8);
		metroGraph.ajouterLiaison(stationD, stationE, 6);

		List<Station> intermediateStations = new ArrayList<>();
		intermediateStations.add(stationC);

		List<Station> path = metroGraph.findPathDFS(stationA, stationE, intermediateStations);
		assertEquals(5, path.size());
		assertEquals(stationA, path.get(0));
		assertEquals(stationB, path.get(1));
		assertEquals(stationC, path.get(2));
		assertEquals(stationD, path.get(3));
	}

	@Test
	public void testFindPathDFS_NoPath() {
		MetroGraph metroGraph = new MetroGraph();

		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 2, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterLiaison(stationA, stationB, 10);

		List<Station> intermediateStations = new ArrayList<>();
		intermediateStations.add(stationC);

		List<Station> path = metroGraph.findPathDFS(stationA, stationC, intermediateStations);
		assertNull(path);
	}

	@Test
	public void testDfs() {
		MetroGraph metroGraph = new MetroGraph();

		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 2, 5);
		Station stationD = new Station("Station D", 0.0, 0.0, 2, 5);
		Station stationE = new Station("Station E", 0.0, 0.0, 3, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterStation(stationD);
		metroGraph.ajouterStation(stationE);
		metroGraph.ajouterLiaison(stationA, stationB, 10);
		metroGraph.ajouterLiaison(stationB, stationC, 5);
		metroGraph.ajouterLiaison(stationC, stationD, 8);
		metroGraph.ajouterLiaison(stationD, stationE, 6);

		Set<Station> visited = new HashSet<>();
		List<Station> currentPath = new ArrayList<>();
		currentPath.add(stationA);

		List<Station> path = metroGraph.dfs(stationA, stationE, new ArrayList<>(), visited, currentPath);
		assertEquals(5, path.size());
		assertEquals(stationA, path.get(0));
		assertEquals(stationB, path.get(1));
		assertEquals(stationC, path.get(2));
		assertEquals(stationD, path.get(3));
		assertEquals(stationE, path.get(4));
	}

	@Test
	public void testDfs_NoPath() {
		MetroGraph metroGraph = new MetroGraph();

		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 0.0, 0.0, 1, 5);
		Station stationC = new Station("Station C", 0.0, 0.0, 2, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterLiaison(stationA, stationB, 10);

		Set<Station> visited = new HashSet<>();
		List<Station> currentPath = new ArrayList<>();
		currentPath.add(stationA);

		List<Station> path = metroGraph.dfs(stationA, stationC, new ArrayList<>(), visited, currentPath);
		assertNull(path);
	}

	@Test
	public void testTrouverStationPlusProche() {
		MetroGraph metroGraph = new MetroGraph();

		Station stationA = new Station("Station A", 0.0, 0.0, 1, 5);
		Station stationB = new Station("Station B", 1.0, 1.0, 1, 5);
		Station stationC = new Station("Station C", -1.0, -1.0, 1, 5);
		Station stationD = new Station("Station D", 2.0, 2.0, 2, 5);
		metroGraph.ajouterStation(stationA);
		metroGraph.ajouterStation(stationB);
		metroGraph.ajouterStation(stationC);
		metroGraph.ajouterStation(stationD);

		Station stationPlusProche = metroGraph.trouverStationPlusProche(0.1, 0.1);
		assertEquals(stationA, stationPlusProche);

		stationPlusProche = metroGraph.trouverStationPlusProche(1.1, 1.1);
		assertEquals(stationB, stationPlusProche);

		stationPlusProche = metroGraph.trouverStationPlusProche(-1.5, -1.5);
		assertEquals(stationC, stationPlusProche);
	}

}
