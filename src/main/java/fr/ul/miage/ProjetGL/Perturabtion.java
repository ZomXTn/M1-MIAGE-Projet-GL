package fr.ul.miage.ProjetGL;

import java.util.ArrayList;
import java.util.List;

public class Perturabtion {
	private String nomPerturbation;
	private List<Liaison> listeLiaisonPerturbes;

	

	public Perturabtion(String nomPerturbation, List<Liaison> listeLiaisonPerturbes) {
		this.nomPerturbation = nomPerturbation;
		this.listeLiaisonPerturbes = listeLiaisonPerturbes;
	}

	public String getNomPerturbation() {
		return nomPerturbation;
	}

	public List<Liaison> getListeLiaisonPerturbes() {
		return listeLiaisonPerturbes;
	}
	
	
}
