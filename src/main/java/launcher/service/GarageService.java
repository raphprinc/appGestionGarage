package launcher.service;

import java.util.List;

import launcher.model.Garage;

public interface GarageService {
	
	Garage findByIdGarage(int idGarage);

	void saveGarage(Garage garage);

	void updateGarage(Garage garage);

	void deleteGarageByIdGarage(int id);

	void deleteAllGarages();

	List<Garage> findAllGarages();

	boolean isGarageExist(Garage garage);
}