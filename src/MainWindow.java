import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class MainWindow extends JFrame implements KeyListener, ComponentListener {

	private static final long serialVersionUID = 1L;
	
	protected JTextArea salidaCMD;
	protected JTextField entrada = new JTextField();
	private Agendas agenda;
	private JPanel panelSup;
	
	public MainWindow() {
		Font fontsup =null;
		Font fontinf = null;
		InputStream a = getClass().getResourceAsStream("/Recursos/FrederickatheGreat-Regular.ttf");
		try {
		fontsup = Font.createFont(Font.TRUETYPE_FONT, a).deriveFont(20f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream b = getClass().getResourceAsStream("/Recursos/Pangolin-Regular.ttf");
		try {
			fontinf = Font.createFont(Font.TRUETYPE_FONT, b).deriveFont(20f);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Adrián - Interfaz Grafica");
		addComponentListener(this);
		
		ImageIcon iconGuardar = new ImageIcon("/Recursos/guardar.png");
		ImageIcon iconCargar = new ImageIcon("/Recursos/cargar.png");
		
		JPanel panelBotton = new JPanel();
		
		JButton botonCopiar = new JButton(iconGuardar);
		JButton botonCargar = new JButton(iconCargar);
		botonCopiar.setActionCommand("botonCopiar");
		botonCargar.setActionCommand("botonCargar");
		//botonCopiar.addActionListener(this);
		//botonCargar.addActionListener(this);
			panelBotton.add(botonCopiar);
			panelBotton.add(botonCargar);
			
	
			
			
		JPanel panelInf = new JPanel(new GridLayout(1, 1));
		panelSup= new JPanel(new GridLayout(1, 1));
		
		panelSup.setBackground(new Color(65,245,231,100));
		panelInf.setBackground(new Color(65,245,231,100));
		
		agenda = new Agendas();		
		entrada.addKeyListener(this);
		entrada.setFont(fontinf);
		
	
		
		panelInf.add(entrada);
		
		salidaCMD = new JTextArea(30,35);
		salidaCMD.setFont(fontsup);
		JScrollPane scrollPane = new JScrollPane(salidaCMD);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panelSup.add(scrollPane);
		salidaCMD.setEditable(false);
		salidaCMD.setBackground(new Color(200,255,251,90));
		
		add(panelBotton,BorderLayout.NORTH);
		add(panelSup, BorderLayout.CENTER);
		add(panelInf,BorderLayout.SOUTH);
		
		 pack();
		 setLocationRelativeTo(null);
			
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
		salidaCMD.append("Para BORRAR un NÚMERO                borrar:numero");
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("Para GUARDAR la AGENDA                guardar:ruta");
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("Para CARGAR la AGENDA                  cargar:ruta");
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
			System.out.println("ejecutando sentencia");
			String mensaje = agenda.ejecutar(entrada.getText());
			entrada.setText("");
			salidaCMD.append(mensaje + System.lineSeparator());
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Guardar_Cargar metodo = new Guardar_Cargar(); 
		if (e.getActionCommand().equals("botonCopiar")) {
			String mensaje = metodo.Guardar();
			salidaCMD.append(mensaje + System.lineSeparator());
		}
		else if(e.getActionCommand().equals("botonCargar")) {
			String mensaje = metodo.Cargar(entrada.getText());
			salidaCMD.append(mensaje + System.lineSeparator());
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
