package gym;


/**
 * Cette classe contient la liste des comptes membres et la liste des comptes professionnels.
 * @author Christine Assal
 *
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RepertoireClient {
	private static ArrayList<Professionnel> listeProfessionnels = new ArrayList<>();
	private static ArrayList<Membre> listeMembres = new ArrayList<>();

	public static ArrayList<Professionnel> getListeProfessionnels() {
		return listeProfessionnels;
	}

	public static void setListeProfessionnels(ArrayList<Professionnel> listeProfessionnels) {
		RepertoireClient.listeProfessionnels = listeProfessionnels;
	}

	public static void hardCoded() {
	Membre m = new Membre(111111111, "christine", "christine@hotmail.com", "montreal", "QC", "XXXXXX", "514111111",
			"membre", "actif");

	
	listeMembres.add(m);
	
	
	Professionnel p = new Professionnel(999999999, "rana", "rana@gmail.com", "montreal", "QC", "XXXXXX", "514111111",
			"membre", "actif");
	
	listeProfessionnels.add(p);
	}
	
	
	public static ArrayList<Membre> getListeMembres() {
		return listeMembres;
	}

	public static void setListeMembres(ArrayList<Membre> listeMembres) {
		RepertoireClient.listeMembres = listeMembres;
	}


	/*
	 * Cette méthode s'occupe de la création de membres
	 * 
	 * @param nom
	 * 
	 * @param courriel
	 * 
	 * @param ville
	 * 
	 * @param codePostale
	 * 
	 * @param province
	 * 
	 * @param phone
	 */

	public static void creer(String nom, String courriel, String ville, String province, String codePostale,
			String phone) {
		Scanner scan = new Scanner(System.in);
		long numero;
		String membreOuProf;
		String payer;
		String statut = "suspendu";
		String expertise;
		boolean invalide = false;
		Random random = new Random();
		numero = 100000000 + random.nextInt(900000000);
		System.out.println("Est-ce que ce client est un membre ou un professionnel ? ");
		membreOuProf = scan.nextLine();

		while (!membreOuProf.equalsIgnoreCase("membre") && !membreOuProf.equalsIgnoreCase("professionnel")) {
			System.out.println("Vous devez entrer un mot valide tel que \"membre\" ou \"professionnel\" ");
			membreOuProf = scan.nextLine();
		}

		if (membreOuProf.equalsIgnoreCase("membre")) {
			Membre membre = new Membre(numero, nom, courriel, ville, province, codePostale, phone, membreOuProf,
					statut);
			do {
				invalide = false;
				System.out.println("Ce membre a-t-il payé ses frais d'adhésion ? ");
				payer = scan.nextLine();
				if (payer.equalsIgnoreCase("oui")) {
					membre.setStatut("actif");
					listeMembres.add(membre);
					System.out.println("Le compte membre a été crée avec succés.");
					System.out.println("Son numéro unique est  : " + numero);
					System.out.println("------------------------------------");
					System.out.println("Voici les informations du membre :");
					membre.afficherClient();

				} else if (payer.equalsIgnoreCase("non")) {
					System.out.println("Son numéro unique non actif est  : " + numero);
					System.out.println(

							"Le compte membre a été créé mais son statut est suspendu. Vous pouvez modifier son statut une fois que le client ait payé. ");
					System.out.println("------------------------------------");
					System.out.println("Voici les informations du membre :");
					listeMembres.add(membre);
					membre.afficherClient();
				} else {
					System.out.println("Entrée invalide. Répondez par oui ou non.");
					invalide = true;
				}

			} while (invalide);

		} else if (membreOuProf.equalsIgnoreCase("professionnel")) {
			System.out.println("Entrez l'expertise du professionnel");
			expertise = scan.nextLine();
			Professionnel professionnel = new Professionnel(numero, nom, courriel, ville, province, codePostale, phone,
					membreOuProf, expertise);
			listeProfessionnels.add(professionnel);
			System.out.println("Le compte professionnel a été crée avec succés.");
			System.out.println("Son numéro unique est  : " + numero);
			System.out.println("------------------------------------");
			System.out.println("Voici les informations du professionnel :");
			professionnel.afficherClient();

		}

	}

	/*
	 * Cette méthode permet de mettre à jour un compte membre en ayant une réference
	 * vers le compte membre à mettre à jour, et le compte membre mise à jour.
	 * 
	 * @param membre
	 * 
	 * @param membreUpdated
	 */
	public static void miseAJourMembre(Membre membre, Membre membreUpdated) {
		int index = listeMembres.indexOf(membre);
		listeMembres.set(index, membreUpdated);
		for (int i = 0; i < listeMembres.size(); i++) {
			Membre m = listeMembres.get(i);
			m.afficherClient();
		}
	}
	/*
	 * Cette méthode permet de mettre à jour un compte professionnel en ayant une
	 * réference vers le compte profesionnel à mettre à jour, et le compte
	 * professionnel mise à jour.
	 * 
	 * @param professionnel
	 * 
	 * @param professionnelUpdated
	 */

	public static void miseAJourProf(Professionnel professionnel, Professionnel professionnelUpdated) {
		int index = listeProfessionnels.indexOf(professionnel);
		listeProfessionnels.set(index, professionnelUpdated);
		for (int i = 0; i < listeProfessionnels.size(); i++) {
			Professionnel p = listeProfessionnels.get(i);
			p.afficherClient();
		}
	}

	/*
	 * Cette méthode permet de supprimer un compte membre
	 * 
	 * @param membre
	 */
	public static void supprimerMembre(Membre membre) {
		listeMembres.remove(membre);
	}

	/*
	 * Cette méthode permet de supprimer un compte professionnel
	 * 
	 * @param professionnel
	 */
	public static void supprimerProf(Professionnel professionnel) {
		listeProfessionnels.remove(professionnel);
	}

}
