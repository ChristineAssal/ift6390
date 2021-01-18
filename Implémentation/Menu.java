package gym;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Cette classe contient le main. Elle s'occupe de l'affichage des différents menus.
 * @author Christine Assal
 *
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		RepertoireClient.hardCoded();
		int version;
		int optionV1;
		int optionV2;
		String membreOuPro = null;
		String optionV1ABC;
		String retour = null;

		// Procédure comptable vendredi minuit (samedi?)
		Date date = new Date();
		int jourI;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		jourI = cal.get(Calendar.DAY_OF_WEEK);
		DateFormat formatter = new SimpleDateFormat("EEEE");
		String jourS = formatter.format(date);
		if (jourS.equalsIgnoreCase("saturday")) {
			ProcedureComptable.genererTEF();
			ProcedureComptable.produireRapport();

		}

		try {
			Scanner scan = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);

			System.out.println("Veillez choisir une version du logicel en entrant le chiffre correspondant : ");
			System.out.println("1. Version de l'agent");
			System.out.println("2. Version de l'application mobile");
			version = scan.nextInt();

			// Version agent

			if (version == 1) {

				do {
					System.out.println("Veillez choisir une option en entrant le chiffre correspondant:  ");
					System.out.println("1. Ajouter / Mettre à jour/ supprimer un client");
					System.out.println("2. Ajouter/ Mettre à jour/ supprimer un service ");
					System.out.println("3. Vérifier un membre");
					System.out.println("4. Vérifier un professionnel");
					System.out.println("5. Consulter le répértoire de services");
					System.out.println("6. Inscription à un service ");
					System.out.println("7. Confirmer la présence d'un membre à une séance");
					System.out.println("8. Production du rapport de synthèse à la demande du gérant");

					scan = new Scanner(System.in);
					optionV1 = scan.nextInt();
					if (optionV1 == 1) {
						System.out.println("Veillez choisir une option en indiquant la lettre correspondante: ");
						System.out.println("a. Ajouter un client");
						System.out.println("b. Mettre à jour un client");
						System.out.println("c. Supprimer un client ");
						optionV1ABC = scan.next();
						if (optionV1ABC.equals("a")) {
							GererClient.creerClient();
						} else if (optionV1ABC.equals("b")) {
							GererClient.miseAJourClient();
						} else if (optionV1ABC.equals("c")) {
							GererClient.supprimerClient();

						}

					} else if (optionV1 == 2) {
						System.out.println("Veillez choisir une option en indiquant la lettre correspondante: ");
						System.out.println("a. Ajouter un service");
						System.out.println("b. Mettre à jour un service");
						System.out.println("c. Supprimer un service ");
						optionV1ABC = scan.next();
						if (optionV1ABC.equals("a")) {
							GererService.creerService();
						} else if (optionV1ABC.equals("b")) {
							GererService.miseAjourService();
						} else if (optionV1ABC.equals("c")) {
							GererService.supprimerService();

						}
					} else if (optionV1 == 3) {
						Membre.verifierMembre();
					} else if (optionV1 == 4) {
						Professionnel.verifierProfessionnel();
					} else if (optionV1 == 5) {
						RepertoireService.consulterRepertoire();
					} else if (optionV1 == 6) {
						Membre membre = GererSeance.identifier();
						GererSeance.inscription(membre);
					} else if (optionV1 == 7) {
						GererSeance.confirmation();
					} else if (optionV1 == 8) {
						ProcedureComptable.produireRapport();
					}
					System.out.println("Voulez-vous retourner au menu principal ?");
					retour = scan.next();
				} while (retour.equals("oui"));

				// Version application mobile

			} else if (version == 2) {
				System.out.println("Êtes vous un membre ou un professionnel ?");

				membreOuPro = scan2.nextLine();

				// Version application mobile : Membre

				if (membreOuPro.equalsIgnoreCase("membre")) {
					System.out.println("Veillez entrer votre courriel pour se loguer sur l'application #GYM");

					Membre membre = Membre.verifierMembreCourriel();

					do {
						System.out.println("Veillez choisir une option en entrant le chiffre correspondant:  ");
						System.out.println(
								"1. Obtenir mon code QR pour accéder au centre ou confirmer ma présence avec un professionnel");
						System.out.println("2. Consulter le répértoire de service");
						System.out.println("3. Inscription à un service  ");
						System.out.println("4. Consulter ma facture membre  ");

						scan = new Scanner(System.in);
						optionV2 = scan.nextInt();

						if (optionV2 == 1) {
							System.out.println("Nom du membre : " + membre.getNom());
							System.out.println("Numéro du membre : " + membre.getNumero());
							System.out.println("[Code QR à scanner]");

						} else if (optionV2 == 2) {
							RepertoireService.consulterRepertoire();
						}

						else if (optionV2 == 3) {
							GererSeance.inscription(membre);
						}

						else if (optionV2 == 4) {
							Membre.factureMembre(membre);
						}
						System.out.println("Voulez-vous retourner au menu principal ?");
						retour = scan.next();
					} while (retour.equals("oui"));

					// Version application mobile : Professionnel
				} else if (membreOuPro.equalsIgnoreCase("professionnel")) {
					System.out.println("Veillez entrer votre courriel pour se loguer sur l'application #GYM");

					Professionnel professionnel = Professionnel.verifierProCourriel();

					do {
						System.out.println("Veillez choisir une option en entrant le chiffre correspondant:  ");
						System.out.println("1. Obtenir mon code QR pour accéder au centre ");
						System.out.println("2. Confirmer la présence des membres à mes séances");
						System.out.println("3. Consulter ma facture professionnel");

						scan = new Scanner(System.in);
						optionV2 = scan.nextInt();

						if (optionV2 == 1) {
							System.out.println("Nom du professsionnel : " + professionnel.getNom());
							System.out.println("Numéro du professionnel : " + professionnel.getNumero());
							System.out.println("[Code QR à scanner]");

						} else if (optionV2 == 2) {
							GererSeance.confirmation();

						} else if (optionV2 == 3) {
							Professionnel.factureProfessionnel(professionnel);

						}
						System.out.println("Voulez-vous retourner au menu principal ?");
						retour = scan.next();
					} while (retour.equals("oui"));

				} else {
					System.out.println("Entrée invalide");
				}
			}
		} catch (

		InputMismatchException e) {
			System.out.println("Entrée invalide");
		}

	}
}
