package gym;
/**
 * Cette classe contient la liste de service.
 * @author Christine Assal
 *
 */
/* SOURCE :  https://www.baeldung.com/java-get-day-of-week */

import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class RepertoireService {

	private static ArrayList<Service> listeServices = new ArrayList<>();

	public static ArrayList<Service> getListeServices() {
		return listeServices;
	}

	public static void setListeServices(ArrayList<Service> listeServices) {
		RepertoireService.listeServices = listeServices;
	}

	public static void consulterRepertoire() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Voici le répertoire de service");
		System.out.println("---------------------------------------");
		consulterService();
		long codeService;
		scan = new Scanner(System.in);
		System.out.println("Entrer le code de service pour consulter les séances d'aujourd'hui");
		codeService = scan.nextLong();
		RepertoireService.consulterSeanceDuJour(codeService);
	}

	public static void consulterService() {

		for (Service s : listeServices) {
			s.afficherService();
			System.out.println("---------------------------------------");

		}
	}

	/*
	 * Cette méthode permet d'afficher les séances de services disponible pour le
	 * jour même
	 * 
	 * @param codeService
	 */
	public static void consulterSeanceDuJour(long codeService) {
		String seance;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate1 = formatter.format(date);
		int jour;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		jour = cal.get(Calendar.DAY_OF_WEEK);
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

		Service service = null;
		for (Service s : listeServices) {
			if (codeService == s.getCodeService()) {
				service = s;
			}
		}
		if (service == null) {
			System.out.println("Code invalide");
			return;
		}

		ArrayList<Seance> listeSeancesParService = service.getListeSeancesParService();
		for (Seance sc : listeSeancesParService) {
			String strDate2 = formatter.format(sc.getDate());
			if (strDate1.equalsIgnoreCase(strDate2)) {
				System.out.println("Voici les séances de service d'aujourdhui : ");
				sc.afficherSance();
				return;
			}
		}
		System.out.println("Ce service n'est pas offert aujourd'hui");

	}

	/*
	 * Cette méthode s'occupe de la création de service
	 * 
	 * @param dateDebut
	 * 
	 * @param dateFin
	 * 
	 * @param nom
	 * 
	 * @param heure
	 * 
	 * @param heure
	 * 
	 * @param recurrence
	 * 
	 * @param capacite
	 * 
	 * @param numero
	 * 
	 * @param codeService
	 * 
	 * @param frais
	 * 
	 * @param commentaires
	 */
	public static void creerService(String dateDebut, String dateFin, String nom, String heure, String reccurence,
			int capacite, long numero, long codeService, float frais, String commentaires) {
		Service service = new Service(dateDebut, dateFin, nom, heure, reccurence, capacite, numero, codeService, frais,
				commentaires);
		System.out.println("Le service a été créé avec succés. Voici les informations : ");
		service.afficherService();
		service.creerSeance(service);
		listeServices.add(service);
		Professionnel professionnel = Professionnel.getProfessionnel(numero);
		professionnel.ajouterService(service);
	}

	/*
	 * Cette méthode permet de mettre à jour un service en ayant une réference vers
	 * le service à mettre à jour, et le service mise à jour.
	 * 
	 * @param service
	 * 
	 * @param serviceUpdated
	 */
	public static void miseAJourService(Service service, Service serviceUpdated) {
		// Mise à jour du service dans la liste de services par professionnel
		Professionnel professionnel = new Professionnel();
		long numero = service.getNumero();
		professionnel = Professionnel.getProfessionnel(numero);
		professionnel.supprimerService(service);
		professionnel.ajouterService(serviceUpdated);

		// Mise à jour du répertoire de service
		int index = listeServices.indexOf(service);
		listeServices.set(index, serviceUpdated);
		for (int i = 0; i < listeServices.size(); i++) {
			Service s = listeServices.get(i);
			s.afficherService();
			ArrayList<Seance> ls = new ArrayList<>();
			ls = s.getListeSeancesParService();
			for (Seance c : ls) {
				c.afficherSance();
			}
		}

	}

	/*
	 * Cette méthode permer de supprimer un service du répertoire
	 * 
	 * @param code
	 */
	public static void supprimerService(long code) {
		// Supprimer le service du répertoire
		Service service = new Service();
		service = Service.getService(code);
		listeServices.remove(service);

		// Supprimer le service de la liste des servcie du professionnel
		Professionnel professionnel = new Professionnel();
		long numero = service.getNumero();
		professionnel = Professionnel.getProfessionnel(numero);
		professionnel.supprimerService(service);

	}

}
