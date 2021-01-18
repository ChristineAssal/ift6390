package gym;

/**
 * Cette classe permet d'instancier des inscriptions.
 * @author Christine Assal
 *
 */

import java.util.Date;

public class Inscription {
	private Seance seance;
	private Membre membre;
	private long numProfessionnel;
	private String codeSeance;
	private String commentaire;
	private boolean isConfirmer;

	public Inscription(Seance seance, Membre membre, long numProfessionnel, String codeSeance, String commentaire,
			boolean isConfirmer) {
		this.seance = seance;
		this.membre = membre;
		this.numProfessionnel = numProfessionnel;
		this.codeSeance = codeSeance;
		this.commentaire = commentaire;
		this.isConfirmer = isConfirmer;
	}

	public void afficherInscription() {
		Date date = new Date();
		System.out.println("Date et heure actuelles : " + date);
		System.out.println("Numéro du professionnel : " + numProfessionnel);
		System.out.println("Numéro de membre : " + membre.getNumero());
		System.out.println("Code de la séance de service : " + codeSeance);
		System.out.println("Commentaires : " + commentaire);
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public long getNumProfessionnel() {
		return numProfessionnel;
	}

	public void setNumProfessionnel(long numProfessionnel) {
		this.numProfessionnel = numProfessionnel;
	}

	public String getCodeSeance() {
		return codeSeance;
	}

	public void setCodeSeance(String codeService) {
		this.codeSeance = codeService;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public boolean isConfirmer() {
		return isConfirmer;
	}

	public void setConfirmer(boolean isConfirmer) {
		this.isConfirmer = isConfirmer;
	}

}
