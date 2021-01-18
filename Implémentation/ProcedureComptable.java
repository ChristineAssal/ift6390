package gym;


/**
 * Cette classe s'occupe de la génération des fichiers TEF et de la production de rapport synthèse
 * @author Christine Assal
 *
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProcedureComptable {

	public static void genererTEF() {
		ArrayList<Seance> listeSeancesParService = new ArrayList<>();
		ArrayList<Inscription> listeInscriptionsParSeance = new ArrayList<>();

		Seance seance;
		int inscriptions = 0;
		double fraisParSeance = 0.0;
		double fraisSeanceTotal = 0.0;
		double fraisTotal;

		for (Professionnel p : RepertoireClient.getListeProfessionnels()) {
			fraisTotal = 0.0;
			System.out.println("Nom du professionnel : " + p.getNom());
			System.out.println("Numéro du professionnel : " + p.getNumero());
			fraisTotal = 0.0;

			for (Service service : p.getListeServicesParProf()) {
				listeSeancesParService = service.getListeSeancesParService();
				fraisParSeance = 0.5 * service.getFrais();
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DAY_OF_MONTH, -7);
				Date dateDebutSemaine = c.getTime();
				c.setTime(dateDebutSemaine);
				String dateDebutSemaineS = formatter.format(dateDebutSemaine);

				while (dateDebutSemaine.compareTo(date) <= 0) {
					for (Seance sc : listeSeancesParService) {
						Date dateSeance = sc.getDate();
						String dateSeanceS = formatter.format(dateSeance);

						if (dateSeance.compareTo(date) <= 0) {

							if (dateDebutSemaineS.equalsIgnoreCase(dateSeanceS)) {
								listeInscriptionsParSeance = sc.getListeInscriptionsParSeance();
								for (Inscription ins : listeInscriptionsParSeance) {
									if (ins.isConfirmer() == true)
										inscriptions++;
								}
								fraisSeanceTotal += inscriptions * fraisParSeance;

							}

						}

					}
					c.add(Calendar.DAY_OF_MONTH, 1);
					dateDebutSemaine = c.getTime();
					c.setTime(dateDebutSemaine);
					dateDebutSemaineS = formatter.format(dateDebutSemaine);

				}
				fraisTotal += fraisSeanceTotal;
			}

			System.out.println("Montant à transférer : $ " + fraisTotal);
		}

	}

	public static void produireRapport() {
		int nbProfessionnel = 0;
		int totalService = 0;
		double totalFrais = 0.0;
		ArrayList<Inscription> listeInscriptionsParSeance = new ArrayList<>();

		Professionnel professionnel = null;

		System.out.println("Liste des professionnels qui doivent être payé cette semaine : ");
		System.out.println("------------------------------------------------------ ");

		for (Professionnel p : RepertoireClient.getListeProfessionnels()) {
			int nbService = 0;
			double frais = 0.0;
			ArrayList<Service> listeServicesParProf = p.getListeServicesParProf();
			for (Service service : listeServicesParProf) {
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date date = new Date();
				Date dateDeb = null;
				Date dateFin = null;
				try {
					dateDeb = format.parse(service.getDateDebut());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					dateFin = format.parse(service.getDateFin());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (date.compareTo(dateDeb) >= 0 && date.compareTo(dateFin) <= 0) {
					professionnel = p;
					nbService++;
					frais += service.getFrais();

				}

			}
			if (professionnel != null) {
				System.out.println("Professionnel : " + professionnel.getNom());
				System.out.println("Nombre de service offert par ce professionnel : " + nbService);
				System.out.println("Total des frais : $ " + frais);
				System.out.println("------------------------------------------------------ ");
			}
			nbProfessionnel++;
			totalService += nbService;
			totalFrais += frais;

		}

		System.out.println("Nombre total des professionnel qui ont fourni des services : " + nbProfessionnel);
		System.out.println("Nombre total de services offerts : " + totalService);
		System.out.println("Total des frais : $" + totalFrais);
		System.out.println("-------------------------------------------------------------------------------- ");

	}
}

