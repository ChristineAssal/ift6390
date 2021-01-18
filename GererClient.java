package gym;

/*
 * Cette classe s'occupe de la vérification de certaines informations entrés
 * par l'utilisateur pour permettre la création, mise à jour et  retrait des 
 * comptes clients.
 * @author Christine Assal
 *
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class GererClient {

	 static Scanner scan = new Scanner(System.in);

	public static boolean isLettresOuEspace(String nom) {
		boolean b = false;
		for (int i = 0; i < nom.length(); i++) {
			char c = nom.charAt(i);
			b = Character.isLetter(c) || Character.isWhitespace(c);
		}
		return b;
	}

	/*
	 * Cette méthode s'assure que le nom d'un client ne contient que des lettres et
	 * des espaces et ne dépasse pas 25 lettres.
	 * 
	 * @param nom
	 */

	public static String validerNom(String nom) {
		while (nom.length() > 25 || (isLettresOuEspace(nom) == false)) {
			System.out.println(
					"Format invalide. Un nom ne peut contenir que des lettres et ne dépasse pas 25 lettres. Veuilez reessayer");
			nom = scan.nextLine();
		}
		return nom;
	}

	/*
	 * Cette méthode s'assure que l'adresse courriel ne dépasse pas 25 caractères
	 * 
	 * @param courriel
	 */
	public static String validerCourriel(String courriel) {
		while (courriel.length() > 25) {
			System.out.println("Format invalide. Un courriel est formé d'au plus 25 caractères. Veuilez reessayer");
			courriel = scan.nextLine();
		}
		return courriel;

	}

	/*
	 * Cette méthode s'assure que le nom d'une ville ne dépasse par 14 caractères
	 * 
	 * @param ville
	 */
	public static String validerVille(String ville) {
		while (ville.length() > 14) {
			System.out.println(
					"Format invalide. Le nom d'une ville ne peut contenir plus que 14 caractères . Veuilez reessayer");
			ville = scan.nextLine();
		}
		return ville;
	}

	/*
	 * Cette méthode s'assure que la province ne contient que deux lettres
	 * 
	 * @param province
	 */
	public static String validerProvince(String province) {
		while (province.length() != 2 || !province.chars().allMatch(Character::isLetter)) {
			System.out.println(
					"Format invalide. Le nom d'une province doit contenir exactement 2 lettres. Veuilez reessayer");
			province = scan.nextLine();
		}
		return province;
	}

	public static String validerCodePostal(String codePostal) {
		while (codePostal.length() != 6) {
			System.out.println(
					"Format invalide. Un code postal doit contenir exactement 6 caractères. Veuilez reessayer");
			codePostal = scan.nextLine();
		}
		return codePostal;
	}

	/*
	 * Cette méthode prend les entrées de l'utilisateur, les valide, puis appelle la
	 * méthode creer de RepertoireClient qui elle s'occuperait de la création des
	 * comptes
	 */
	public static void creerClient() {
		Scanner scan = new Scanner(System.in);
		String nom = null;
		String courriel;
		String ville, province, codePostal;
		String phone;

		try {
			Scanner scan2 = new Scanner(System.in);

			System.out.println("Entrez le nom du client: ");
			nom = scan2.nextLine();
			nom=validerNom(nom);

			System.out.println("Entrez l'adresse courriel du client : ");
			courriel = scan2.nextLine();
			courriel= validerCourriel(courriel);

			System.out.println("Entrez la ville du client : ");
			ville = scan2.nextLine();
			ville= validerVille(ville);

			System.out.println("Entrez la province du client (2 lettres) : ");
			province = scan2.nextLine();
			province=validerProvince(province);

			System.out.println("Entrez le code postal du client (6 caractères) : ");
			codePostal = scan2.nextLine();
			codePostal=validerCodePostal(codePostal);

			System.out.println("Entrez le numéro de téléphone du client : ");
			phone = scan2.nextLine();

			RepertoireClient.creer(nom, courriel, ville, province, codePostal, phone);
		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}
	}

	/*
	 * Cette méthode permet la mise à jour des comptes clients en appelant les
	 * méthodes miseAJourMembre et MiseAJourProfessionnel de RepertoireClient
	 */
	public static void miseAJourClient() {
		long numero;
		String modification;
		String newCourriel, newVille, newProvince, newCodePostal, newPhone, newStatut, newExpertise, ouiNon;
		Membre membre;
		Professionnel professionnel;
		String boucle;
		boolean invalide = false;
		try {
			System.out.println("Entrez le numéro unique de l'utilisateur : ");
			numero = scan.nextLong();
			Scanner scan2 = new Scanner(System.in);

			// compte membre
			if (Membre.isMembre(numero)) {
				membre = Membre.getMembre(numero);
				Membre membreUpdated = new Membre();
				membreUpdated = membre;
				do {
					invalide = false;
					System.out.println(
							"Entrez quel champs voulez-vous modifier ? (Exemple de réponse : \"courriel\" , \"ville\" , etc)");
					modification = scan2.nextLine();
					if (modification.equalsIgnoreCase("courriel")) {
						System.out.println("Entrez le nouveau courriel");
						newCourriel = scan2.nextLine();
						validerCourriel(newCourriel);
						membreUpdated.setCourriel(newCourriel);
					} else if (modification.equalsIgnoreCase("ville")) {
						System.out.println("Entrez la nouvelle ville");
						newVille = scan2.nextLine();
						validerVille(newVille);
						membreUpdated.setVille(newVille);
					} else if (modification.equalsIgnoreCase("province")) {
						System.out.println("Entrez la nouvelle province");
						newProvince = scan2.nextLine();
						validerProvince(newProvince);
						membreUpdated.setProvince(newProvince);
					} else if (modification.equalsIgnoreCase("code postal")) {
						System.out.println("Entrez le nouveau code postal");
						newCodePostal = scan2.nextLine();
						validerCodePostal(newCodePostal);
						membreUpdated.setCodePostale(newCodePostal);
					} else if (modification.equalsIgnoreCase("numéro de téléphone")) {
						System.out.println("Entrez le nouveau numéro de téléphone");
						newPhone = scan2.nextLine();
						membreUpdated.setPhone(newPhone);
					} else if (modification.equalsIgnoreCase("statut")) {
						System.out.println("Le membre a-t-il payé ses frais ?");
						ouiNon = scan2.nextLine();
						if (ouiNon.equalsIgnoreCase("oui")) {
							newStatut = "actif";
							membreUpdated.setStatut(newStatut);
						} else {
							System.out.println("Le membre doit payer ses frais pour modifier son statut");
						}
					} else {
						System.out.println("Entrée invalide");
						invalide = true;
						return;
					}
					RepertoireClient.miseAJourMembre(membre, membreUpdated);

					System.out.println("Voulez-vous effectuer d'autres moficiations sur le même membre?");
					boucle = scan.next();

				} while (boucle.equalsIgnoreCase("oui") || invalide);

			}

			// compte professionnel
			else if (Professionnel.isProfessionnel(numero)) {
				professionnel = Professionnel.getProfessionnel(numero);
				Professionnel professionnelUpdated = new Professionnel();
				professionnelUpdated = professionnel;
				do {
					invalide = false;
					System.out.println(
							"Entrez quel champs voulez-vous modifier ? (Exemple de réponse : \"courriel\" , \"ville\" , etc)");
					modification = scan2.nextLine();
					if (modification.equalsIgnoreCase("courriel")) {
						System.out.println("Entrez le nouveau courriel");
						newCourriel = scan2.nextLine();
						validerCourriel(newCourriel);
						professionnelUpdated.setCourriel(newCourriel);
					} else if (modification.equalsIgnoreCase("ville")) {
						System.out.println("Entrez la nouvelle ville");
						newVille = scan2.nextLine();
						validerVille(newVille);
						professionnelUpdated.setVille(newVille);
					} else if (modification.equalsIgnoreCase("province")) {
						System.out.println("Entrez la nouvelle province");
						newProvince = scan2.nextLine();
						validerProvince(newProvince);
						professionnelUpdated.setProvince(newProvince);
					} else if (modification.equalsIgnoreCase("code postal")) {
						System.out.println("Entrez le nouveau code postal");
						newCodePostal = scan2.nextLine();
						validerCodePostal(newCodePostal);
						professionnelUpdated.setCodePostale(newCodePostal);
					} else if (modification.equalsIgnoreCase("numéro de téléphone")) {
						System.out.println("Entrez le nouveau numéro de téléphone");
						newPhone = scan2.nextLine();
						professionnelUpdated.setPhone(newPhone);
					} else if (modification.equalsIgnoreCase("expertise")) {
						System.out.println("Entrez la nouvelle expertise");
						newExpertise = scan2.nextLine();
						professionnelUpdated.setExpertise(newExpertise);
					} else {
						System.out.println("Entrée invalide");
						invalide = true;
					}
					RepertoireClient.miseAJourProf(professionnel, professionnelUpdated);

					System.out.println("Voulez-vous effectuer d'autres moficiations sur le même professionnel?");
					boucle = scan.next();

				} while (boucle.equalsIgnoreCase("oui") || invalide);

			} else {
				System.out.println("Numéro invalide");
			}
		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}

	/*
	 * Cette méthode permet le retrait d'un compte client en appelant les méthodes
	 * supprimerMembre et supprimerProf de RepertoireClient
	 */
	public static void supprimerClient() {
		long numero;
		Membre membre;
		Professionnel professionnel;
		String confirmation = null;
		boolean invalide = false;
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Entrez le numéro unique de l'utilisateur : ");
			numero = scan.nextLong();
			Scanner scan2 = new Scanner(System.in);
			// Supprimer membre
			if (Membre.isMembre(numero)) {
				membre = Membre.getMembre(numero);
				do {
					System.out.println("Voulez-vous supprimer ce membre?");
					membre.afficherClient();
					confirmation = scan2.nextLine();
					if (confirmation.equalsIgnoreCase("oui"))
						RepertoireClient.supprimerMembre(membre);
					else if (confirmation.equalsIgnoreCase("non")) {
						System.out.println("Le membre n'a pas été supprimé ");
						return;
					} else {
						System.out.println("Entrée invalide. Répondez par oui ou non.");

						invalide = true;
					}
				} while (invalide);

			}
			// Supprimer professionnel
			else if (Professionnel.isProfessionnel(numero)) {
				professionnel = Professionnel.getProfessionnel(numero);
				do {
					System.out.println("Voulez-vous supprimer ce membre?");
					professionnel.afficherClient();
					confirmation = scan2.nextLine();
					if (confirmation.equalsIgnoreCase("oui"))
						RepertoireClient.supprimerProf(professionnel);
					else if (confirmation.equalsIgnoreCase("non")) {
						System.out.println("Le professionnel n'a pas été supprimé ");
						return;
					} else {
						System.out.println("Entrée invalide. Répondez par oui ou non.");
						invalide = true;
					}
				} while (invalide);

			} else {
				System.out.println("Numéro invalide");
			}
		} catch (

		InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}
}
