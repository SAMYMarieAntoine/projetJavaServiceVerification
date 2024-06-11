package fr.formation.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.repository.VerificationRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/verification")
@CrossOrigin("*")
public class VerificationApiController {

	private static final Logger log = LoggerFactory.getLogger(VerificationApiController.class);

	//@Autowired
	private final VerificationRepository verificationRepository;



	public VerificationApiController(VerificationRepository verificationRepository) {
		this.verificationRepository = verificationRepository;
		log.info("Initialisation de VerificationApiController");
	}


	// Génération d'un mot de passe fort
		@GetMapping("/generateMotDePasseFort")
		public String generateMotDePasseFort() {
			log.info("Génération d'un mot de passe fort.");
			String motDePasseFort = generateStrongPassword();
			log.info("Mot de passe fort généré : {}", motDePasseFort);
			return motDePasseFort;
		}

		// Méthode pour générer un mot de passe fort
		private String generateStrongPassword() {
		// Exemple de génération de mot de passe fort
		return new BCryptPasswordEncoder().encode("StrongP@ssw0rd!");
		}
		

	//Vérification de la force d'un mot de passe

	@PostMapping("/mot-de-passe/force")
	private boolean getForceMotDePasse(@Valid @RequestBody String motDePasse) {

		log.info("Vérification de la force du mot de passe.");
		boolean isStrong = isForceMotDePasse(motDePasse);
		log.info("Résultat de la vérification de la force du mot de passe : {}", isStrong);
		return isStrong;
	}


	// Méthode pour vérifier si un mot de passe est fort
	private boolean isForceMotDePasse(String motDePasse) {
		// Exemple de vérification de la force du mot de passe
		return motDePasse.length() > 8 && motDePasse.matches(".*\\d.*") && motDePasse.matches(".*[a-z].*") && motDePasse.matches(".*[A-Z].*");
	}

	//Vérification mot de passe compromis

	@PostMapping("/mot-de-passe/compromis")
	private boolean  getMotDePasseCompromis(@Valid @RequestBody String motDePasse) {

		log.info("Exécution de la méthode getMotDePasseCompromis pour le mot de passe: {}", motDePasse);

		log.info("Vérification si le mot de passe est compromis.");
		boolean isCompromis = isPasswordCompromis(motDePasse);
		log.info("Résultat de la vérification du mot de passe compromis : {}", isCompromis);
		return isCompromis;

	}


	// Méthode pour vérifier si un mot de passe est compromis
	private boolean isPasswordCompromis(String motDePasse) {
		// Exemple de vérification contre une liste de mots de passe compromis
		// Ici, il faudrait comparer avec les mots de passe hachés en SHA-1 stockés dans les fichiers TXT
		//return false; // À implémenter
		return true;// à tester le retour du SHA
	}

}
