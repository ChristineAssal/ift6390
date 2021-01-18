package gym;


/**
 * Cette classe sert è créer un compte professinnel en instanciant des objets Professionnel.
 * @author Christine Assal
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Professionnel extends Client {
	private String expertise;
	private static ArrayList<Service> listeServicesParProf = new ArrayList<>();
	private static Scanner scan;

	public Professionnel(long numero, String nom, String courriel, String ville, String province, String codePostale,
			String phone, String membreOuProf, String expertise) {
		super(numero, nom, courriel, ville, province, codePostale, phone, membreOuProf);

		this.expertise = expertise;
	}

	public Professionnel() {
		this.expertise = expertise;

	}
	/*
	 * @param numero
	 * 
	 * @return true si le numéro est celui d 'un professionell
	 */

	public static boolean isProfessionnel(long numero) {
		ArrayList<Professionnel> LProfessionnel = RepertoireClient.getListeProfessionnels();
		for (Professionnel p : LProfessionnel) {
			if (numero == p.getNumero()) {
				return true;
			}
		}
		return false;

	}

	/*
	 * @param numero
	 * 
	 * @return Professionnel correspondant au numero
	 */
	public static Professionnel getProfessionnel(long numero) {
		ArrayList<Professionnel> LProfessionnel = RepertoireClient.getListeProfessionnels();
		for (Professionnel p : LProfessionnel) {
			if (numero == p.getNumero()) {
				return p;
			}
		}
		System.out.println("Numéro invalide");
		return null;

	}

	/*
	 * Cette méthode permet à l'agent de donner accés au professionnel du centre
	 */
	public static void verifierProfessionnel() {
		try {
			scan = new Scanner(System.in);
			long numero;
			System.out.println("Entrez le numéro unique de l'utilisateur : ");
			numero = scan.nextLong();
			if (isProfessionnel(numero)) {
				System.out.println("Validé");
			} else {
				System.out.println("Numéro invalide");
			}
		} catch (

		InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}
	/*
	 * Cette méthode est utile pour l'application mobile
	 * 
	 * @param courriel
	 * 
	 * @return Professionnel
	 */

	public static Professionnel getProfessionnelParCourriel(String courriel) {
		ArrayList<Professionnel> LProfessionnel = RepertoireClient.getListeProfessionnels();
		for (Professionnel p : LProfessionnel) {

			if (courriel.equalsIgnoreCase(p.getCourriel())) {
				return p;
			}
		}
		return null;

	}

	/*
	 * Cette méthode est utilisé par l'application mobile pour vérifier le numéro de
	 * professionnel lorsqu'il entre son courriel
	 */
	public static Professionnel verifierProCourriel() {
		scan = new Scanner(System.in);
		Professionnel professionnel;
		String courriel;
		long numero;
		try {
			courriel = scan.nextLine();
			professionnel = getProfessionnelParCourriel(courriel);
			numero = professionnel.getNumero();
			if (isProfessionnel(numero)) {
				System.out.println("Validé");
				System.out.println("Nom du professionnel : " + professionnel.getNom());
				System.out.println("Numéro du professionnel : " + professionnel.getNumero());
				System.out.println("[Code QR à scanner]");
				System.out.println("----------------------------------------");
				return professionnel;

			} else {
				System.out.println("Numéro invalide");
				return null;
			}

		} catch (

		InputMismatchException e) {
			System.out.println("Entrée invalide");
		}
		return null;

	}

	/*
	 * Cette méthode génére une facture professionnel
	 */
	public static void factureProfessionnel(Professionnel professionnel) {
		double frais;
		String code = null;
		Membre membre;
		String nomMembre;
		long numMembre;
		Date date;
		System.out.println("Nom : " + professionnel.getNom());
		System.out.println("Numéro unique : " + professionnel.getNumero());
		System.out.println("Courriel : " + professionnel.getCourriel());
		System.out.println("Ville : " + professionnel.getVille());
		System.out.println("Province : " + professionnel.getProvince());
		System.out.println("Code postal : " + professionnel.getCodePostale());

		ArrayList<Service> listeServiceParProf = professionnel.getListeServicesParProf();
		ArrayList<Seance> seancesParService;
		ArrayList<Inscription> inscriptionParSeance = null;

		for (Service s : listeServiceParProf) {
			seancesParService = s.getListeSeancesParService();
			frais = s.getFrais();

			for (Seance sc : seancesParService) {
				code = sc.getCodeSeance();
				date = sc.getDate();
				inscriptionParSeance = sc.getListeInscriptionsParSeance();

				for (Inscription i : inscriptionParSeance) {
					membre = i.getMembre();
					nomMembre = membre.getNom();
					numMembre = membre.getNumero();
					System.out.println("Nom du membre : " + nomMembre);
					System.out.println("Numéro du membre : " + numMembre);
					System.out.println("Code de la séance : " + code);
					System.out.println("Montant à payer : " + frais);

				}
			}
		}

	}

	public void afficherClient() {
		System.out.println("Nom : " + this.getNom());
		System.out.println("Numéro unique : " + this.getNumero());
		System.out.println("Courriel : " + this.getCourriel());
		System.out.println("Ville : " + this.getVille());
		System.out.println("Province : " + this.getProvince());
		System.out.println("Code postal : " + this.getCodePostale());
		System.out.println("Numéro de téléphone : " + this.getPhone());
		System.out.println("Expertise : " + this.getExpertise());
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public static ArrayList<Service> getListeServicesParProf() {
		return listeServicesParProf;
	}

	public static void setListeServicesParProf(ArrayList<Service> listeServicesParProf) {
		Professionnel.listeServicesParProf = listeServicesParProf;
	}

	public void ajouterService(Service service) {
		listeServicesParProf.add(service);
	}

	public void supprimerService(Service service) {
		listeServicesParProf.remove(service);
	}

}
