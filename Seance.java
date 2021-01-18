package gym;

/**
 * Cette classe permet d'instancier des Seance.
 * @author Christine Assal
 *
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Seance {
	private Service service;
	private Date date;
	private String jour;
	private String codeSeance;
	private ArrayList<Inscription> listeInscriptionsParSeance = new ArrayList<>();

	public Seance(Service service, Date date, String codeSeance, String jour) {
		this.service = service;
		this.date = date;
		this.codeSeance = codeSeance;
		this.jour = jour;
	}

	public void afficherSance() {
		System.out.println("Code de la séance: " + codeSeance);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);
		System.out.println("Date de la sécance : " + strDate);
		System.out.println("Jour de la semaine : " + jour);
	}

	public Service getService() {
		return service;
	}

	public String getJour() {
		return jour;
	}

	public String getCodeSeance() {
		return codeSeance;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public void setCodeSeance(String codeSeance) {
		this.codeSeance = codeSeance;
	}

	public ArrayList<Inscription> getListeInscriptionsParSeance() {
		return listeInscriptionsParSeance;
	}

	public void setListeInscriptionsParSeance(ArrayList<Inscription> listeInscriptionsParSeance) {
		this.listeInscriptionsParSeance = listeInscriptionsParSeance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
