package launcher.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="APP_Garage")
public class Garage implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idGarage;

	@NotEmpty
	@Column(name="NOMGARAGE", nullable=false)
	private String nomGarage;

	@Column(name="NBPLACE", nullable=false)
	private int nbPlace;

	public int getIdGarage() {
		return idGarage;
	}

	public void setIdGarage(int idGarage) {
		this.idGarage = idGarage;
	}
	
	public String getNomGarage() {
		return nomGarage;
	}

	public void setNomGarage(String nomGarage) {
		this.nomGarage = nomGarage;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Garage Garage = (Garage) o;

		if (Integer.compare(Garage.idGarage, idGarage) != 0 ) return false;
		if (nomGarage != null ? !nomGarage.equals(Garage.nomGarage) : Garage.nomGarage != null) return false;
		return Integer.compare(Garage.nbPlace, nbPlace) != 0;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idGarage;
		result = 31 * result + (nomGarage != null ? nomGarage.hashCode() : 0);
		temp = nbPlace;
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Garage [idGarage=" + idGarage + ", nomGarage=" + nomGarage
				+ ", nbPlace=" + nbPlace + "]";
	}


}
