package hotel.classes;

import java.time.LocalDate;

public class Reserves{
	
	protected Clients client;
	protected int numeropersones;
	protected Habitacions habitacio;
	protected LocalDate dataentrada;
	protected LocalDate sortida;	
	
	public Reserves(Clients client) {
		super();
		this.client = client;
	}
	
	

	public int getNumeropersones() {
		return numeropersones;
	}



	public void setNumeropersones(int numeropersones) {
		this.numeropersones = numeropersones;
	}



	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	public Habitacions getHabitacio() {
		return habitacio;
	}

	public void setHabitacio(Habitacions habitacio) {
		this.habitacio = habitacio;
	}

	public LocalDate getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(LocalDate dataentrada) {
		this.dataentrada = dataentrada;
	}

	public LocalDate getSortida() {
		return sortida;
	}

	public void setSortida(LocalDate sortida) {
		this.sortida = sortida;
	}
	
	public String[] arrayReservaPendent() {
        String[] array = new String[4];
        array[0]=this.dataentrada.getDayOfMonth()+"-"+this.dataentrada.getMonthValue()+"-"+this.dataentrada.getYear();
        array[1]=client.getDni();
        array[2]=this.numeropersones+"";
        array[3]="";
        return array;
    }
	
	
	
		
	
}