import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MainWindow extends JFrame implements KeyListener, ComponentListener, ActionListener {

	private static final long serialVersionUID = 1L;

	protected JTextArea salidaCMD;
	protected JTextField entrada = new JTextField();

	private Agendas agenda;
	private JPanel panelSup;
	protected JButton botonCopiar = new JButton();
	protected JButton botonCargar = new JButton();

	public MainWindow() {

		// FUENTES

		Font fontsup = null;
		Font fontinf = null;
		InputStream a = getClass().getResourceAsStream("/Recursos/FrederickatheGreat-Regular.ttf");
		try {
			fontsup = Font.createFont(Font.TRUETYPE_FONT, a).deriveFont(20f);
		} catch (FontFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		InputStream b = getClass().getResourceAsStream("/Recursos/Pangolin-Regular.ttf");
		try {
			fontinf = Font.createFont(Font.TRUETYPE_FONT, b).deriveFont(20f);
		} catch (FontFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// ICONOS
		ImageIcon iconGuardar = new ImageIcon("src/Recursos/guardar.png");
		ImageIcon iconCargar = new ImageIcon("src/Recursos/cargar.png");
		ImageIcon icono = new ImageIcon("src/Recursos/agenda.png");
		// Image guardar = iconGuardar.getImage();
		// Image cargar = iconCargar.getImage();
		Image image = icono.getImage();

		// VENTANA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Adrián - Agenda GUI");
		addComponentListener(this);
		setIconImage(image);

		// BOTONES
		JPanel panelBotton = new JPanel();
		panelBotton.setBackground(new Color(255, 192, 84));
		JButton botonCopiar = new JButton();
		botonCopiar.setActionCommand("botonCargar");
		panelBotton.add(botonCopiar);
		botonCopiar.setIcon(iconCargar);
		botonCopiar.addActionListener(this);

		JButton botonCargar = new JButton();
		botonCargar.setActionCommand("botonGuardar");
		panelBotton.add(botonCargar);
		botonCargar.setIcon(iconGuardar);
		botonCargar.addActionListener(this);

		
		
		// Panel Superior Salida

		salidaCMD = new JTextArea(30, 35);
		panelSup = new JPanel(new GridLayout(1, 1));
		panelSup.setBackground(new Color(65, 245, 231, 100));
		agenda = new Agendas();
		salidaCMD.setFont(fontsup);
		JScrollPane scrollPane = new JScrollPane(salidaCMD);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelSup.add(scrollPane);
		salidaCMD.setEditable(false);
		salidaCMD.setBackground(new Color(200, 255, 251, 90));

		// Panel inferior CMD
		JPanel panelInf = new JPanel(new GridLayout(1, 1));
		panelInf.setBackground(new Color(65, 245, 231, 100));
		entrada.addKeyListener(this);
	
		entrada.setFont(fontinf);
		panelInf.add(entrada);

		// Añadir paneles a la ventana
		add(panelBotton, BorderLayout.NORTH);
		add(panelSup, BorderLayout.CENTER);
		add(panelInf, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null); // Centrar ventana

	}

	public void mostrarAyuda() {

		salidaCMD.append("##################  AGENDA DE ADRIAN ###############");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("######################### GUI ########################");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("¿QUE DESEA HACER?");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("Para INTRODUCIR un CONTACTO      nombre-telefono");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("Para BUSCAR un NÚMERO                buscar:nombre");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("Para BORRAR un NÚMERO                borrar:nombre");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("Para GUARDAR la AGENDA                guardar");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("Para CARGAR la AGENDA                  cargar");
		salidaCMD.append(System.getProperty("line.separator"));
		salidaCMD.append("Introduce");
		salidaCMD.append(System.getProperty("line.separator"));

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			String mensaje = agenda.ejecutar(entrada.getText());
			entrada.setText("");
			salidaCMD.append(mensaje + System.lineSeparator());
		}

	}

	// Orden de los botones

	public void actionPerformed(ActionEvent e) {

		String salida;
		if (e.getActionCommand().equals("botonCargar")) {
			salida = agenda.ejecutar("guardar");
			salidaCMD.append(salida + System.lineSeparator());
		}

		else if (e.getActionCommand().equals("botonGuardar")) {
			salida = agenda.ejecutar("cargar");
			salidaCMD.append(salida + System.lineSeparator());
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
