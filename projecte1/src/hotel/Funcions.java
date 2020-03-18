package hotel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import com.toedter.calendar.JCalendar;

import hotel.classes.Clients;

public class Funcions{
	
	
	public static boolean regexDNI(String dni) {
		
		if (dni.matches("[0-9]{8}[A-Z a-z]")) {			
			return true;			
		}
		
		return false;
		
	}

	public static boolean regexNom(String nom) {
		
		if (nom.matches("^[ A-Za-z]+$")) {		
			return true;			
		}		
		return false;		
	}

	public static boolean regexCognoms(String cognom) {
		
		if(cognom.matches("^[ A-Za-z]+$")) {			
			return true;		
		}
		return false;
	}

	public static boolean regexNumPersones(String numpersones ) {

		if(numpersones.matches("[0-9]{1,3}")) {			
			return true;			
		}
		return false;		
	}

	public static boolean regexNumNits(String numnits ) {
		
		if(numnits.matches("[0-9]{1,3}")) {			
			return true;			
		}
		return false;
	}

	public static boolean habilitarBotoPanell2(boolean camp1, boolean camp2, boolean camp3, boolean camp4, boolean camp5) {
		
		if(camp1 && camp2 && camp3 && camp4 && camp5) {
			return true;			
		}
		
		return false;
		
	}

	public static boolean clientJaHaFetReserva(String client, ArrayList<Clients> llistaClient) {
		
		for (Clients i : llistaClient) {
			
			if (i.getDni().equalsIgnoreCase(client)) {
				return true;			
			}			
		}
		return false;
	}

	public static Clients agafarClientRegistrar(String dni, ArrayList<Clients> arrayList) {
		
		for (Clients i : arrayList) {
			
			if(i.getDni().equalsIgnoreCase(dni)) {
				
				return i;
			}
			
		}
		
		return null;

	}

	public static LocalDate dataEntradaJCalendar(JCalendar jccalendari) {		
		Long dataNano = jccalendari.getDate().getTime();
        LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
        return data;       

	}
	
	
}