package controller;

import java.awt.font.NumericShaper;
import java.security.DrbgParameters.Capability;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.IIOByteBuffer;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import hotel.Hotel;
import model.*;

public class Controller {

	private Hotels hotel;

	public Controller() {
		hotel = new Hotels();
	}

	public Hotels getHotel() {
		return hotel;
	}

	public void setHotel(Hotels hotel) {
		this.hotel = hotel;
	}

	public static boolean regexDNI(String dni) {

		boolean correcte = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher matcher = pattern.matcher(dni);

		if (matcher.matches()) {

			String lletra = matcher.group(2);

			String lletres = "TRWAGMYFPDXBNJZSQVHLCKE";

			int index = Integer.parseInt(matcher.group(1));

			index = index % 23;

			String reference = lletres.substring(index, index + 1);

			if (reference.equalsIgnoreCase(lletra)) {

				correcte = true;

			} else {

				correcte = false;

			}

		} else {

			correcte = false;

		}

		return correcte;

	}

	public static boolean regexNom(String nom) {
		if (nom.matches("^[ A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ]+$")) {
			return true;
		}
		return false;
	}

	public static boolean regexCognoms(String cognom) {
		if (cognom.matches("^[ A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ]+$")) {
			return true;
		}
		return false;
	}

	public static boolean regexNumPersones(String numpersones) {
		if (numpersones.matches("[1-6]")) {
			return true;
		}
		return false;
	}

	public static boolean regexNumNits(String numnits) {

		if (numnits.matches("[1234567890]{1,2}")) {
			if (Integer.parseInt(numnits) < 31) {
				return true;
			}
		}
		return false;
	}

