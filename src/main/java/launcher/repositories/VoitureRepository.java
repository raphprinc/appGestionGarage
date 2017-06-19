package launcher.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import launcher.model.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    Voiture findByIdVoiture(int idVoiture);
    
    List<Voiture> findByIdGarage(int idGarage);

}
