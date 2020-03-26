package model;

import java.util.ArrayList;

public class Hotels{
	
	protected String nomhotel;
	protected ArrayList<Habitacions> llistaHabitacions = new ArrayList<Habitacions>();
	protected ArrayList<Clients> llistaClient = new ArrayList<Clients>();
	protected ArrayList<Reserves> llistaReservesPendents = new ArrayList<Reserves>();
	protected ArrayList<Reserves> llistaReservesConfirmades = new ArrayList<Reserves>();
	
	
	public Hotels() {
		super();
	}


	public String getNomhotel() {
		return nomhotel;
	}


	public void setNomhotel(String nomhotel) {
		this.nomhotel = nomhotel;
	}


	public ArrayList<Habitacions> getLlistaHabitacions() {
		return llistaHabitacions;
	}


	public void setLlistaHabitacions(ArrayList<Habitacions> llistaHabitacions) {
		this.llistaHabitacions = llistaHabitacions;
	}


	public ArrayList<Clients> getLlistaClient() {
		return llistaClient;
	}


	public void setLlistaClient(ArrayList<Clients> llistaClient) {
		this.llistaClient = llistaClient;
	}


	public ArrayList<Reserves> getLlistaReservesPendents() {
		return llistaReservesPendents;
	}


	public void setLlistaReservesPendents(ArrayList<Reserves> llistaReservesPendents) {
		this.llistaReservesPendents = llistaReservesPendents;
	}


	public ArrayList<Reserves> getLlistaReservesConfirmades() {
		return llistaReservesConfirmades;
	}


	public void setLlistaReservesConfirmades(ArrayList<Reserves> llistaReservesConfirmades) {
		this.llistaReservesConfirmades = llistaReservesConfirmades;
	}
	
	public void afegirClientArraylist(Clients client){
		
		this.llistaClient.add(client);
	}
	
	public void afegirLlistaReservesPendents(Reserves reserves){
		
		this.llistaReservesPendents.add(reserves);
	}
	
	public void afegirLlistaReservesConfiramdes(Reserves reserves){
		
		this.llistaReservesPendents.add(reserves);
	}
	
	public void afegirLlistaHabitacions(Habitacions habitacio){
		this.llistaHabitacions.add(habitacio);	
	}


	public  boolean checkHabitacioLliureIAfegeix(Reserves reserva) {
		
		for (int i = 0; i < 2; i++) {
			
			for (Habitacions x: this.llistaHabitacions) {
				
				if(x.getCapacitat() == reserva.getNumeropersones()+i) {
					
					if(reserva.mirarHabitacions(this, x)){
						return true;
					}
				}
				
			}
			
		}		
		return false;
	}
	

	
	
	
	
}