	public static boolean habilitarBotoPanell2(boolean camp1, boolean camp2, boolean camp3, boolean camp4,
			boolean camp5) {

		if (camp1 && camp2 && camp3 && camp4 && camp5) {
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

	public static Clients agafarClientRegistrat(String dni, ArrayList<Clients> arrayList) {

		for (Clients i : arrayList) {

			if (i.getDni().equalsIgnoreCase(dni)) {

				return i;
			}

		}

		return null;

	}

	public static LocalDate pasarDateALocalDate(Date date) {

		Long dataNano = date.getTime();
		LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
		return data;

	}

	public static void afegirReserva(Clients client, String persones, LocalDate data, boolean clientregistrat,
			Hotels hotel, DefaultTableModel gestiotablemodel1) {

	}

	public void posarHabitacions(Hotels hotel) {

		Random r = new Random();
		int numpersones;

		for (int i = 0; i < 50; i++) {

			numpersones = r.nextInt(6)+1;

			Habitacions habitacio = new Habitacions(i + 1);
			habitacio.setCapacitat(numpersones);
			hotel.afegirLlistaHabitacions(habitacio);

		}

	}

	public boolean checkHabitacioExistent(Hotels hotel2, String numhabitacio) {

		for (Habitacions i : hotel2.getLlistaHabitacions()) {
			if (i.getNumhabitacio() == Integer.parseInt(numhabitacio)) {
				return true;
			}
		}
		return false;
	}

	public static boolean regexNumeroHabitacio(String numerohab) {

		if (numerohab.matches("[1234567890]{1,3}")) {
			if (Integer.parseInt(numerohab) < 601) {
				return true;
			}
		}
		return false;
	}

	public static boolean habilitarBotoPanell3(boolean camp1p3, boolean camp2p3) {
		if (camp1p3 & camp2p3) {
			return true;
		}
		return false;
	}

	public static boolean checkSiHabitacioExist(Hotels hotel2, String nhabitacio) {

		for (Habitacions i : hotel2.getLlistaHabitacions()) {

			if (i.getNumhabitacio() == Integer.parseInt(nhabitacio)) {
				return true;
			}

		}

		return false;
	}

	public static void afegirHabitacióNova(String numero, String quantitat, Hotels hotel) {

		Habitacions nova = new Habitacions(Integer.parseInt(numero));
		nova.setCapacitat(Integer.parseInt(quantitat));

		hotel.afegirLlistaHabitacions(nova);

	}

	public static boolean checkIfHabitacioEstaOcupada(String habitacio, Hotels hotel) {

		if (checkIfHabitacioReservadaPendents(habitacio, hotel) || checkIfHabitacioIsConfirmada(habitacio, hotel)) {
			return true;
		}

		return false;

	}

	public static boolean checkIfHabitacioReservadaPendents(String numhabitació, Hotels hotel) {

		for (Reserves i : hotel.getLlistaReservesPendents()) {
			if (i.getHabitacio().getNumhabitacio() == Integer.parseInt(numhabitació)) {
				return true;
			}
		}

		return false;
	}

	public static boolean checkIfHabitacioIsConfirmada(String numhabitacio, Hotels hotel) {

		for (Reserves i : hotel.getLlistaReservesConfirmades()) {
			if (i.getHabitacio().getCapacitat() == Integer.parseInt(numhabitacio)) {
				return true;
			}
		}
		return false;
	}

	public static String getCapacitatActual(String num, Hotels hotel) {

		for (Habitacions i : hotel.getLlistaHabitacions()) {

			if (i.getNumhabitacio() == Integer.parseInt(num)) {
				return Integer.toString(i.getCapacitat());
			}

		}

		return null;
	}

	public static void afegirNovaCapacitatHabitacio(String quantiat, String num, Hotels hotel) {

		for (Habitacions i : hotel.getLlistaHabitacions()) {
			if (i.getNumhabitacio() == Integer.parseInt(num)) {
				i.setNovaCapacitat(Integer.parseInt(quantiat));
			}
		}

	}

	public static boolean checkSiHiaUnaHabitacióDisponible(String numpersones, Hotels hotel) {

		for (Habitacions i : hotel.getLlistaHabitacions()) {

			if (i.getCapacitat() == Integer.parseInt(numpersones)
					|| i.getCapacitat() == Integer.parseInt(numpersones) + 1) {

				for (Reserves x : hotel.getLlistaReservesConfirmades()) {

					if (i.getNumhabitacio() == x.getHabitacio().getNumhabitacio()) {
						return false;
					}

				}

			}

		}

		return true;
	}

	public static Reserves afegirDadesReserva(String numpersones, LocalDate data, Reserves reserva, String numnits) {

		reserva.setNumeropersones(Integer.parseInt(numpersones));
		reserva.setDataentrada(data);
		reserva.setSortida(data.plusDays(Integer.parseInt(numnits)));
		return reserva;
	}

	public static boolean crearClientIFerReserva(Hotels hotel, String dni, String nom, String cognoms,
			LocalDate data, String numnits, String numpersones, DefaultTableModel gestiotablemodel1) {
		
	Clients client = new Clients();
	client.setDni(dni);	
	client.setNom(nom);
	client.setCognoms(cognoms);
	hotel.afegirClientArraylist(client);
	
	Reserves reserva = new Reserves(client);
	reserva.setDataentrada(data);
	reserva.setSortida(data.plusDays(Integer.parseInt(numnits)));
	reserva.setNumeropersones(Integer.parseInt(numpersones));
	
	if (hotel.checkHabitacioLliureIAfegeix(reserva)) {
		hotel.afegirLlistaReservesPendents(reserva);
		gestiotablemodel1.addRow(reserva.arrayReservaPendent());
		return true;	
	}
	
	return false;
	
	
	}

	

	public static boolean agafarClientCrearReserva(Hotels hotel, String dni, LocalDate data, String numnits,
			String numpersones, DefaultTableModel gestiotablemodel1) {
		
		Clients client = agafarClientRegistrat(dni, hotel.getLlistaClient());
		
		Reserves reserva = new Reserves(client);
		reserva.setDataentrada(data);
		reserva.setSortida(data.plusDays(Integer.parseInt(numnits)));
		reserva.setNumeropersones(Integer.parseInt(numpersones));
		
		if (hotel.checkHabitacioLliureIAfegeix(reserva)) {
			hotel.afegirLlistaReservesPendents(reserva);
			actualitzarPendents(gestiotablemodel1, hotel);
			return true;		
		}
		else {
			return false;
		}
	}

	private static  void actualitzarPendents(DefaultTableModel gestiotablemodel1, Hotels hotel) {
		gestiotablemodel1.setRowCount(0);
		if(hotel.getLlistaReservesPendents().size() > 0) {
			for (Reserves i : hotel.getLlistaReservesPendents()) {
				gestiotablemodel1.addRow(i.arrayReservaPendent());			
			}			
		}

	}

	public void passarDades(int filaseleccionada, JTable taula1, DefaultTableModel gestiotablemodel2, DefaultTableModel gestiotablemodel1) {
		Reserves reserva = hotel.getLlistaReservesPendents().get(filaseleccionada);
		gestiotablemodel1.removeRow(filaseleccionada);
		hotel.afegirLlistaReservesConfirmades(reserva);
		hotel.getLlistaReservesPendents().remove(filaseleccionada);
	}
	public void eliminarLinia(int filaseleccionada, DefaultTableModel gestiotablemodel1) {
		gestiotablemodel1.removeRow(filaseleccionada);		
	}
	public Object getTodayDate() {
		
		Date avui = new Date();
		avui = Calendar.getInstance().getTime();
		return avui;
	}

	public void actualitzaTaulaDeReservesConfirmades(JToggleButton jtbentradasortida,
			LocalDate data, DefaultTableModel gestiotablemodel2) {
		gestiotablemodel2.setRowCount(0);
		
			if (jtbentradasortida.isSelected()) {
				
				for (Reserves i : hotel.getLlistaReservesConfirmades()) {
					if(i.getSortida().isEqual(data)){
						gestiotablemodel2.addRow(i.arrayReservaReservats());					}
				}
				
			}
			else {
				for (Reserves i : hotel.getLlistaReservesConfirmades()) {
					if(i.getDataentrada().isEqual(data)){
						gestiotablemodel2.addRow(i.arrayReservaReservats());
					}
				}				
			}

				
		
	}

	public void actualitzaLaLlistadeClients(DefaultListModel<Clients> llistamodel1, String paraula) {
		
		if(hotel.getLlistaClientsPerPattern(paraula).size() > 0){
			llistamodel1.clear();
			for (Clients i : hotel.getLlistaClientsPerPattern(paraula)){
				llistamodel1.addElement(i);				
			}			
		}


	}

	public void eliminarLlistaClient(DefaultListModel<Clients> llistamodel1, DefaultListModel<Reserves> llistamodel2) {
		llistamodel1.clear();
		llistamodel2.clear();
	}

	public void actualitzarLlistaDelClient(Clients clients, DefaultListModel<Reserves> llistamodel2) {
		
		if(hotel.getLlistaReservesPendentsConfirmades(clients).size() > 0) {
			llistamodel2.clear();
			for (Reserves i : hotel.getLlistaReservesPendentsConfirmades(clients)) {
				llistamodel2.addElement(i);				
			}
		}
		
	}

	public void eliminarReserva(Reserves reserva) {
		hotel.eliminarReserva(reserva);				
	}

	public void actualitzarLlistaReservesPendents(DefaultTableModel gestiotablemodel1) {
		actualitzarPendents(gestiotablemodel1, hotel);
	}

	public void autoCompletarCamps(JTextField jtdni, JTextField jtnom, JTextField jtcognoms, Hotels hotels) {
		Clients client = new Clients();
		client = agafarClientRegistrat(jtdni.getText(),hotels.getLlistaClient());
		
		jtnom.setText(client.getNom());
		jtcognoms.setText(client.getCognoms());
		jtnom.setEditable(false);
		jtcognoms.setEditable(false);
		
	}



}