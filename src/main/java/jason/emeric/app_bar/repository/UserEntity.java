package jason.emeric.app_bar.repository;




public class UserEntity {

	
	private Integer id;
	
	private String Nom;
	private String Prenom;
	private String Année;
	private String email;
	private double montant;

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getAnnée() {
		return Année;
	}

	public void setAnnée(String année) {
		Année = année;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
