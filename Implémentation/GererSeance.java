package gym;


/*
 * Cette classe s'occupe de l'incription et de la confirmation de présences de membres aux séances de service.
 * @author Christine Assal
 *
 */

/* Référence :  https://www.baeldung.com/java-get-day-of-week */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GererSeance {

	static Scanner scan = new Scanner(System.in);
	static long codeService;

	public static Membre identifier() {
		Membre membre;
		long numero;

		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Veillez entrer le numéro de membre ");
			numero = scan.nextLong();
			membre = Membre.getMembre(numero);
			return membre;

		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}
		return null;
	}

	/*
	 * Cette méthode s'occupe de l'inscription d'un membre à une séance de service
	 * 
	 * @param membre
	 */
	public static void inscription(Membre membre) {
		Date date = new Date();
		int jourI;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		jourI = cal.get(Calendar.DAY_OF_WEEK);
		DateFormat formatter = new SimpleDateFormat("EEEE");
		String jourS = formatter.format(date);

		if (jourS.equalsIgnoreCase("monday"))
			jourS = "lundi";
		else if (jourS.equalsIgnoreCase("tuesday"))
			jourS = "mardi";
		else if (jourS.equalsIgnoreCase("wednesday"))
			jourS = "mercredi";
		else if (jourS.equalsIgnoreCase("thursday"))
			jourS = "jeudi";
		else if (jourS.equalsIgnoreCase("friday"))
			jourS = "vendredi";
		else if (jourS.equalsIgnoreCase("saturday"))
			jourS = "samedi";
		else if (jourS.equalsIgnoreCase("sunday"))
			jourS = "dimanche";

		String codeSeance;
		Service service;
		Seance seance = null;
		long numero;
		String jour;
		String ouiNon;
		String commentaire = "";
		ArrayList<Service> LServices = RepertoireService.getListeServices();
		ArrayList<Seance> LSeances;

		try {

			Scanner scan2 = new Scanner(System.in);

			numero = membre.getNumero();
			ArrayList<Inscription> LInscriptionsParMembre = membre.getListeInscriptionsParMembre();
			if (Membre.isMembreActif(numero)) {
				System.out.println("Voici le répértoire de services.");
				System.out.println("---------------------------------------");
				for (Service s : LServices) {
					s.afficherService();
					System.out.println("---------------------------------------");
				}
				System.out.println("Veillez indiquer le code de service choisi");
				codeService = scan.nextLong();
				for (Service s : LServices) {
					if (s.getCodeService() == codeService) {
						service = s;
						LSeances = service.getListeSeancesParService();
						System.out.println("Voici les séances de ce service disponible aujourd'hui  : ");
						for (Seance sc : LSeances) {
							if (sc.getJour().equalsIgnoreCase(jourS)) {
								sc.afficherSance();
								break;
							}

						}
						System.out.println("Veuillez inidiquer le code la séance choisi: ");
						codeSeance = scan2.nextLine();
						for (Seance sc1 : LSeances) {
							if (sc1.getCodeSeance().equalsIgnoreCase(codeSeance)) {
								seance = sc1;

							}

						}
						ArrayList<Inscription> LInscriptionsParSeance = seance.getListeInscriptionsParSeance();
						if (LInscriptionsParSeance.size() < service.getCapacite()) {
							System.out.println("Avez vous des commentaires ?");
							ouiNon = scan2.nextLine();
							if (ouiNon.equals("oui")) {
								System.out.println("Entrez votre commentaire :");
								commentaire = scan2.nextLine();
							}
							Inscription inscirption = new Inscription(seance, membre, service.getNumero(),
									seance.getCodeSeance(), commentaire, false);
							LInscriptionsParSeance.add(inscirption);
							seance.setListeInscriptionsParSeance(LInscriptionsParSeance);
							LInscriptionsParMembre.add(inscirption);
							membre.setListeInscriptionsParMembre(LInscriptionsParMembre);
							System.out.println(
									"L'inscription a été effectuée avec succés. Vous devez confirmer votre présence et payer les frais avant le début de la séance.");

						} else {
							System.out.println("Il ne reste plus de places disponibles dans cette séance. ");
						}

					}
				}
			} else if (Membre.isMembre(numero)) {
				System.out.println("Membre suspendu");
			} else {
				System.out.println("Numéro invalide");

			}
		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}

	/*
	 * Cette méthode s'occupe de la confirmation des présences de memebres
	 */
	public static void confirmation() {
		long numero;
		Service service = null;
		Membre membre;
		String boucle;
		String codeSeance;
		boolean valide = true;
		Seance seance = null;
		ArrayList<Service> LServices = RepertoireService.getListeServices();
		ArrayList<Seance> LSeances = null;
		try {
			Scanner scan2 = new Scanner(System.in);
			RepertoireService.consulterRepertoire();
			System.out.println("Veuillez inidiquer le code de la séance pour confirmer la présence : ");
			codeSeance = scan2.nextLine();
			for (Service sv : LServices) {
				LSeances = sv.getListeSeancesParService();
				for (Seance s : LSeances) {
					if (s.getCodeSeance().equals(codeSeance)) {
						service = sv;
						seance = s;
						valide = true;
						break;

					} else {
						valide = false;
					}
				}
			}
			if (!valide) {
				System.out.println("Ce code est invalide");
				return;
			}

			do {
				System.out.println("Veillez entrer le numéro de membre: ");
				numero = scan.nextLong();
				membre = Membre.getMembre(numero);
				if (Membre.isMembreActif(numero)) {
					ArrayList<Inscription> LInscriptionsParSeance = seance.getListeInscriptionsParSeance();
					if (LInscriptionsParSeance.size() == 0) {
						System.out.println("Il n'y a aucune inscription à cette séance. ");
						return;

					}
					for (Inscription i : LInscriptionsParSeance) {

						if (i.getMembre() == membre) {
							i.setConfirmer(true);
							System.out.println("La présence a été confirmé avec succés. ");
							i.afficherInscription();
							System.out.println("*Les frais à payer sont de : $" + service.getFrais());
						} else {
							System.out.println("Ce membre n'est pas inscrit à cette séance. ");

						}
					}

				} else if (Membre.isMembre(numero)) {
					System.out.println("Membre suspendu");
					return;
				} else {
					System.out.println("Numéro invalide");
					return;

				}
				System.out.println("Voulez vous confirmer la présence d'autres membres à cette séance ?");
				boucle = scan2.nextLine();
			} while (boucle.equalsIgnoreCase("oui"));

		} catch (InputMismatchException e) {
			System.out.println("Entrée invalide");
		}
	}
}