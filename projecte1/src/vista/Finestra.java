package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.junit.internal.runners.statements.RunAfters;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controller.Controller;
import model.*;

public class Finestra extends JFrame {

	JPanel panell1, panell2, panell3;
	JLabel Jltitol1, jlreservespendents, jlreservesconfirmades, Jltitol2, jldni, jlnom, jlcognoms, jlnumpersones,
			jlnumnits, jldata, Jltitol3, jlnomhotel, jlregistrenovahabitacio, jlnumhabitacionova,
			jlnumpersoneshabitacio, jlconsultareserva, jlreservanomclient, jletiquetadni, icona1, icona2, icona3,
			icona4, icona5, icona1p3, icona2p3;
	JTextField jtdni, jtnom, jtcognoms, jtnumnits, jtfnomhotel, jtfnumhabitacionova, jtfnumpersoneshabitacio,
			jtfreservanomclient, jtnumpersones;

	JCalendar jccalendari;
	JButton jbreserva, jbnomhotel, jbnnumpersoneshabitacio, jbconsultareservaclient;
	JToggleButton jtbentradasortida;
	JDateChooser jdcescollir;
	DefaultTableModel gestiotablemodel1, gestiotablemodel2;
	JTable taula1, taula2;
	DefaultListModel<Clients> llistamodel1;
	DefaultListModel<Reserves> llistamodel2;
	JList<Clients> jlconsultareservaclient1;
	JList<Reserves> jlconsultareservaclient2;
	JScrollPane taulascroll1, taulascroll2, llistrascroll1, llistrascroll2;

	Controller c = new Controller();
	boolean clientregistrat;

	boolean camp1, camp2, camp3, camp4, camp5, camp1p3, camp2p3;

	public Finestra() {
		this.setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Oasis");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		mostrarComponents();
	}

	private void mostrarComponents() {

		posarPanells();
		muntarFinestra();
//		c.posarHabitacions(c.getHotel());

	}

	private void muntarFinestra() {

		muntarFinestra1();
		muntarFinestra2();
		muntarFinestra3();

	}

	private void posarPanells() {

		muntarPanell1();
		muntarPanell2();
		muntarPanell3();

	}

	private void muntarPanell1() {
		panell1 = new JPanel();
		panell1.setLayout(null);
		panell1.setBounds(0, 0, 397, 700);
		panell1.setBackground(Color.WHITE);
		this.getContentPane().add(panell1);

	}

	private void muntarPanell2() {
		panell2 = new JPanel();
		panell2.setBounds(400, 0, 397, 700);
		panell2.setBackground(Color.WHITE);
		panell2.setLayout(null);
		this.getContentPane().add(panell2);

	}

	private void muntarPanell3() {

		panell3 = new JPanel();
		panell3.setBounds(800, 0, 397, 700);
		panell3.setBackground(Color.WHITE);
		panell3.setLayout(null);
		this.getContentPane().add(panell3);

	}

