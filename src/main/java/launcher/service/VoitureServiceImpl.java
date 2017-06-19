package launcher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import launcher.model.Voiture;
import launcher.repositories.VoitureRepository;



@Service("voitureService")
@Transactional
public class VoitureServiceImpl implements VoitureService{

	@Autowired
	private VoitureRepository voitureRepository;

	public Voiture findByIdVoiture(int idVoiture) {
		return voitureRepository.findOne((long) idVoiture);
	}

	public List<Voiture> findByIdGarage(int idGarage) {
		return voitureRepository.findByIdGarage(idGarage);
	}

	public void saveVoiture(Voiture voiture) {
		voitureRepository.save(voiture);
	}

	public void updateVoiture(Voiture voiture){
		saveVoiture(voiture);
	}

	public void deleteVoitureByIdVoiture(int idVoiture){
		voitureRepository.delete((long) idVoiture);
	}

	public void deleteAllVoitures(){
		voitureRepository.deleteAll();
	}

	public List<Voiture> findAllVoitures(){
		return voitureRepository.findAll();
	}

	public boolean isVoitureExist(Voiture voiture) {
		return findByIdVoiture(voiture.getIdVoiture()) != null;
	}

}
