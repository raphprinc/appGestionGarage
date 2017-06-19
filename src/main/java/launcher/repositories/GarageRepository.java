package launcher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import launcher.model.Garage;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    Garage findByIdGarage(int idGarage);

}