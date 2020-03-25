package controller;

import java.awt.font.NumericShaper;
import java.security.DrbgParameters.Capability;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

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

	public static Clients agafarClientRegistrar(String dni, ArrayList<Clients> arrayList) {

		for (Clients i : arrayList) {

			if (i.getDni().equalsIgnoreCase(dni)) {

				return i;
			}

		}

		return null;

	}

	public static LocalDate pasarDateALocalDate(JCalendar jccalendari) {

		Long dataNano = jccalendari.getDate().getTime();
		LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
		return data;

	}

	public static void afegirReserva(Clients client, String persones, LocalDate data, boolean clientregistrat,
			Hotels hotel, DefaultTableModel gestiotablemodel1) {

	}

	public static void posarHabitacions(Hotels hotel) {

		Random r = new Random();
		int numpersones;

		for (int i = 0; i < 50; i++) {

			numpersones = r.nextInt(6 + 1);

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

	public static void afegirReserva(Clients client, Reserves reserva, boolean clientregistrat, Hotels hotel,
			DefaultTableModel gestiotablemodel1) {
		if (clientregistrat = true) {
			hotel.afegirLlistaReservesPendents(reserva);
			gestiotablemodel1.addRow(reserva.arrayReservaPendent());
		} else if (clientregistrat = false) {
			hotel.afegirClientArraylist(client);
			hotel.afegirLlistaReservesPendents(reserva);
			gestiotablemodel1.addRow(reserva.arrayReservaPendent());
		}

	}



}