package model;

import java.util.ArrayList;

public class Hotels {

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

	public void afegirClientArraylist(Clients client) {

		this.llistaClient.add(client);
	}

	public void afegirLlistaReservesPendents(Reserves reserves) {

		this.llistaReservesPendents.add(reserves);
	}

	public void afegirLlistaReservesConfirmades(Reserves reserves) {

		this.llistaReservesConfirmades.add(reserves);
	}

	public void afegirLlistaHabitacions(Habitacions habitacio) {
		this.llistaHabitacions.add(habitacio);
	}

	public boolean checkHabitacioLliureIAfegeix(Reserves reserva) {

		for (int i = 0; i < 2; i++) {

			for (Habitacions x : this.llistaHabitacions) {

				if (x.getCapacitat() == reserva.getNumeropersones() + i) {

					if (reserva.mirarHabitacions(this, x)) {
						return true;
					}
				}

			}

		}
		return false;
	}

	public void eliminarReservaPendent(String dni, Reserves reserva) {
		for (Reserves i : llistaReservesPendents) {
			if (i.getClient().getDni().equalsIgnoreCase(dni)
					&& i.getHabitacio().getNumhabitacio() == reserva.getHabitacio().getNumhabitacio()
					&& i.getDataentrada().equals(reserva.getDataentrada())
					&& i.getSortida().equals(reserva.getSortida())) {
				llistaReservesPendents.remove(i);
			}

		}
	}

	public ArrayList<Clients> getLlistaClientsPerPattern(String paraula) {
		ArrayList<Clients> arraylistclients = new ArrayList<Clients>();

		for (Clients c : llistaClient) {
			if (c.getNom().toLowerCase().contains(paraula.toLowerCase())
					|| c.getCognoms().toLowerCase().contains(paraula.toLowerCase())
					|| c.getDni().toLowerCase().contains(paraula.toLowerCase())) {
				arraylistclients.add(c);
			}
		}
		return arraylistclients;
	}

	public ArrayList<Reserves> getLlistaReservesPendentsConfirmades(Clients client) {
		ArrayList<Reserves> arraylistclientreserves = new ArrayList<Reserves>();

		if (llistaReservesPendents.size() > 0) {
			for (Reserves c : llistaReservesPendents) {
				if (c.getClient().getDni().toLowerCase().contains(client.getDni().toLowerCase())) {
					arraylistclientreserves.add(c);
				}
			}
		}
		if (llistaReservesConfirmades.size() > 0) {
			for (Reserves c : llistaReservesConfirmades) {
				if (c.getClient().getDni().toLowerCase().contains(client.getDni().toLowerCase())) {
					arraylistclientreserves.add(c);
				}
			}
		}
		return arraylistclientreserves;
	}

	public void eliminarReserva(Reserves reserva) {
		boolean borrarconfirmada = false;
		boolean borrarpendent = false;
		if (llistaReservesConfirmades.size() >= 0) {
			for (Reserves i : llistaReservesConfirmades) {
				System.out.println("qweqwe");
				if (i.getClient().getDni().toLowerCase().equals(reserva.getClient().getDni().toLowerCase())
						&& i.getHabitacio().getNumhabitacio() == reserva.getHabitacio().getNumhabitacio()
						&& i.getDataentrada().isEqual(reserva.getDataentrada())
						&& i.getSortida().isEqual(reserva.getSortida())) {
					borrarconfirmada = true;

				}
			}
		}
		if (llistaReservesConfirmades.size() >= 0) {
			for (Reserves i : llistaReservesPendents) {
				if (i.getClient().getDni().toLowerCase().equals(reserva.getClient().getDni().toLowerCase())
						&& i.getHabitacio().getNumhabitacio() == reserva.getHabitacio().getNumhabitacio()
						&& i.getDataentrada().isEqual(reserva.getDataentrada())
						&& i.getSortida().isEqual(reserva.getSortida())) {
					borrarpendent = true;

				}
			}
		}
		
		if(borrarconfirmada){
			llistaReservesConfirmades.remove(reserva);			
		}
		else if(borrarpendent){
			llistaReservesPendents.remove(reserva);	
		}
			
	}


}