package model;

public class Clients{
	
	protected String dni, nom, cognoms;

	public Clients() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}
	
@Override
public String toString() {
	return this.nom.substring(0,1)+". "+this.getCognoms()+" - "+this.getDni();		
}
	
	
	

}

