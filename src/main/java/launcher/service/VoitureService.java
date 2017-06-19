package launcher.service;

import java.util.List;

import launcher.model.Voiture;

public interface VoitureService {
	
	Voiture findByIdVoiture(int idVoiture);

	List<Voiture> findByIdGarage(int idGarage);

	void saveVoiture(Voiture voiture);

	void updateVoiture(Voiture voiture);

	void deleteVoitureByIdVoiture(int id);

	void deleteAllVoitures();

	List<Voiture> findAllVoitures();

	boolean isVoitureExist(Voiture voiture);
}