package fr.ul.miage.ProjetGL;

import java.util.UUID;

public class Station {
	
	private String id;
	private String nomStation; // nom station métro
	private double latitude; // latitude de la station de métro
	private double longitude; // longitude de la station de métro
	private String ligne; // ligne à la quelle appartient la station de métro
	private int tempsArret; // le temps d'arret de la rame a chaque station en minutes
	
	public Station(String nomStation, double latitude, double longitude, int ligne, int tempsArret) {
		this.id = UUID.randomUUID().toString();
		this.nomStation = nomStation;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ligne = String.format("LIGNE-%d", ligne);
		this.tempsArret = tempsArret;
	}

	public String getId() {
		return id;
	}

	public String getNomStation() {
		return nomStation;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getLigne() {
		return ligne;
	}

	public int getTempsArret() {
		return tempsArret;
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
		Station other = (Station) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", nomStation=" + nomStation + ", ligne=" + ligne + ", tempsArret=" + tempsArret
				+ "]";
	}
	
	
	
	
}
