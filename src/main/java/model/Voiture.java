package model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="APP_Voiture")
public class Voiture implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idVoiture;
	
	@Column(name="IDGARAGE", nullable=true)
	private int idGarage;

	@NotEmpty
	@Column(name="PROPRIETAIRE", nullable=false)
	private String proprietaire;

	@Column(name="MARQUE", nullable=false)
	private String marque;

	@Column(name="VITESSEMAX", nullable=false)
	private int vitesseMax;

	public int getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}
	
	public int getIdGarage() {
		return idGarage;
	}

	public void setIdGarage(int idGarmarque) {
		this.idGarage = idGarmarque;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getVitesseMax() {
		return vitesseMax;
	}

	public void setVitesseMax(int vitesseMax) {
		this.vitesseMax = vitesseMax;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Voiture Voiture = (Voiture) o;

		if (Double.compare(Voiture.vitesseMax, vitesseMax) != 0) return false;
		if (Integer.compare(Voiture.idVoiture, idVoiture) != 0 ) return false;
		if (Integer.compare(Voiture.idGarage, idGarage) != 0 ) return false;
		if (proprietaire != null ? !proprietaire.equals(Voiture.proprietaire) : Voiture.proprietaire != null) return false;
		return marque != null ? marque.equals(Voiture.marque) : Voiture.marque == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idVoiture;
		result = 31 * result + idGarage;
		result = 31 * result + (proprietaire != null ? proprietaire.hashCode() : 0);
		result = 31 * result + (marque != null ? marque.hashCode() : 0);
		temp = Double.doubleToLongBits(vitesseMax);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Voiture [idVoiture=" + idVoiture +", idGarage=" + idGarage + ", proprietaire=" + proprietaire
				+ ", marque=" + marque + ", vitesseMax=" + vitesseMax + "]";
	}


}
