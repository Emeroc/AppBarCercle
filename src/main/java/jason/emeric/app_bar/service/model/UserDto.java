package jason.emeric.app_bar.service.model;

public class UserDto {
	
	private String Nom;
	private String Prenom;
	private String Année;
	private String email;
	
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

}
