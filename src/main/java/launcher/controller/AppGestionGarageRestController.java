package launcher.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import launcher.model.Garage;
import launcher.model.Voiture;
import launcher.service.GarageService;
import launcher.service.VoitureService;
import launcher.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class AppGestionGarageRestController {

	public static final Logger logger = LoggerFactory.getLogger(AppGestionGarageRestController.class);

	@Autowired
	GarageService garageService;
	VoitureService voitureService;

	// -------------------Récupère tous les Garages---------------------------------------------

	@RequestMapping(value = "/garage/", method = RequestMethod.GET)
	public ResponseEntity<List<Garage>> listAllGarages() {
		List<Garage> garages = garageService.findAllGarages();
		if (garages.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// Cette réponse peut-être remplacée par la réponse HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Garage>>(garages, HttpStatus.OK);
	}

	// -------------------Récupère un Garage------------------------------------------

	@RequestMapping(value = "/garage/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGarage(@PathVariable("id") int id) {
		logger.info("Récupération d'un Garage avec l'id {}", id);
		Garage garage = garageService.findByIdGarage(id);
		if (garage == null) {
			logger.error("Aucun Garage avec l'id {} n'a été trouvé.", id);
			return new ResponseEntity(new CustomErrorType("Aucun Garage avec l'id " + id 
					+ " n'a été trouvé."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Garage>(garage, HttpStatus.OK);
	}

	// -------------------Créer un Garage-------------------------------------------

	@RequestMapping(value = "/garage/", method = RequestMethod.POST)
	public ResponseEntity<?> createGarage(@RequestBody Garage garage, UriComponentsBuilder ucBuilder) {
		logger.info("Création du Garage : {}", garage);

		if (garageService.isGarageExist(garage)) {
			logger.error("Impossible de créer. Un garage avec le nom {} existe déjà.", garage.getNomGarage());
			return new ResponseEntity(new CustomErrorType("Impossible de créer. Un garage avec le nom " + 
			garage.getNomGarage() + " existe déjà."),HttpStatus.CONFLICT);
		}
		garageService.saveGarage(garage);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/garage/{id}").buildAndExpand(garage.getIdGarage()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Met à jour un Garage ------------------------------------------------

	@RequestMapping(value = "/garage/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGarage(@PathVariable("id") int id, @RequestBody Garage garage) {
		logger.info("Mise à jour du Garage avec l'id {}", id);

		Garage currentGarage = garageService.findByIdGarage(id);

		if (currentGarage == null) {
			logger.error("Impossible de mettre à jour. Aucun Garage avec l'id {} n'a été trouvé.", id);
			return new ResponseEntity(new CustomErrorType("Impossible de mettre à jour. Aucun Garage avec l'id " + id + " n'a été trouvé."),
					HttpStatus.NOT_FOUND);
		}

		currentGarage.setNomGarage(garage.getNomGarage());
		currentGarage.setNbPlace(garage.getNbPlace());

		garageService.updateGarage(currentGarage);
		return new ResponseEntity<Garage>(currentGarage, HttpStatus.OK);
	}

	// ------------------- Supprime un Garage-----------------------------------------

	@RequestMapping(value = "/garage/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGarage(@PathVariable("id") int id) {
		logger.info("suppression du Garage avec l'id {}", id);

		Garage garage = garageService.findByIdGarage(id);
		if (garage == null) {
			logger.error("Impossible de supprimer. Aucun Garage avec l'id {} n'a été trouvé.", id);
			return new ResponseEntity(new CustomErrorType("Impossible de supprimer. Aucun Garage avec l'id " + id + " n'a été trouvé."),
					HttpStatus.NOT_FOUND);
		}
		garageService.deleteGarageByIdGarage(id);
		return new ResponseEntity<Garage>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Supprimer tous les Garages-----------------------------

	@RequestMapping(value = "/garage/", method = RequestMethod.DELETE)
	public ResponseEntity<Garage> deleteAllGarages() {
		logger.info("Suppression de tous les Garages");

		garageService.deleteAllGarages();
		return new ResponseEntity<Garage>(HttpStatus.NO_CONTENT);
	}
	
	// -------------------Récupère les Voitures d'un Garage---------------------------------------------

		@RequestMapping(value = "/voiture/", method = RequestMethod.GET)
		public ResponseEntity<List<Voiture>> listAllVoituresInGarage(int idGarage) {
			List<Voiture> voitures = voitureService.findByIdGarage(idGarage);
			if (voitures.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// Cette réponse peut-être remplacée par la réponse HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<Voiture>>(voitures, HttpStatus.OK);
		}

		// -------------------Récupère une Voiture------------------------------------------

		@RequestMapping(value = "/voiture/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getVoiture(@PathVariable("id") int id) {
			logger.info("Récupération d'une Voiture avec l'id {}", id);
			Voiture voiture = voitureService.findByIdVoiture(id);
			if (voiture == null) {
				logger.error("Aucune Voiture avec l'id {} n'a été trouvée.", id);
				return new ResponseEntity(new CustomErrorType("Aucune Voiture avec l'id " + id 
						+ " n'a été trouvée."), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Voiture>(voiture, HttpStatus.OK);
		}

		// -------------------Créer une Voiture-------------------------------------------

		@RequestMapping(value = "/voiture/", method = RequestMethod.POST)
		public ResponseEntity<?> createVoiture(@RequestBody Voiture voiture, UriComponentsBuilder ucBuilder) {
			logger.info("Création de la Voiture : {}", voiture);

			if (voitureService.isVoitureExist(voiture)) {
				logger.error("Impossible de créer. Un voiture avec le nom {} existe déjà.", voiture.getProprietaire());
				return new ResponseEntity(new CustomErrorType("Impossible de créer. Un voiture avec le nom " + 
				voiture.getProprietaire() + " existe déjà."),HttpStatus.CONFLICT);
			}
			voitureService.saveVoiture(voiture);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/voiture/{id}").buildAndExpand(voiture.getIdVoiture()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}

		// ------------------- Met à jour une Voiture ------------------------------------------------

		@RequestMapping(value = "/voiture/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateVoiture(@PathVariable("id") int id, @RequestBody Voiture voiture) {
			logger.info("Mise à jour de la Voiture avec l'id {}", id);

			Voiture currentVoiture = voitureService.findByIdVoiture(id);

			if (currentVoiture == null) {
				logger.error("Impossible de mettre à jour. Aucune Voiture avec l'id {} n'a été trouvée.", id);
				return new ResponseEntity(new CustomErrorType("Impossible de mettre à jour. Aucune Voiture avec l'id " + id + " n'a été trouvée."),
						HttpStatus.NOT_FOUND);
			}

			currentVoiture.setProprietaire(voiture.getProprietaire());
			currentVoiture.setMarque(voiture.getMarque());
			currentVoiture.setVitesseMax(voiture.getVitesseMax());

			voitureService.updateVoiture(currentVoiture);
			return new ResponseEntity<Voiture>(currentVoiture, HttpStatus.OK);
		}

		// ------------------- Supprime une Voiture-----------------------------------------

		@RequestMapping(value = "/voiture/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteVoiture(@PathVariable("id") int id) {
			logger.info("suppression de la Voiture avec l'id {}", id);

			Voiture voiture = voitureService.findByIdVoiture(id);
			if (voiture == null) {
				logger.error("Impossible de supprimer. Aucune Voiture avec l'id {} n'a été trouvée.", id);
				return new ResponseEntity(new CustomErrorType("Impossible de supprimer. Aucune Voiture avec l'id " + id + " n'a été trouvée."),
						HttpStatus.NOT_FOUND);
			}
			voitureService.deleteVoitureByIdVoiture(id);
			return new ResponseEntity<Voiture>(HttpStatus.NO_CONTENT);
		}

		// ------------------- Supprimer toutes les Voitures-----------------------------

		@RequestMapping(value = "/voiture/", method = RequestMethod.DELETE)
		public ResponseEntity<Voiture> deleteAllVoitures() {
			logger.info("Suppression de toutes les Voitures");

			voitureService.deleteAllVoitures();
			return new ResponseEntity<Voiture>(HttpStatus.NO_CONTENT);
		}
		
		//-------------------- Ajouter une Voiture à un Garage-----------------------------
		
		@RequestMapping(value = "/voiture/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> addVoitureToGarage(@PathVariable("id") int id, int idGarage) {
			logger.info("Ajout de la Voiture avec l'id {} à un Garage", id);

			Voiture currentVoiture = voitureService.findByIdVoiture(id);

			if (currentVoiture == null) {
				logger.error("Impossible d'ajouter à un Garage. Aucune Voiture avec l'id {} n'a été trouvée.", id);
				return new ResponseEntity(new CustomErrorType("Impossible d'ajouter à un Garage. Aucune Voiture avec l'id " + id + " n'a été trouvée."),
						HttpStatus.NOT_FOUND);
			}

			currentVoiture.setIdGarage(idGarage);

			voitureService.updateVoiture(currentVoiture);
			return new ResponseEntity<Voiture>(currentVoiture, HttpStatus.OK);
		}
		
		
		//------------------------ Supprimer une Voiture d'un Garage ------------------------
		
		
		@RequestMapping(value = "/voiture/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> removeVoitureFromGarage(@PathVariable("id") int id) {
			logger.info("Suppression de la Voiture avec l'id {} de son Garage", id);

			Voiture currentVoiture = voitureService.findByIdVoiture(id);

			if (currentVoiture == null) {
				logger.error("Impossible de supprimer du Garage. Aucune Voiture avec l'id {} n'a été trouvée.", id);
				return new ResponseEntity(new CustomErrorType("Impossible de supprimer du Garage. Aucune Voiture avec l'id " + id + " n'a été trouvée."),
						HttpStatus.NOT_FOUND);
			}
			
			currentVoiture.setIdGarage((Integer) null);

			voitureService.updateVoiture(currentVoiture);
			return new ResponseEntity<Voiture>(currentVoiture, HttpStatus.OK);
		}
		
	

}