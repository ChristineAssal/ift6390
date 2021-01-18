package gym;


/**
 * Cette classe sert è créer un compte membre en instanciant des objets Membre.
 * @author Christine Assal
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Membre extends Client {
	private String statut;
	private ArrayList<Inscription> listeInscriptionsParMembre = new ArrayList<>();
	private static Scanner scan;

	public Membre(long numero, String nom, String courriel, String ville, String province, String codePostale,
			String phone, String membreOuProf, String statut) {
		super(numero, nom, courriel, ville, province, codePostale, phone, membreOuProf);
		this.statut = statut;

	}

	public Membre() {
		this.statut = statut;
	}

	/*
	 * Ne tient pas compte du statut de membre
	 * 
	 * @param numero
	 * 
	 * @return true si le numéro passé en argument est celui d'un membre GYM
	 */

	public static boolean isMembre(long numero) {
		ArrayList<Membre> LMembres = RepertoireClient.getListeMembres();
		for (Membre m : LMembres) {
			if (numero == m.getNumero()) {
				return true;
			}
		}
		return false;

	}

	/*
	 * @param numero
	 * 
	 * @return Membre
	 */
	public static Membre getMembre(long numero) {
		ArrayList<Membre> LMembres = RepertoireClient.getListeMembres();
		for (Membre m : LMembres) {
			if (numero == m.getNumero()) {
				return m;
			}
		}
		System.out.println("Numéro invalide");
		return null;

	}

	/*
	 * @param numero
	 * 
	 * @return true si le numéro passé en argumente est valide + le statut est actif
	 */
	public static boolean isMembreActif(long numero) {
		ArrayList<Membre> LMembres = RepertoireClient.getListeMembres();
		Membre membre = getMembre(numero);
		String statut = membre.getStatut();
		for (Membre m : LMembres) {
			if (numero == m.getNumero() && statut.equals("actif")) {
				return true;
			}
		}
		return false;

	}

	/*
	 * Cette méthode permet à l'agent de vérifier un membre pour lui donner accés au
	 * centre
	 */
	public static void verifierMembre() {
		scan = new Scanner(System.in);
		long numero;
		try {
			System.out.println("Entrez le numéro unique de l'utilisateur : ");
			numero = scan.nextLong();
			if (isMembre(numero) && (isMembreActif(numero))) {
				System.out.println("Validé");
			} else if ((isMembre(numero) && !isMembreActif(numero))) {
				System.out.println("Membre suspendu ");
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
	 * @return Membre
	 */
	public static Membre getMembreParCourriel(String courriel) {
		ArrayList<Membre> LMembres = RepertoireClient.getListeMembres();
		for (Membre m : LMembres) {

			if (courriel.equalsIgnoreCase(m.getCourriel())) {
				return m;
			}
		}
		return null;

	}

	/*
	 * Cette méthode est utilisé par l'application mobile pour vérifier le numéro de
	 * membre lorsqu'il entre son courriel
	 */
	public static Membre verifierMembreCourriel() {
		scan = new Scanner(System.in);
		Membre membre;
		String courriel;
		long numero = 0;
		try {
			courriel = scan.nextLine();
			membre = getMembreParCourriel(courriel);
			if (membre!=null)
			numero = membre.getNumero();
			if (isMembre(numero) && (isMembreActif(numero))) {
				System.out.println("Validé");
				System.out.println("Nom du membre : " + membre.getNom());
				System.out.println("Numéro du membre : " + membre.getNumero());
				System.out.println("[Code QR à scanner]");
				System.out.println("----------------------------------------");
				return membre;

			} else if ((isMembre(numero) && !isMembreActif(numero))) {
				System.out.println("Membre suspendu ");
				return membre;

			} else {
				System.out.println("Numéro invalide");
	
			}

		} catch (

		InputMismatchException e) {
			System.out.println("Entrée invalide");
		}
		return null;

	}

	/*
	 * Cette méthode est utilisé par l'application mobile pour générer une facture
	 * membre
	 */

	public static void factureMembre(Membre membre) {
		Service service;
		Professionnel professionnel;
		long numPro;
		String nomPro;
		Seance seance;
		String nomService;
		Date date;
		System.out.println("Nom : " + membre.getNom());
		System.out.println("Numéro unique : " + membre.getNumero());
		System.out.println("Courriel : " + membre.getCourriel());
		System.out.println("Ville : " + membre.getVille());
		System.out.println("Province : " + membre.getProvince());
		System.out.println("Code postal : " + membre.getCodePostale());

		ArrayList<Inscription> listeInscriptions = membre.getListeInscriptionsParMembre();
		for (Inscription i : listeInscriptions) {
			seance = i.getSeance();
			date = seance.getDate();
			service = seance.getService();
			numPro = service.getNumero();
			professionnel = Professionnel.getProfessionnel(numPro);
			nomPro = professionnel.getNom();
			nomService = service.getNomService();

			System.out.println("Date du service : " + date);
			System.out.println("Nom du professionnel : " + nomPro);
			System.out.println("Nom du service : " + nomService);
			System.out.println("--------------------------------");

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
		System.out.println("Statut de membre : " + this.getStatut());

	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public ArrayList<Inscription> getListeInscriptionsParMembre() {
		return listeInscriptionsParMembre;
	}

	public void setListeInscriptionsParMembre(ArrayList<Inscription> listeInscriptionsParMembre) {
		this.listeInscriptionsParMembre = listeInscriptionsParMembre;
	}

}
