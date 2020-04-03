package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import controller.Controller;
import static java.lang.System.*;

public class Fitxers {
	File fitxerhabitacions, fitxerclients, fitxerreservespendents, fitxerreservescomfirmades, carpeta;
	FileWriter fwriter;
	BufferedWriter buffWriter;
	FileReader freader;
	BufferedReader buffReader;
	Controller c;

	public Fitxers() {
		creaDirectori();
		crearFitxerHabitacions();
		CrearFitxerClients();
		CrearFitxerReservesPendents();
		CrearFitxerReservesComfirmades();
	}

	public void omplirAlHabitacions(ArrayList<Habitacions> arraylhabitacions) {
		try {
			freader = new FileReader(fitxerhabitacions);
			buffReader = new BufferedReader(freader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String currentLine = "";

		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] habitaciosplit = currentLine.split("-");
			Habitacions habitacio = new Habitacions(Integer.parseInt(habitaciosplit[0]));
			habitacio.setCapacitat(Integer.parseInt(habitaciosplit[1]));
			arraylhabitacions.add(habitacio);
		}
		try {
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void omplirAlClient(ArrayList<Clients> llistaClient) {
		try {
			freader = new FileReader(fitxerclients);
			buffReader = new BufferedReader(freader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String currentLine = "";

		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] arrayclient = currentLine.split("-");
			Clients client = new Clients();
			client.setDni(arrayclient[0]);
			client.setNom(arrayclient[1]);
			client.setCognoms(arrayclient[2]);

			llistaClient.add(client);
		}
		try {
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void omplirAlReservesPendents(ArrayList<Clients> llistaClient, ArrayList<Habitacions> llistaHabitacions,
			ArrayList<Reserves> llistaReservesPendents) {
		try {
			freader = new FileReader(fitxerreservespendents);
			buffReader = new BufferedReader(freader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String currentLine = "";
		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] arrayclient = currentLine.split("-");
			Clients client = new Clients();
			for (Clients i : llistaClient) {
				if (i.getDni().equalsIgnoreCase(arrayclient[0])) {
					client.setDni(i.getDni());
					client.setNom(i.getNom());
					client.setCognoms(i.getCognoms());
				}
			}
			Reserves reservapendent = new Reserves(client);
			reservapendent.setNumeropersones(Integer.parseInt(arrayclient[3]));
			for (Habitacions i : llistaHabitacions) {
				if (i.getNumhabitacio() == Integer.parseInt(arrayclient[1])) {
					reservapendent.setHabitacio(i);
				}
			}
			;
			String[] dataentrada = arrayclient[4].split("/");
			String[] datasortida = arrayclient[5].split("/");
			reservapendent.setDataentrada(LocalDate.of(Integer.parseInt(dataentrada[2]),
					Integer.parseInt(dataentrada[1]), Integer.parseInt(dataentrada[0])));
			reservapendent.setSortida(LocalDate.of(Integer.parseInt(datasortida[2]), Integer.parseInt(datasortida[1]),
					Integer.parseInt(datasortida[0])));
			llistaReservesPendents.add(reservapendent);
		}
		try {
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void omplirAlReservesComfirmades(ArrayList<Clients> llistaClient, ArrayList<Habitacions> llistaHabitacions,
			ArrayList<Reserves> llistaReservesComfirmades) {
		try {
			freader = new FileReader(fitxerreservescomfirmades);
			buffReader = new BufferedReader(freader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String currentLine = "";
		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] arrayclient = currentLine.split("-");
			Clients client = new Clients();
			System.out.println("va be");
			for (Clients i : llistaClient) {
				if (i.getDni().equalsIgnoreCase(arrayclient[0])) {
					client.setDni(i.getDni());
					client.setNom(i.getNom());
					client.setCognoms(i.getCognoms());
					System.out.println("asd");
				}
			}
			Reserves reservapendent = new Reserves(client);
 			reservapendent.setNumeropersones(Integer.parseInt(arrayclient[3]));
			for (Habitacions i : llistaHabitacions) {
				if (i.getNumhabitacio() == Integer.parseInt(arrayclient[1])) {
					reservapendent.setHabitacio(i);
					System.out.println("habitació agafada");
				}
			}
			;
			String[] dataentrada = arrayclient[4].split("/");
			String[] datasortida = arrayclient[5].split("/");
			reservapendent.setDataentrada(LocalDate.of(Integer.parseInt(dataentrada[2]),
					Integer.parseInt(dataentrada[1]), Integer.parseInt(dataentrada[0])));
			System.out.println("data entrada");
			reservapendent.setSortida(LocalDate.of(Integer.parseInt(datasortida[2]), Integer.parseInt(datasortida[1]),
					Integer.parseInt(datasortida[0])));
			System.out.println("data sortida");
			llistaReservesComfirmades.add(reservapendent);
			System.out.println("reserva posada");

		}
		try {
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actualitzarHabitacions(Habitacions i, String quantiat) {

		File fileTemp = new File("dades" + File.separator + "habitacionsTemp.txt");
		try {
			freader = new FileReader(fitxerhabitacions);
			fwriter = new FileWriter(fileTemp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		buffReader = new BufferedReader(freader);
		buffWriter = new BufferedWriter(fwriter);
		String currentLine = "";
		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] nhabitacio = currentLine.split("-");
			if (nhabitacio[0].equals(Integer.toString(i.getNumhabitacio()))) {
				try {
					buffWriter.write(i.getNumhabitacio() + "-" + quantiat + System.getProperty("line.separator"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				continue;
			} else {
				try {
					buffWriter.write(currentLine + System.getProperty("line.separator"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			buffWriter.close();
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fitxerhabitacions.delete();
		fileTemp.renameTo(fitxerhabitacions);
	}

	public void esborrarReservaPendent(Reserves reserva) {
		System.out.println("asd");
		File fileTemp = new File("dades" + File.separator + "reservesPendentsTEMP.txt");
		try {
			freader = new FileReader(fitxerreservespendents);
			fwriter = new FileWriter(fileTemp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("asad");
		buffReader = new BufferedReader(freader);
		buffWriter = new BufferedWriter(fwriter);
		String currentLine = "";
		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] reservapedent = currentLine.split("-");
			String[] dataentradastring = reservapedent[4].split("/");
			String[] datasortidastring = reservapedent[5].split("/");
			LocalDate dataentrada = LocalDate.of(Integer.parseInt(dataentradastring[2]),
					Integer.parseInt(dataentradastring[1]), Integer.parseInt(dataentradastring[0]));
			LocalDate datasortida = LocalDate.of(Integer.parseInt(datasortidastring[2]),
					Integer.parseInt(datasortidastring[1]), Integer.parseInt(datasortidastring[0]));
			
			if (reservapedent[0].equalsIgnoreCase(reserva.getClient().getDni())
					&& reservapedent[1].equalsIgnoreCase(Integer.toString(reserva.getHabitacio().getNumhabitacio()))
							&& reserva.getDataentrada().isEqual(dataentrada) && reserva.getSortida().isEqual(datasortida)){

			} else {
				try {
					buffWriter.write(currentLine + System.getProperty("line.separator"));			
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			buffWriter.close();
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fitxerreservespendents.delete();
		fileTemp.renameTo(fitxerreservespendents);
	}
	public void esborrarReservaConfirmada(Reserves reserva) {
		System.out.println("asd");
		File fileTemp = new File("dades" + File.separator + "reserversConfirmadaTEMP.txt");
		try {
			freader = new FileReader(fitxerreservescomfirmades);
			fwriter = new FileWriter(fileTemp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("asad");
		buffReader = new BufferedReader(freader);
		buffWriter = new BufferedWriter(fwriter);
		String currentLine = "";
		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] reservaconfirmada = currentLine.split("-");
			String[] dataentradastring = reservaconfirmada[4].split("/");
			String[] datasortidastring = reservaconfirmada[5].split("/");
			LocalDate dataentrada = LocalDate.of(Integer.parseInt(dataentradastring[2]),
					Integer.parseInt(dataentradastring[1]), Integer.parseInt(dataentradastring[0]));
			LocalDate datasortida = LocalDate.of(Integer.parseInt(datasortidastring[2]),
					Integer.parseInt(datasortidastring[1]), Integer.parseInt(datasortidastring[0]));
			
			if (reservaconfirmada[0].equalsIgnoreCase(reserva.getClient().getDni())
					&& reservaconfirmada[1].equalsIgnoreCase(Integer.toString(reserva.getHabitacio().getNumhabitacio()))
							&& reserva.getDataentrada().isEqual(dataentrada) && reserva.getSortida().isEqual(datasortida)){

			} else {
				try {
					buffWriter.write(currentLine + System.getProperty("line.separator"));			
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			buffWriter.close();
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fitxerreservescomfirmades.delete();
		fileTemp.renameTo(fitxerreservescomfirmades);
	}
	public void esborrarReservaPendentAfegirAComfirmada(Reserves reservapendet, ArrayList<Reserves> reservacomfirmada) {
		System.out.println("asd");
		File fileTemp = new File("dades" + File.separator + "reservesPendentsTEMP.txt");
		try {
			freader = new FileReader(fitxerreservespendents);
			fwriter = new FileWriter(fileTemp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("asad");
		buffReader = new BufferedReader(freader);
		buffWriter = new BufferedWriter(fwriter);
		String currentLine = "";
		while (true) {
			try {
				if (!((currentLine = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] reservapedent = currentLine.split("-");
			String[] dataentradastring = reservapedent[4].split("/");
			String[] datasortidastring = reservapedent[5].split("/");
			LocalDate dataentrada = LocalDate.of(Integer.parseInt(dataentradastring[2]),
					Integer.parseInt(dataentradastring[1]), Integer.parseInt(dataentradastring[0]));
			LocalDate datasortida = LocalDate.of(Integer.parseInt(datasortidastring[2]),
					Integer.parseInt(datasortidastring[1]), Integer.parseInt(datasortidastring[0]));
			
			if (reservapedent[0].equalsIgnoreCase(reservapendet.getClient().getDni())
					&& reservapedent[1].equalsIgnoreCase(Integer.toString(reservapendet.getHabitacio().getNumhabitacio()))
							&& reservapendet.getDataentrada().isEqual(dataentrada) && reservapendet.getSortida().isEqual(datasortida)){
				reservacomfirmada.add(reservapendet);

			} else {
				try {
					buffWriter.write(currentLine + System.getProperty("line.separator"));			
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			buffWriter.close();
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fitxerreservespendents.delete();
		fileTemp.renameTo(fitxerreservespendents);
	}

	private void creaDirectori() {
		carpeta = new File("dades");
		if (carpeta.mkdir()) {
			out.println("Carpeta creada correctament");
		} else {
			err.println("Error al crear el directory");
		}
	}

	private void crearFitxerHabitacions() {
		fitxerhabitacions = new File("dades" + File.separator + "habitacions.txt");
		if (fitxerhabitacions.exists()) {
			out.println("El fitxer ja existeix");
		} else {
			try {
				if (fitxerhabitacions.createNewFile()) {
					out.println("Fitxer creat correctament");
				} else {
					out.println("Error al crear el fitxer...");
				}
			} catch (IOException e) {
				out.println("Error al crear el fitxer: " + e);
			}
		}
	}

	private void CrearFitxerClients() {
		fitxerclients = new File("dades" + File.separator + "clients.txt");
		if (fitxerclients.exists()) {
			out.println("El fitxer ja existeix");
		} else {
			try {
				if (fitxerclients.createNewFile()) {
					out.println("Fitxer creat correctament");
				} else {
					out.println("Error al crear el fitxer...");
				}
			} catch (IOException e) {
				out.println("Error al crear el fitxer: " + e);
			}
		}
	}

	private void CrearFitxerReservesPendents() {
		fitxerreservespendents = new File("dades" + File.separator + "reservespendents.txt");
		if (fitxerreservespendents.exists()) {
			out.println("El fitxer ja existeix");
		} else {
			try {
				if (fitxerreservespendents.createNewFile()) {
					out.println("Fitxer creat correctament");
				} else {
					out.println("Error al crear el fitxer...");
				}
			} catch (IOException e) {
				out.println("Error al crear el fitxer: " + e);
			}
		}
	}

	private void CrearFitxerReservesComfirmades() {
		fitxerreservescomfirmades = new File("dades" + File.separator + "reservescomfirmades.txt");
		if (fitxerreservescomfirmades.exists()) {
			out.println("El fitxer ja existeix");
		} else {
			try {
				if (fitxerreservescomfirmades.createNewFile()) {
					out.println("Fitxer creat correctament");
				} else {
					out.println("Error al crear el fitxer...");
				}
			} catch (IOException e) {
				out.println("Error al crear el fitxer: " + e);
			}
		}
	}

	public void writeOnFileHabitacio(Habitacions habitacio) {
		try {
			fwriter = new FileWriter(fitxerhabitacions, true);
			buffWriter = new BufferedWriter(fwriter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			buffWriter.write(
					Integer.toString(habitacio.getNumhabitacio()) + "-" + Integer.toString(habitacio.getCapacitat()));
			buffWriter.write(lineSeparator());
			buffWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeOnFileClients(Clients client) {
		try {
			fwriter = new FileWriter(fitxerclients, true);
			buffWriter = new BufferedWriter(fwriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			buffWriter.write(client.getDni() + "-" + client.getNom() + "-" + client.getCognoms());
			buffWriter.write(lineSeparator());
			buffWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeOnFileReservesPendents(Reserves reserva) {
		try {
			fwriter = new FileWriter(fitxerreservespendents, true);
			buffWriter = new BufferedWriter(fwriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			buffWriter.write(reserva.getClient().getDni() + "-" + reserva.getHabitacio().getNumhabitacio() + "-"
					+ reserva.getHabitacio().getCapacitat() + "-" + reserva.getNumeropersones() + "-"
					+ reserva.getDataentrada().getDayOfMonth() + "/" + reserva.getDataentrada().getMonthValue() + "/"
					+ reserva.getDataentrada().getYear() + "-" + reserva.getSortida().getDayOfMonth() + "/"
					+ reserva.getSortida().getMonthValue() + "/" + reserva.getSortida().getYear());
			buffWriter.write(lineSeparator());
			buffWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeOnFileReservesComfirmades(Reserves reserva) {
		try {
			fwriter = new FileWriter(fitxerreservescomfirmades, true);
			buffWriter = new BufferedWriter(fwriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			buffWriter.write(reserva.getClient().getDni() + "-" + reserva.getHabitacio().getNumhabitacio() + "-"
					+ reserva.getHabitacio().getCapacitat() + "-" + reserva.getNumeropersones() + "-"
					+ reserva.getDataentrada().getDayOfMonth() + "/" + reserva.getDataentrada().getMonthValue() + "/"
					+ reserva.getDataentrada().getYear() + "-" + reserva.getSortida().getDayOfMonth() + "/"
					+ reserva.getSortida().getMonthValue() + "/" + reserva.getSortida().getYear());
			buffWriter.write(lineSeparator());
			buffWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}