	private void muntarFinestra1() {

		Jltitol1 = new JLabel();
		Jltitol1.setBounds(0, 0, 397, 75);
		Jltitol1.setFont(new Font("Arial", Font.BOLD, 22));
		Jltitol1.setHorizontalAlignment(SwingConstants.CENTER);
		Jltitol1.setText("Gestió");
		panell1.add(Jltitol1);

		// Reserves
		jlreservespendents = new JLabel();
		jlreservespendents.setBounds(15, 95, 150, 16);
		jlreservespendents.setFont(new Font("Arial", Font.PLAIN, 16));
		jlreservespendents.setText("Reserves pendents");
		panell1.add(jlreservespendents);

		// taula
		gestiotablemodel1 = new DefaultTableModel();
		gestiotablemodel1.addColumn("Dia");
		gestiotablemodel1.addColumn("Dni");
		gestiotablemodel1.addColumn("Persones");
		gestiotablemodel1.addColumn("Habitació");
		taula1 = new JTable(gestiotablemodel1);
		taula1.setBounds(15, 140, 360, 235);
		taula1.setDefaultEditor(Object.class, null);
		panell1.add(taula1);
		taulascroll1 = new JScrollPane(taula1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		taulascroll1.setBounds(15, 140, 360, 200);
		panell1.add(taulascroll1);

		// Reserves
		jlreservesconfirmades = new JLabel();
		jlreservesconfirmades.setBounds(15, 360, 170, 16);
		jlreservesconfirmades.setFont(new Font("Arial", Font.PLAIN, 16));
		jlreservesconfirmades.setText("Reserves confirmades");
		panell1.add(jlreservesconfirmades);

		jtbentradasortida = new JToggleButton("Entrada");
		jtbentradasortida.setBounds(15, 385, 120, 26);
		panell1.add(jtbentradasortida);

		jdcescollir = new JDateChooser();
		jdcescollir.setDate(Calendar.getInstance().getTime());
		jdcescollir.setBounds(250, 385, 120, 26);
		panell1.add(jdcescollir);

		// taula
		gestiotablemodel2 = new DefaultTableModel();
		gestiotablemodel2.addColumn("Nom");
		gestiotablemodel2.addColumn("Date in");
		gestiotablemodel2.addColumn("Date out");
		gestiotablemodel2.addColumn("Habitació");
		taula2 = new JTable(gestiotablemodel2);
		taula2.setDefaultEditor(Object.class, null);
		taula2.setBounds(15, 420, 360, 235);
		panell1.add(taula2);
		taulascroll2 = new JScrollPane(taula2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		taulascroll2.setBounds(15, 420, 360, 200);
		panell1.add(taulascroll2);

		listenerFinestra1();

	}

	private void listenerFinestra1() {

		MouseListener listenertaula1 = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 1) {
					int filaseleccionada = taula1.getSelectedRow();
					if (filaseleccionada >= 0) {
						String[] opcions = new String[] { "Confirmar-la", "Descarta-la", "Cancelar" };
						int opcio = JOptionPane.showOptionDialog(null, "Què vols fer amb aquesta reserva",
								"Confirmació", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
								opcions, opcions[0]);

						switch (opcio) {
						case 0:
							c.passarDades(filaseleccionada, taula1, gestiotablemodel2, gestiotablemodel1);
							c.actualitzaTaulaDeReservesConfirmades(jtbentradasortida,
									Controller.pasarDateALocalDate(jdcescollir.getDate()), gestiotablemodel2);
							break;
						case 1:
							c.eliminarLinia(filaseleccionada, gestiotablemodel1);
							break;
						case 2:
							break;
						}
					}
				}
			}

		};

		taula1.addMouseListener(listenertaula1);

