package fr.ul.miage.ProjetGL;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// données pour test en attendant l'implémentation de l'API
//		MetroGraph metroGraph = new MetroGraph();
//
//		// Ajout des stations
//		Station stationA = new Station("Station A", 0, 0, 1, 2);
//		Station stationB = new Station("Station B", 0, 0, 1, 3);
//		Station stationC = new Station("Station C", 0, 0, 2, 2);
//		Station stationD = new Station("Station D", 0, 0, 2, 3);
//		Station stationE = new Station("Station E", 0, 0, 3, 1);
//		Station stationF = new Station("Station F", 0, 0, 3, 2);
//		Station stationG = new Station("Station G", 0, 0, 4, 2);
//		Station stationH = new Station("Station H", 0, 0, 4, 3);
//		Station stationI = new Station("Station I", 0, 0, 5, 2);
//		Station stationJ = new Station("Station J", 0, 0, 5, 3);
//
//		metroGraph.ajouterStation(stationA);
//		metroGraph.ajouterStation(stationB);
//		metroGraph.ajouterStation(stationC);
//		metroGraph.ajouterStation(stationD);
//		metroGraph.ajouterStation(stationE);
//		metroGraph.ajouterStation(stationF);
//		metroGraph.ajouterStation(stationG);
//		metroGraph.ajouterStation(stationH);
//		metroGraph.ajouterStation(stationI);
//		metroGraph.ajouterStation(stationJ);
//
//		// Ajout des liaisons entre les stations
//		metroGraph.ajouterLiaison(stationA, stationB, 5);
//		metroGraph.ajouterLiaison(stationA, stationC, 10);
//		metroGraph.ajouterLiaison(stationB, stationD, 7);
//		metroGraph.ajouterLiaison(stationC, stationD, 5);
//		metroGraph.ajouterLiaison(stationC, stationE, 8);
//		metroGraph.ajouterLiaison(stationD, stationF, 3);
//		metroGraph.ajouterLiaison(stationE, stationG, 4);
//		metroGraph.ajouterLiaison(stationF, stationH, 5);
//		metroGraph.ajouterLiaison(stationG, stationI, 6);
//		metroGraph.ajouterLiaison(stationH, stationJ, 7);
//		metroGraph.ajouterLiaison(stationB, stationI, 10000);
//
//		// Ajout de perturbations
//		Liaison liaisonPerturbee = metroGraph.getListeAdjacence().get(stationA).get(0);
//		// liaisonPerturbee.setEstPerturbee(true);
//
//		// Définir les stations intermédiaires
//		List<Station> intermediateStations = new ArrayList<>();
//		intermediateStations.add(stationC);
//		intermediateStations.add(stationE);
//
//		// Recherche du trajet en passant par les stations intermédiaires
//		List<Station> trajet = metroGraph.findPathDFS(stationA, stationI, List.of(stationB));
//
//		// Affichage du trajet trouvé
//		if (trajet != null) {
//			System.out.println("Trajet trouvé :");
//			for (Station station : trajet) {
//				System.out.println(station.getNomStation());
//			}
//		} else {
//			System.out.println("Aucun trajet possible.");
//		}
//		trajet = metroGraph.findShortestPath(stationA, stationI);
//		// Affichage du trajet trouvé
//		if (trajet != null) {
//			System.out.println("Trajet trouvé :");
//			for (Station station : trajet) {
//				System.out.println(station.getNomStation());
//			}
//		} else {
//			System.out.println("Aucun trajet possible.");
//		}
//		trajet = metroGraph.findShortestPathWithLeastTransfers(stationA, stationI);
//		// Affichage du trajet trouvé
//		if (trajet != null) {
//			System.out.println("Trajet trouvé :");
//			for (Station station : trajet) {
//				System.out.println(station.getNomStation());
//			}
//		} else {
//			System.out.println("Aucun trajet possible.");
//		}
	}

}
