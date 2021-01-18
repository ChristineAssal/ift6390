package gym;

/*
 * Cette classe s'occupe de la vérification de certaines informations entrés
 * par l'utilisateur pour permettre la création, mise à jour et  retrait des 
 * services.
 * @author Christine Assal
 *
 */
/* Référence :https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/ */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;

public class GererService {

	static Scanner scan = new Scanner(System.in);

	/*
	 * Cette méthode s'assure que la date entré par l'utilisateur respecte un bon
	 * format.
	 * 
	 * @param dateV
	 * 
	 * @return true si valide
	 */
	public static boolean validerDate(String dateV) {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		format.setLenient(false);

		try {
			format.parse(dateV);
		} catch (Exception e) {
			System.out.println("Format invalide");
			return false;

		}

		return true;
	}

	/*
	 * Cette méthode s'assure que l'heure entré par l'utilisateur respecte un bon
	 * format.
	 * 
	 * @param heureV
	 * 
	 * @return true si valide
	 */
	public static boolean validerHeure(String heureV) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setLenient(false);

		try {
			format.parse(heureV);
		} catch (Exception e) {
			System.out.println("Format invalide");
			return false;
		}

		return true;
	}

	/*
	 * Cette méthode s'assure que la capacité entré par l'utilisateur ne dépasse pas
	 * 30 membres
	 * 
	 * @param capacite
	 */
	public static void validerCapacite(int capacite) {
		while (capacite > 30) {
			System.out.println("La capacité maximale est de 30 inscriptions");
			System.out.println("Entrez la capacité d'une séance de service : ");

			capacite = scan.nextInt();

		}
	}

	/*
	 * Cette méthode s'assure que le code de service est composé de 3 chiffres
	 * 
	 * 
	 * @param codeService
	 */
	public static void validerCodeService(long codeService) {
		String code = codeService + "";
		while (code.length() != 3) {
			System.out.println("Le code de service doit être composé de 3 chiffres ");
			System.out.println("Entrez le code de service (3 chiffres) : ");

			codeService = scan.nextInt();
		}
	}

	/*
	 * Cette méthode s'assure que le commentaire ne dépasse pas 100 caractères
	 * 
	 * @param commentaires
	 */
	public static void validerCommentaire(String commentaires) {
		while (commentaires.length() > 100) {
			System.out.println("Le commentaire ne peut contenir plus que 100 commentaires ");
			System.out.println("Entrez le code de service (3 chiffres) : ");

			commentaires = scan.nextLine();
		}
	}

	/*
	 * Cette méthode prend les entrées de l'utilisateur,les valide, puis appelle la
	 * méthode creerService de RepertoireService qui elle, s'occuperait de la
	 * création des services.
	 */

	public static void creerService() {
		long numero;
		String dateDebut;
		String dateFin;
		String nom;
		String heure;
		String reccurence;
		int capacite;
		long codeService;
		float frais;
		String ouiNon;
		String commentaires = "";
		try {
			Scanner scan = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);

			System.out.println("Entrez le numéro unique du professionnel : ");
			numero = scan.nextLong();
			if (Professionnel.isProfessionnel(numero)) {
				boolean valide = false;
				// date début
				do {
					System.out.println("Entrez la date de début du service selon le format JJ-MM-AAAA : ");
					dateDebut = scan2.nextLine();
					valide = validerDate(dateDebut);
				} while (!valide);

				// date fin
				do {
					System.out.println("Entrez la date de fin du service selon le format JJ-MM-AAAA : ");
					dateFin = scan2.nextLine();
					valide = validerDate(dateFin);
				} while (!valide);

				// nom service
				System.out.println("Entrez le nom du service : ");
				nom = scan2.nextLine();

				// heure
				do {
					System.out.println("Entrez l'heure du service selon le format HH:MM : ");
					heure = scan2.nextLine();
					valide = validerHeure(heure);
				} while (!valide);

				// recurrence
				System.out.println(
						"Entrez la Récurrence hebdomadaire des séances en mettant une espace entre chaque jour : ");
				reccurence = scan2.nextLine();

				// capacité
				System.out.println("Entrez la capacité d'une séance de service : ");
				capacite = scan.nextInt();
				validerCapacite(capacite);

				// code de service
				System.out.println("Entrez le code de service à 3 chiffres : ");
				codeService = scan.nextLong();
				validerCodeService(codeService);

				// faris de service
				System.out.println("Entrez les frais de service : ");
				frais = scan.nextFloat();

				System.out.println("Voulez-vous entrer un commentaire ? ");
				ouiNon = scan.next();
				if (ouiNon.equals("oui")) {
					System.out.println("Entrez votre commentaire (maximum 100 caractères) : ");
					commentaires = scan2.nextLine();
					validerCommentaire(commentaires);

				}

				RepertoireService.creerService(dateDebut, dateFin, nom, heure, reccurence, capacite, numero,
						codeService, frais, commentaires);

			} else {
				System.out.println("Numéro invalide");
			}
		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}

	/*
	 * Cette méthode permet la mise à jour des services en appelant la méthode
	 * miseAJourService de RepertoireService
	 */
	public static void miseAjourService() {
		Service serviceUpdated;
		String boucle;
		boolean valide;
		long numero;
		long code;
		Service service = null;
		String infoAmodifier;
		boolean invalide = false;
		String newDateDebut, newDateFin, newHeure, newRecurrence, newCommentaire;
		float newFrais;
		int newCapacite;
		long newCodeService;
		Professionnel professionnel;
		try {
			Scanner scan2 = new Scanner(System.in);
			System.out.println("Entrez le numéro unique du professionnel : ");
			numero = scan.nextInt();
			if (Professionnel.isProfessionnel(numero)) {
				professionnel = Professionnel.getProfessionnel(numero);
				ArrayList<Service> LServicesParProf = professionnel.getListeServicesParProf();
				System.out.println("Voici les services offerts par ce professionnel :");
				for (Service s : LServicesParProf) {
					s.afficherService();
					System.out.println("-----------------------");
				}
				System.out.println("Entrez le code du service que vous souhaitez mettre à jour : ");
				code = scan.nextInt();
				for (Service s : LServicesParProf) {
					if (s.getCodeService() == code) {
						service = s;
					} else {
						System.out.println("code invalide");
					}
				}
				serviceUpdated = service;
				serviceUpdated.afficherService();
				do {
					invalide = false;
					System.out.println("Quels champs souhaitez-vous mettre à jour ?");
					infoAmodifier = scan2.nextLine();
					if (infoAmodifier.equalsIgnoreCase("Date de début")) {
						do {
							System.out.println(
									"Entrez la nouvelle date de début du service selon le format JJ-MM-AAAA : ");
							newDateDebut = scan2.nextLine();
							valide = validerDate(newDateDebut);
						} while (!valide);
						serviceUpdated.setDateDebut(newDateDebut);
					} else if (infoAmodifier.equalsIgnoreCase("Date de fin")) {
						do {
							System.out
									.println("Entrez la nouvelle date de fin du service selon le format JJ-MM-AAAA : ");
							newDateFin = scan2.nextLine();
							valide = validerDate(newDateFin);
						} while (!valide);
						serviceUpdated.setDateFin(newDateFin);
					} else if (infoAmodifier.equalsIgnoreCase("Heure du service")) {
						do {
							System.out.println("Entrez la nouvelle heure du service selon le format HH:MM : ");
							newHeure = scan2.nextLine();
							valide = validerHeure(newHeure);
						} while (!valide);
						serviceUpdated.setHeure(newHeure);

					} else if (infoAmodifier.equalsIgnoreCase("Capacité")) {
						System.out.println("Entrez la nouvelle capacité : ");
						newCapacite = scan.nextInt();
						validerCapacite(newCapacite);
						serviceUpdated.setCapacite(newCapacite);

					} else if (infoAmodifier.equalsIgnoreCase("Code de service")) {
						System.out.println("Entrez le nouveau code de service ");
						newCodeService = scan.nextLong();
						validerCodeService(newCodeService);
						serviceUpdated.setCodeService(newCodeService);
					} else if (infoAmodifier.equalsIgnoreCase("Commentaires")) {
						System.out.println("Entrez le nouveau commentaire (max 100 caractères) ");
						newCommentaire = scan2.nextLine();
						serviceUpdated.setCommentaires(newCommentaire);
					} else if (infoAmodifier.equalsIgnoreCase("frais de service")) {
						System.out.println("Entrez les nouvelles frais de service : ");
						newFrais = scan.nextFloat();
						serviceUpdated.setFrais(newFrais);
					} else {
						System.out.println("Entré invalide");
						invalide = true;
					}

					RepertoireService.miseAJourService(service, serviceUpdated);
					System.out.println("Voulez-vous effectuer d'autres moficiations sur le même service?");
					boucle = scan.next();

				} while (boucle.equalsIgnoreCase("oui") || invalide);

			} else

			{
				System.out.println("Numéro invalide");
			}
		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}

	/*
	 * Cette méthode permet le retrait des services en appelant la méthode
	 * supprimerService de RepertoireService
	 */
	public static void supprimerService() {
		Professionnel professionnel;
		long numero;
		long code;
		try {
			System.out.println("Entrez le numéro unique du professionnel : ");
			numero = scan.nextInt();
			if (Professionnel.isProfessionnel(numero)) {
				professionnel = Professionnel.getProfessionnel(numero);
				ArrayList<Service> LServicesParProf = professionnel.getListeServicesParProf();
				System.out.println("Voici les services offerts par ce professionnel :");
				for (Service s : LServicesParProf) {
					s.afficherService();
				}
				System.out.println("Entrez le code du service à supprimer : ");
				code = scan.nextInt();

				RepertoireService.supprimerService(code);

				System.out.println("Le service a été supprimé avec succés.");

			}
		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}
}