		ActionListener listenertoglebutton = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtbentradasortida.isSelected()) {
					jtbentradasortida.setText("Sortida");
					c.actualitzaTaulaDeReservesConfirmades(jtbentradasortida,
							Controller.pasarDateALocalDate(jdcescollir.getDate()), gestiotablemodel2);

				} else {
					jtbentradasortida.setText("Entrada");
					c.actualitzaTaulaDeReservesConfirmades(jtbentradasortida,
							Controller.pasarDateALocalDate(jdcescollir.getDate()), gestiotablemodel2);
				}
			}
		};

		jtbentradasortida.addActionListener(listenertoglebutton);

		PropertyChangeListener datechooser = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				c.actualitzaTaulaDeReservesConfirmades(jtbentradasortida,
						Controller.pasarDateALocalDate(jdcescollir.getDate()), gestiotablemodel2);
				System.out.println("hola");
			}
		};
		jdcescollir.addPropertyChangeListener(datechooser);

	}

	/*
	 * #############################################################################
	 * ##########################################################################
	 */

	private void muntarFinestra2() {

		Jltitol2 = new JLabel();
		Jltitol2.setBounds(0, 0, 397, 75);
		Jltitol2.setFont(new Font("Arial", Font.BOLD, 22));
		Jltitol2.setHorizontalAlignment(SwingConstants.CENTER);
		Jltitol2.setText("Clients");
		panell2.add(Jltitol2);

		// Demanar dni
		jldni = new JLabel();
		jldni.setBounds(30, 85, 40, 13);
		jldni.setText("Dni:");
		jldni.setFont(new Font("Arial", Font.PLAIN, 13));
		panell2.add(jldni);

		jtdni = new JTextField();
		jtdni.setName("jtdni");
		jtdni.setBounds(175, 81, 180, 20);
		panell2.add(jtdni);

		icona1 = new JLabel();
		icona1.setBounds(360, 81, 15, 15);
		panell2.add(icona1);

		// Demanar nom
		jlnom = new JLabel();
		jlnom.setBounds(30, 110, 40, 13);
		jlnom.setText("Nom:");
		jlnom.setFont(new Font("Arial", Font.PLAIN, 13));
		panell2.add(jlnom);

		jtnom = new JTextField();
		jtnom.setName("jtnom");
		jtnom.setBounds(175, 106, 180, 20);
		panell2.add(jtnom);

		icona2 = new JLabel();
		icona2.setBounds(360, 106, 15, 15);
		panell2.add(icona2);

		// Demanar cognoms
		jlcognoms = new JLabel();
		jlcognoms.setBounds(30, 135, 80, 13);
		jlcognoms.setText("Cognoms:");
		jlcognoms.setFont(new Font("Arial", Font.PLAIN, 13));
		panell2.add(jlcognoms);

		jtcognoms = new JTextField();
		jtcognoms.setName("jtcognoms");
		jtcognoms.setBounds(175, 131, 180, 20);
		panell2.add(jtcognoms);

		icona3 = new JLabel();
		icona3.setBounds(360, 131, 15, 15);
		panell2.add(icona3);

		// Demanar num persones
		jlnumpersones = new JLabel();
		jlnumpersones.setBounds(30, 160, 120, 13);
		jlnumpersones.setText("Num. Persones:");
		jlnumpersones.setFont(new Font("Arial", Font.PLAIN, 13));
		panell2.add(jlnumpersones);

		jtnumpersones = new JTextField();
		jtnumpersones.setName("jtnumpersones");
		jtnumpersones.setBounds(175, 156, 50, 20);
		panell2.add(jtnumpersones);

		icona4 = new JLabel();
		icona4.setBounds(360, 156, 15, 15);
		panell2.add(icona4);

		// Demanar num nits
		jlnumnits = new JLabel();
		jlnumnits.setBounds(30, 185, 120, 13);
		jlnumnits.setText("Num. Nits:");
		jlnumnits.setFont(new Font("Arial", Font.PLAIN, 13));
		panell2.add(jlnumnits);

		jtnumnits = new JTextField();
		jtnumnits.setName("jtnumnits");
		jtnumnits.setBounds(175, 181, 50, 20);
		panell2.add(jtnumnits);

		icona5 = new JLabel();
		icona5.setBounds(360, 181, 15, 15);
		panell2.add(icona5);

		// demanar data
		jldata = new JLabel();
		jldata.setBounds(30, 240, 140, 13);
		jldata.setText("Data d'entrada:");
		jldata.setFont(new Font("Arial", Font.PLAIN, 13));
		panell2.add(jldata);

		jccalendari = new JCalendar();
		jccalendari.setBounds(30, 280, 330, 250);
		jccalendari.setMinSelectableDate(Calendar.getInstance().getTime());
		panell2.add(jccalendari);

		jbreserva = new JButton("Reserva");
		jbreserva.setEnabled(false);
		jbreserva.setBounds(150, 600, 100, 30);
		panell2.add(jbreserva);

		listenersFinestra2();

	}

	private void listenersFinestra2() {

		KeyListener listener = new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getComponent().getName().equalsIgnoreCase("jtdni")) {

					if (Controller.regexDNI(jtdni.getText())) {
						icona1.setIcon(creariconasi());
						camp1 = true;
						if(Controller.clientJaHaFetReserva(jtdni.getText(), c.getHotel().getLlistaClient())){
							c.autoCompletarCamps(jtdni,jtnom,jtcognoms,c.getHotel());						
						}

					} else {
						camp1 = false;
						icona1.setIcon(creariconano());
						jtnom.setEditable(true);
						jtcognoms.setEditable(true);
						jtnom.setText("");
						jtcognoms.setText("");

					}

					if (Controller.habilitarBotoPanell2(camp1, camp2, camp3, camp4, camp5)) {
						jbreserva.setEnabled(true);
						panell2.add(jbreserva);
					} else {
						jbreserva.setEnabled(false);
						panell2.add(jbreserva);
					}
				} else if (e.getComponent().getName().equalsIgnoreCase("jtnom")) {

					if (Controller.regexNom(jtnom.getText())) {
						icona2.setIcon(creariconasi());
						camp2 = true;
					} else {
						icona2.setIcon(creariconano());
						camp2 = false;
					}
					if (Controller.habilitarBotoPanell2(camp1, camp2, camp3, camp4, camp5)) {
						jbreserva.setEnabled(true);
						panell2.add(jbreserva);
					} else {
						jbreserva.setEnabled(false);
						panell2.add(jbreserva);
					}

				} else if (e.getComponent().getName().equalsIgnoreCase("jtcognoms")) {
					if (Controller.regexCognoms(jtcognoms.getText())) {
						icona3.setIcon(creariconasi());
						camp3 = true;
					} else {
						icona3.setIcon(creariconano());
						camp3 = true;
					}
					if (Controller.habilitarBotoPanell2(camp1, camp2, camp3, camp4, camp5)) {
						jbreserva.setEnabled(true);
						panell2.add(jbreserva);
					} else {
						jbreserva.setEnabled(false);
						panell2.add(jbreserva);
					}
				} else if (e.getComponent().getName().equalsIgnoreCase("jtnumpersones")) {
					if (Controller.regexNumPersones(jtnumpersones.getText())) {
						icona4.setIcon(creariconasi());
						camp4 = true;
					} else {
						icona4.setIcon(creariconano());
						camp4 = false;
					}
					if (Controller.habilitarBotoPanell2(camp1, camp2, camp3, camp4, camp5)) {
						jbreserva.setEnabled(true);
						panell2.add(jbreserva);
					} else {
						jbreserva.setEnabled(false);
						panell2.add(jbreserva);
					}
				} else if (e.getComponent().getName().equalsIgnoreCase("jtnumnits")) {
					if (Controller.regexNumNits(jtnumnits.getText())) {
						icona5.setIcon(creariconasi());
						camp5 = true;
					} else {
						icona5.setIcon(creariconano());
						camp5 = false;
					}
					if (Controller.habilitarBotoPanell2(camp1, camp2, camp3, camp4, camp5)) {
						jbreserva.setEnabled(true);
						panell2.add(jbreserva);
					} else {
						jbreserva.setEnabled(false);
						panell2.add(jbreserva);
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

		};

		jtdni.addKeyListener(listener);
		jtnom.addKeyListener(listener);
		jtcognoms.addKeyListener(listener);
		jtnumpersones.addKeyListener(listener);
		jtnumnits.addKeyListener(listener);

		ActionListener listenerboto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Controller.clientJaHaFetReserva(jtdni.getText(), c.getHotel().getLlistaClient())) {
					if (Controller.agafarClientCrearReserva(c.getHotel(), jtdni.getText(),
							Controller.pasarDateALocalDate(jccalendari.getDate()), jtnumnits.getText(),
							jtnumpersones.getText(), gestiotablemodel1)) {
						System.out.println();
						JOptionPane.showMessageDialog(null, "Afegit correctament a reserves pendents");
					} else {
						JOptionPane.showMessageDialog(null,
								"No hi ha una habitació disponible per aquest quantitat de persones");
					}

				} else {
					if (Controller.crearClientIFerReserva(c.getHotel(), jtdni.getText(), jtnom.getText(),
							jtcognoms.getText(), Controller.pasarDateALocalDate(jccalendari.getDate()),
							jtnumnits.getText(), jtnumpersones.getText(), gestiotablemodel1)) {
						JOptionPane.showMessageDialog(null, "Afegit correctament a reserves pendents");

					} else {
						JOptionPane.showMessageDialog(null,
								"No hi ha una habitació disponible per aquest quantitat de persones");
					}

				}

				jtdni.setText("");
				jtnom.setText("");
				jtcognoms.setText("");
				jtnumpersones.setText("");
				jtnumnits.setText("");

				icona1.setIcon(null);
				icona2.setIcon(null);
				icona3.setIcon(null);
				icona4.setIcon(null);
				icona5.setIcon(null);

				jccalendari.setMinSelectableDate(Calendar.getInstance().getTime());
				panell2.add(jccalendari);

				jbreserva.setEnabled(false);
			}
		};

		jbreserva.addActionListener(listenerboto);

	}

	/*
	 * #############################################################################
	 * ##########################################################################
	 */

	private void muntarFinestra3() {

		Jltitol3 = new JLabel();
		Jltitol3.setBounds(0, 0, 397, 75);
		Jltitol3.setFont(new Font("Arial", Font.BOLD, 22));
		Jltitol3.setHorizontalAlignment(SwingConstants.CENTER);
		Jltitol3.setText("Back");
		panell3.add(Jltitol3);

		jlnomhotel = new JLabel();
		jlnomhotel.setBounds(30, 85, 120, 13);
		jlnomhotel.setFont(new Font("Arial", Font.BOLD, 13));
		jlnomhotel.setText("Nom Hotel");
		panell3.add(jlnomhotel);

		jtfnomhotel = new JTextField();
		jtfnomhotel.setBounds(175, 81, 150, 20);
		panell3.add(jtfnomhotel);

		jbnomhotel = new JButton("Guarda!");
		jbnomhotel.setBounds(150, 120, 100, 30);
		panell3.add(jbnomhotel);

		jlregistrenovahabitacio = new JLabel();
		jlregistrenovahabitacio.setBounds(30, 180, 170, 15);
		jlregistrenovahabitacio.setText("Registre nova habitació");
		jlregistrenovahabitacio.setFont(new Font("Arial", Font.BOLD, 15));
		panell3.add(jlregistrenovahabitacio);

		jlnumhabitacionova = new JLabel();
		jlnumhabitacionova.setBounds(30, 220, 50, 13);
		jlnumhabitacionova.setText("Num.");
		jlnumhabitacionova.setFont(new Font("Arial", Font.PLAIN, 13));
		panell3.add(jlnumhabitacionova);

		jtfnumhabitacionova = new JTextField();
		jtfnumhabitacionova.setBounds(100, 216, 60, 20);
		jtfnumhabitacionova.setName("novahabitacio");
		panell3.add(jtfnumhabitacionova);

		icona1p3 = new JLabel();
		icona1p3.setBounds(170, 216, 15, 15);
		panell3.add(icona1p3);

		jlnumpersoneshabitacio = new JLabel();
		jlnumpersoneshabitacio.setBounds(220, 220, 50, 13);
		jlnumpersoneshabitacio.setText("#Pers.");
		jlnumpersoneshabitacio.setFont(new Font("Arial", Font.PLAIN, 13));
		panell3.add(jlnumpersoneshabitacio);

		jtfnumpersoneshabitacio = new JTextField();
		jtfnumpersoneshabitacio.setName("personeshabitacio");
		jtfnumpersoneshabitacio.setBounds(290, 216, 60, 20);
		panell3.add(jtfnumpersoneshabitacio);

		icona2p3 = new JLabel();
		icona2p3.setBounds(355, 216, 15, 15);
		panell3.add(icona2p3);

		jbnnumpersoneshabitacio = new JButton("Guarda!");
		jbnnumpersoneshabitacio.setBounds(150, 270, 100, 30);
		jbnnumpersoneshabitacio.setEnabled(false);
		panell3.add(jbnnumpersoneshabitacio);

		jlconsultareserva = new JLabel();
		jlconsultareserva.setBounds(30, 320, 170, 15);
		jlconsultareserva.setText("Consulta reserva");
		jlconsultareserva.setFont(new Font("Arial", Font.BOLD, 15));
		panell3.add(jlconsultareserva);

		jlreservanomclient = new JLabel();
		jlreservanomclient.setBounds(30, 370, 100, 13);
		jlreservanomclient.setText("Nom client");
		jlreservanomclient.setFont(new Font("Arial", Font.BOLD, 13));
		panell3.add(jlreservanomclient);

		jtfreservanomclient = new JTextField();
		jtfreservanomclient.setBounds(160, 366, 200, 20);
		panell3.add(jtfreservanomclient);

		llistamodel1 = new DefaultListModel<Clients>();
		jlconsultareservaclient1 = new JList<Clients>(llistamodel1);
		jlconsultareservaclient1.setBounds(30, 410, 150, 150);
		jlconsultareservaclient1.setSelectedIndex(0);
		panell3.add(jlconsultareservaclient1);
		llistrascroll1 = new JScrollPane(jlconsultareservaclient1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		llistrascroll1.setBounds(30, 410, 150, 150);
		panell3.add(llistrascroll1);

		llistamodel2 = new DefaultListModel<Reserves>();
		jlconsultareservaclient2 = new JList<Reserves>(llistamodel2);
		jlconsultareservaclient1.setBounds(30, 410, 150, 150);
		jlconsultareservaclient2.setSelectedIndex(0);
		panell3.add(jlconsultareservaclient2);
		llistrascroll2 = new JScrollPane(jlconsultareservaclient2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		llistrascroll2.setBounds(215, 410, 150, 150);
		panell3.add(llistrascroll2);

		jbconsultareservaclient = new JButton("Elimina!");
		jbconsultareservaclient.setBounds(150, 600, 100, 30);
		jbconsultareservaclient.setEnabled(false);
		panell3.add(jbconsultareservaclient);

		listenersFinestra3();

	}

	private void listenersFinestra3() {

		ActionListener listener1 = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				c.getHotel().setNomhotel(jtfnomhotel.getText());
				setTitle(jtfnomhotel.getText());
			}
		};
		jbnomhotel.addActionListener(listener1);

		KeyListener keylistener1 = new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getComponent().getName().equalsIgnoreCase("novahabitacio")) {
					if (Controller.regexNumeroHabitacio(jtfnumhabitacionova.getText())) {
						icona1p3.setIcon(creariconasi());
						panell3.add(icona1p3);
						camp1p3 = true;
					} else {
						icona1p3.setIcon(creariconano());
						panell3.add(icona1p3);
						camp1p3 = false;
					}
					if (Controller.habilitarBotoPanell3(camp1p3, camp2p3)) {
						jbnnumpersoneshabitacio.setEnabled(true);
						panell3.add(jbnnumpersoneshabitacio);
					} else {
						jbnnumpersoneshabitacio.setEnabled(false);
						panell3.add(jbnnumpersoneshabitacio);
					}

				}

				else if (e.getComponent().getName().equalsIgnoreCase("personeshabitacio")) {
					if (Controller.regexNumPersones(jtfnumpersoneshabitacio.getText())) {
						icona2p3.setIcon((creariconasi()));
						panell3.add(icona2p3);
						camp2p3 = true;
					} else {
						icona2p3.setIcon(creariconano());
						panell3.add(icona2p3);
						camp2p3 = false;
					}
					if (Controller.habilitarBotoPanell3(camp1p3, camp2p3)) {
						jbnnumpersoneshabitacio.setEnabled(true);
						panell3.add(jbnnumpersoneshabitacio);
					} else {
						jbnnumpersoneshabitacio.setEnabled(false);
						panell3.add(jbnnumpersoneshabitacio);
					}

				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		};

		jtfnumhabitacionova.addKeyListener(keylistener1);
		jtfnumpersoneshabitacio.addKeyListener(keylistener1);

		ActionListener listener2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Controller.checkSiHabitacioExist(c.getHotel(), jtfnumhabitacionova.getText())) {
					if (Controller.checkIfHabitacioEstaOcupada(jtfnumhabitacionova.getText(), c.getHotel())) {
						JOptionPane.showMessageDialog(null,
								"Aquesta habitació esta reservada o en ús, no pot ser modificada");
					} else {

						int opcio = JOptionPane.showConfirmDialog(null,
								"Aquesta habitació ja es troba registrada," + " aquesta compta amb una capacitat de "
										+ Controller.getCapacitatActual(jtfnumhabitacionova.getText(), c.getHotel())
										+ " vols cambiar-la a " + jtfnumpersoneshabitacio.getText() + " ?");
						switch (opcio) {

						case 0:
							Controller.afegirNovaCapacitatHabitacio(jtfnumpersoneshabitacio.getText(),
									jtfnumhabitacionova.getText(), c.getHotel());
							icona1p3.setIcon(null);
							icona2p3.setIcon(null);
							jtfnumhabitacionova.setText(null);
							jtfnumpersoneshabitacio.setText(null);
							jbnnumpersoneshabitacio.setEnabled(false);

							break;
						case 1:
							icona1p3.setIcon(null);
							icona2p3.setIcon(null);
							jtfnumhabitacionova.setText(null);
							jtfnumpersoneshabitacio.setText(null);
							jbnnumpersoneshabitacio.setEnabled(false);
							break;
						case 2:
							break;

						}
					}

				}

				else {
					Controller.afegirHabitacióNova(jtfnumhabitacionova.getText(), jtfnumpersoneshabitacio.getText(),
							c.getHotel());
					JOptionPane.showMessageDialog(null, "La habitació s'ha afegit correctament!");
					icona1p3.setIcon(null);
					icona2p3.setIcon(null);
					jtfnumhabitacionova.setText(null);
					jtfnumpersoneshabitacio.setText(null);
					jbnnumpersoneshabitacio.setEnabled(false);
				}

			}
		};

		jbnnumpersoneshabitacio.addActionListener(listener2);

		KeyListener listenerreservanomclient = new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (!jtfreservanomclient.getText().isBlank()) {
					c.actualitzaLaLlistadeClients(llistamodel1, jtfreservanomclient.getText());
				} else {
					c.eliminarLlistaClient(llistamodel1, llistamodel2);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		};

		jtfreservanomclient.addKeyListener(listenerreservanomclient);

		ListSelectionListener jlmostrarbooking = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					c.actualitzarLlistaDelClient(jlconsultareservaclient1.getSelectedValue(), llistamodel2);
				}
			}
		};

		jlconsultareservaclient1.addListSelectionListener(jlmostrarbooking);

		ListSelectionListener jlbookingborrar = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (jlconsultareservaclient2.isSelectionEmpty()) {
					jbconsultareservaclient.setEnabled(false);
				} else {
					jbconsultareservaclient.setEnabled(true);
				}
			}
		};
		jlconsultareservaclient2.addListSelectionListener(jlbookingborrar);

		ActionListener jbconsultareservalistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] opcions = new String[] { "Si", "No" };
				int opcio = JOptionPane.showOptionDialog(null, "Vols eliminar aquest reserva?", "Confirmació",
						JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, opcions, opcions[0]);
				switch (opcio) {
				case 0:
					c.eliminarReserva(jlconsultareservaclient2.getSelectedValue());
					JOptionPane.showMessageDialog(null, "Reserva eliminada correctament.");
					llistamodel2.clear();
					c.actualitzarLlistaDelClient(jlconsultareservaclient1.getSelectedValue(), llistamodel2);
					c.actualitzarLlistaReservesPendents(gestiotablemodel1);
					c.actualitzaTaulaDeReservesConfirmades(jtbentradasortida,
							Controller.pasarDateALocalDate(jdcescollir.getDate()), gestiotablemodel2);
					break;
				case 1:
					break;
				}
			}
		};

		jbconsultareservaclient.addActionListener(jbconsultareservalistener);

	}

	private ImageIcon creariconano() {

		ImageIcon imageIcon = new ImageIcon("./imatges/no.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		return imageIcon;

	}

	private ImageIcon creariconasi() {

		ImageIcon imageIcon = new ImageIcon("./imatges/yes.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		return imageIcon;

	}

}