package model;


public class Habitacions{
	
	protected int numhabitacio, capacitat;

	
	public Habitacions(int numhabitacio) {
		super();
		this.numhabitacio = numhabitacio;
		
	}

	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}

	public int getNumhabitacio() {
		return numhabitacio;
	}

	public void setNumhabitacio(int numhabitacio) {
		this.numhabitacio = numhabitacio;
	}

	public int getCapacitat() {
		return capacitat;
	}
	
	public void setNovaCapacitat(int novacapacitat) {
		this.capacitat = novacapacitat;
	}

	
	
	
	
}