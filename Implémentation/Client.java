package gym;

/*
 * Cette classe abstraite permet de créer les comptes membres et professionnels
 * des clients de #GYM. Elle regroupe tous les caractéristiques en commun entre
 * les 2 types de comptes.
 * 
 * @author Christine Assal
 *
 */

abstract public class Client {
	private long numero;
	private String nom;
	private String courriel;
	private String ville, province, codePostale;
	private String phone;
	private String membreOuProf;

	public Client(long numero, String nom, String courriel, String ville, String province, String codePostale,
			String phone, String membreOuProf) {
		this.numero = numero;
		this.nom = nom;
		this.courriel = courriel;
		this.ville = ville;
		this.province = province;
		this.codePostale = codePostale;
		this.phone = phone;
		this.membreOuProf = membreOuProf;
	}

	public Client() {
		this.numero = numero;
		this.nom = nom;
		this.courriel = courriel;
		this.ville = ville;
		this.province = province;
		this.codePostale = codePostale;
		this.phone = phone;
		this.membreOuProf = membreOuProf;
	}

	public long getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public String getCourriel() {
		return courriel;
	}

	public String getVille() {
		return ville;
	}

	public String getProvince() {
		return province;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public String getPhone() {
		return phone;
	}

	public String getMembreOuProf() {
		return membreOuProf;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMembreOuProf(String membreOuProf) {
		this.membreOuProf = membreOuProf;
	}

}
