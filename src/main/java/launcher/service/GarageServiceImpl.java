package launcher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import launcher.model.Garage;
import launcher.repositories.GarageRepository;



@Service("garageService")
@Transactional
public class GarageServiceImpl implements GarageService{

	@Autowired
	private GarageRepository garageRepository;

	public Garage findByIdGarage(int idGarage) {
		return garageRepository.findOne((long) idGarage);
	}

	public void saveGarage(Garage garage) {
		garageRepository.save(garage);
	}

	public void updateGarage(Garage garage){
		saveGarage(garage);
	}

	public void deleteGarageByIdGarage(int idGarage){
		garageRepository.delete((long) idGarage);
	}

	public void deleteAllGarages(){
		garageRepository.deleteAll();
	}

	public List<Garage> findAllGarages(){
		return garageRepository.findAll();
	}

	public boolean isGarageExist(Garage garage) {
		return findByIdGarage(garage.getIdGarage()) != null;
	}

}
