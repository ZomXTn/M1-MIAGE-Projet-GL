package fr.ul.miage.ProjetGL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MetroGraph {
	private Map<Station, List<Liaison>> listeAdjacence; // théorie des graphes: la liste d'adjacence contient les noeuds
														// et les leurs arretes
	private List<Perturabtion> perturbationsEnCours;

	public MetroGraph() {
		// TODO Auto-generated constructor stub
		this.listeAdjacence = new HashMap<Station, List<Liaison>>();
		this.perturbationsEnCours = new ArrayList<Perturabtion>();
	}

	public Map<Station, List<Liaison>> getListeAdjacence() {
		return listeAdjacence;
	}

	public List<Perturabtion> getPerturbationsEnCours() {
		return perturbationsEnCours;
	}

	public void ajouterStation(Station station) {
		this.listeAdjacence.put(station, new ArrayList<Liaison>());
	}

	public void ajouterLiaison(Station depart, Station destination, int tempsParcours) {
		// ajouter une liaison dans le premier sens
		List<Liaison> liaisons = this.listeAdjacence.get(depart);
		liaisons.add(new Liaison(destination, tempsParcours, depart.getTempsArret()));
	}

	public void ajouterPerturbationParLiaison(Station depart, Liaison liaison) { // perturbation sur une ligne
		liaison.setEstPerturbee(true);
		this.perturbationsEnCours
				.add(new Perturabtion(
						String.format("Perturbation sur la liaison entre %s et %s", depart.getNomStation(),
								liaison.getDestination().getNomStation()),
						new ArrayList<Liaison>(Arrays.asList(liaison))));
	}

	public void ajouterPerturbationParStation(Station station) { // perturbation sur toutes les lignes liées à cette
																	// station
		List<Liaison> liaisonsPerturbes = new ArrayList<Liaison>();
		for (Map.Entry<Station, List<Liaison>> entry : this.listeAdjacence.entrySet()) {
			Station key = entry.getKey();
			if (key.equals(station)) {
				liaisonsPerturbes.addAll(this.listeAdjacence.get(key));
				for (Liaison liaison : this.listeAdjacence.get(key)) {
					liaison.setEstPerturbee(true);
				}
			} else {
				for (Liaison liaison : this.listeAdjacence.get(key)) {
					if (liaison.getDestination().equals(station))
						liaisonsPerturbes.add(liaison);
					liaison.setEstPerturbee(true);
				}
			}

		}
		this.perturbationsEnCours.add(new Perturabtion(
				String.format("Perturbation sur la station %s", station.getNomStation()), liaisonsPerturbes));
	}

	public List<Station> findShortestPath(Station source, Station destination) {
		Map<Station, Integer> distances = new HashMap<Station, Integer>();
		Map<Station, Station> stationsPrecedentes = new HashMap<Station, Station>();
		PriorityQueue<Liaison> fileDePriorite = new PriorityQueue<>();

		// Initialisation
		for (Station station : this.listeAdjacence.keySet()) {
			distances.put(station, Integer.MAX_VALUE);
			stationsPrecedentes.put(station, null);
		}

		distances.put(source, 0);

		fileDePriorite.add(new Liaison(source, 0, 0));

		while (!fileDePriorite.isEmpty()) {
			Liaison liaisonActuelle = fileDePriorite.poll();
			Station stationActuelle = liaisonActuelle.getDestination();
			int distanceActuelle = liaisonActuelle.getTempsTotal();

			if (distanceActuelle > distances.get(stationActuelle)) {
				continue;
			}

			List<Liaison> liaisons = this.listeAdjacence.get(stationActuelle);

			for (Liaison liaison : liaisons) {
				if (!liaison.isEstPerturbee()) {
					Station prochaineStation = liaison.getDestination();
					int tempsTotal = liaison.getTempsTotal();
					int nouvelleDistance = distanceActuelle + tempsTotal;

					if (nouvelleDistance < distances.get(prochaineStation)) {
						distances.put(prochaineStation, nouvelleDistance);
						stationsPrecedentes.put(prochaineStation, stationActuelle);
						fileDePriorite.add(liaison);
					}
				}
			}
		}

		if (stationsPrecedentes.get(destination) == null) {
	        return null; // Aucun trajet possible
	    }
		
		return reconstruireChemin(stationsPrecedentes, destination);

	}

	public List<Station> findShortestPathWithLeastTransfers(Station source, Station destination) {
		Map<Station, Integer> lineChanges = new HashMap<>();
		Map<Station, Station> previousStations = new HashMap<>();
		PriorityQueue<Station> queue = new PriorityQueue<>(Comparator.comparingInt(lineChanges::get));

		// Initialisation
		for (Station station : listeAdjacence.keySet()) {
			lineChanges.put(station, Integer.MAX_VALUE);
			previousStations.put(station, null);
		}

		lineChanges.put(source, 0);
		queue.add(source);

		while (!queue.isEmpty()) {
			Station currentStation = queue.poll();

			List<Liaison> adjacentLiaisons = listeAdjacence.get(currentStation);

			for (Liaison liaison : adjacentLiaisons) {
				if (!liaison.isEstPerturbee()) {
					Station nextStation = liaison.getDestination();
					int lineChange = (currentStation.getLigne().equals(nextStation.getLigne())) ? 0 : 1;
					int totalLineChanges = lineChanges.get(currentStation) + lineChange;

					if (totalLineChanges < lineChanges.get(nextStation)) {
						lineChanges.put(nextStation, totalLineChanges);
						previousStations.put(nextStation, currentStation);
						queue.add(nextStation);
					}
				}
			}
		}
		if (previousStations.get(destination) == null) {
	        return null; // Aucun trajet possible
	    }
		
		return reconstruireChemin(previousStations, destination);
	}

	public List<Station> reconstruireChemin(Map<Station, Station> stationsPrecedentes, Station destination) {
		// Reconstitution du chemin
		List<Station> chemin = new ArrayList<Station>();
		Station actuelle = destination;
		while (actuelle != null) {
			chemin.add(actuelle);
			actuelle = stationsPrecedentes.get(actuelle);
		}
		Collections.reverse(chemin);

		return chemin;
	}
	
	public List<Station> findPathDFS(Station depart, Station arrivee, List<Station> stationsIntermediaires) {
	    Set<Station> visitees = new HashSet<>();
	    List<Station> cheminActuel = new ArrayList<>();
	    cheminActuel.add(depart);

	    return dfs(depart, arrivee, stationsIntermediaires, visitees, cheminActuel);
	}

	protected List<Station> dfs(Station actuelle, Station arrivee, List<Station> stationsIntermediaires,
	                          Set<Station> visitees, List<Station> cheminActuel) {
	    visitees.add(actuelle);

	    if (actuelle.equals(arrivee)) {
	        // Vérifier si toutes les stations intermédiaires ont été visitées dans l'ordre spécifié
	        int index = 0;
	        for (Station station : stationsIntermediaires) {
	            if (!cheminActuel.contains(station)) {
	                visitees.remove(actuelle);
	                return null;
	            }
	            int currentIndex = cheminActuel.indexOf(station);
	            if (currentIndex <= index) {
	                visitees.remove(actuelle);
	                return null;
	            }
	            index = currentIndex;
	        }

	        return cheminActuel;
	    }

	    // Parcourir les liaisons de la station actuelle
	    List<Liaison> liaisons = listeAdjacence.get(actuelle);
	    for (Liaison liaison : liaisons) {
	        Station prochaineStation = liaison.getDestination();
	        if (!visitees.contains(prochaineStation) && !liaison.isEstPerturbee()) {
	            cheminActuel.add(prochaineStation);
	            List<Station> resultat = dfs(prochaineStation, arrivee, stationsIntermediaires, visitees, cheminActuel);
	            if (resultat != null) {
	                return resultat;
	            }
	            cheminActuel.remove(cheminActuel.size() - 1);
	        }
	    }

	    visitees.remove(actuelle);
	    return null;
	}
	
	public Station trouverStationPlusProche(double latitude, double longitude) {
	    Station stationPlusProche = null;
	    double distanceMin = Double.MAX_VALUE;

	    for (Station station : this.listeAdjacence.keySet()) {
	        double distance = Math.sqrt(Math.pow(latitude - station.getLatitude(), 2) + Math.pow(longitude - station.getLongitude(), 2));
	        if (distance < distanceMin) {
	            distanceMin = distance;
	            stationPlusProche = station;
	        }
	    }

	    return stationPlusProche;
	}

}
