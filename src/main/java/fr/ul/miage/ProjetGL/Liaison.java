package fr.ul.miage.ProjetGL;

import java.util.UUID;

public class Liaison implements Comparable<Liaison> {
	private String id;
	private Station destination; // station destination
	private int tempsParcours; // le temps que met la rame pour parcourir la liaison
	private boolean estPerturbee; // liaison perturbee? oui ou non
	private int tempsTotal; // somme du temps de parcours et temps d'arret de la station source

	public Liaison(Station destination, int tempsParcours, int tempsArretDepart) {
		this.id = UUID.randomUUID().toString();
		this.destination = destination;
		this.tempsParcours = tempsParcours;
		this.estPerturbee = false;
		this.tempsTotal = tempsParcours + tempsArretDepart;
	}

	public String getId() {
		return id;
	}

	public Station getDestination() {
		return destination;
	}

	public int getTempsParcours() {
		return tempsParcours;
	}

	public boolean isEstPerturbee() {
		return estPerturbee;
	}

	public void setEstPerturbee(boolean estPerturbee) {
		this.estPerturbee = estPerturbee;
	}

	public int getTempsTotal() {
		return tempsTotal;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Liaison other = (Liaison) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Liaison [id=" + id + ", destination=" + destination + ", tempsParcours=" + tempsParcours
				+ ", estPerturbee=" + estPerturbee + ", tempsTotal=" + tempsTotal + "]";
	}

	public int compareTo(Liaison o) {
		// TODO Auto-generated method stub
		return Integer.compare(tempsTotal, o.tempsTotal);
	}

}
