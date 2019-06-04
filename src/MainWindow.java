import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
public class MainWindow extends JFrame implements KeyListener {

	protected static JTextArea salidaCMD;
	protected static JTextField entrada = new JTextField();
	private static final long serialVersionUID = 1L;
	private Image foto;
	
	public MainWindow() {
		Toolkit ventana = Toolkit.getDefaultToolkit();
		Dimension tamano = ventana.getScreenSize();
		int altura = tamano.height;
		int ancho = tamano.width;
		setSize(ancho/2, altura/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Adrián - Interfaz Grafica");
		setVisible(true);
	
		
		JPanel panelInf = new JPanel(new GridLayout(1, 1));
		JPanel panelSup= new JPanel();
		Agendas agenda = new Agendas();
		agenda.ejecutar(entrada.getText());
 
	
		
		panelInf.add(entrada);
		add(panelInf,BorderLayout.SOUTH);
		
		
		
		salidaCMD = new JTextArea(10,70);
		
		JScrollPane scrollPane = new JScrollPane(salidaCMD);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(700, 350));
		
		panelSup.add(salidaCMD);
		panelSup.add(scrollPane);
		scrollPane.setViewportView(salidaCMD);
		add(panelSup, BorderLayout.NORTH);
		salidaCMD.setEditable(false);
		
		
		 try{
	            foto = ImageIO.read(new File("/RES/fondo_TexField.jpg"));
	        } catch(IOException e) {
	        	salidaCMD.append(e.toString());
	        }
		
		 pack();
		 setLocationRelativeTo(null);
			
	}
	
	public void mostrarAyuda() {
		
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("øQUE DESEA HACER?");
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("Para introducir nombre nombre-telefono");
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("Para buscar un n˙mero buscar:nombre");
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("Para borrar un n˙mero borrar:numero");
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("Para guardar la Agenda guardar:ruta");
		salidaCMD.append(System.getProperty("line.separator")); 
		salidaCMD.append("Para cargar la Agenda cargar:ruta");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}