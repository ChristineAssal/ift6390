package gym;

/**
 * Cette classe permet de créer un service.
 * Elle créé aussi des seances offertes aux périodes spécifiés.
 * @author Christine Assal
 *
 */

/* Référence : https://gist.github.com/WardBenjamin/e91e57bfc0614bc2b0d4e084875ffe04 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Service {
	private String nomService;
	private String dateDebut;
	private String dateFin;
	private String heure;
	private String recurrence;
	private int capacite;
	private long numero;
	private long codeService;
	private float frais;
	private String commentaires;
	private static ArrayList<Seance> listeSeancesParService = new ArrayList<>();

	public Service(String dateDebut, String dateFin, String nomService, String heure, String reccurence, int capacite,
			long numero, long codeService, float frais, String commentaires) {
		this.setNomService(nomService);
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.heure = heure;
		this.recurrence = reccurence;
		this.capacite = capacite;
		this.numero = numero;
		this.codeService = codeService;
		this.frais = frais;
		this.commentaires = commentaires;

	}

	public Service() {

	}

	public void afficherService() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);
		System.out.println("Date actuelle : " + strDate);
		System.out.println("Date de début : " + dateDebut);
		System.out.println("Date de fin : " + dateFin);
		System.out.println("Heure du service : " + heure);
		System.out.println("Récurrence hebdomadaire des séances : " + recurrence);
		System.out.println("Capacité maximale : " + capacite);
		System.out.println("Numéro du professionnel : " + numero);
		System.out.println("Code de service : " + codeService);
		System.out.println("Frais de service : " + frais);
		System.out.println("Commentaires : " + commentaires);
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public long getCodeService() {
		return codeService;
	}

	public void setCodeService(long newCodeService) {
		this.codeService = newCodeService;
	}

	public float getFrais() {
		return frais;
	}

	public void setFrais(float frais) {
		this.frais = frais;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public ArrayList<Seance> getListeSeancesParService() {
		return listeSeancesParService;
	}

	public static void setListeSeancesParService(ArrayList<Seance> listeSeancesParService) {
		Service.listeSeancesParService = listeSeancesParService;
	}

	/*
	 * @param code
	 * 
	 * @return Service
	 */
	public static Service getService(long code) {
		ArrayList<Service> LServices = RepertoireService.getListeServices();
		for (Service s : LServices) {
			if (code == s.codeService) {
				return s;
			}
		}
		System.out.println("Code invalide");
		return null;

	}

	/*
	 * Cette méthode retourne le nombre de semaines entre les dates de début et de
	 * fin
	 * 
	 * @param debut
	 * 
	 * @param fin
	 * 
	 * @return nbSemaines
	 */
	public static int nbSemaines(Date debut, Date fin) {
		Calendar a = new GregorianCalendar();
		Calendar b = new GregorianCalendar();

		a.setTime(debut);
		b.setTime(fin);

		return b.get(Calendar.WEEK_OF_YEAR) - a.get(Calendar.WEEK_OF_YEAR);
	}

	/*
	 * Cette méthode retourne la date correspondant à j2
	 * 
	 * @param d1
	 * 
	 * @param j1
	 * 
	 * @param j2
	 * 
	 * @return d2
	 */
	public static Date getDate(Date d1, String j1, String j2) {
		int nbJour = 0;

		if (j1.equalsIgnoreCase(j2)) {
			return d1;
		}
		String[] jourSemaine = { "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche" };
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {

				if (j1.equalsIgnoreCase(jourSemaine[i]) && j2.equalsIgnoreCase(jourSemaine[j])) {
					// trouver le nombre de jour entre j1 et j2
					nbJour = j - i;

				}

			}

		}

		Calendar c = Calendar.getInstance();
		c.setTime(d1);

		// aditionner le nombre de jour trouvé à d1
		c.add(Calendar.DAY_OF_MONTH, nbJour);

		Date d2 = c.getTime();

		return d2;

	}

	/*
	 * Cette méthode créé les séances de services dans les périodes spécifiés par la
	 * récurrence hebdomadaire en trouvant le nombre de semaines entre les dates de
	 * début et fin du service et en trouvant la date correspondant à chaque jour de
	 * semaine.
	 * 
	 * @param service
	 */
	public static void creerSeance(Service service) {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		Date dateDeb = null;
		try {
			dateDeb = format.parse(service.getDateDebut());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dateFin = null;
		try {
			dateFin = format.parse(service.getDateFin());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int nbSemaines = nbSemaines(dateDeb, dateFin);

		String[] recurrenceTab = service.getRecurrence().split(" ");
		String numero = service.getNumero() + "";
		String digitsProf = numero.substring(7, 9);
		String codeService = service.getCodeService() + "";
		String codeSeance;
		int numSeance = 9;
		int compteur = 0;
		String d = recurrenceTab[0];
		Calendar c = Calendar.getInstance();

		while (compteur < nbSemaines) {
			for (int i = 0; i < recurrenceTab.length; i++) {
				c.setTime(dateDeb);
				c.add(Calendar.DAY_OF_MONTH, 7 * compteur);
				Date d1 = c.getTime();
				numSeance++;
				codeSeance = "" + codeService + numSeance + digitsProf;
				date = getDate(d1, d, recurrenceTab[i]);
				Seance seance = new Seance(service, date, codeSeance, recurrenceTab[i]);
				service.listeSeancesParService.add(seance);
			}
			compteur++;

		}

	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

}
