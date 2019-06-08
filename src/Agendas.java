import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;


public class Agendas {

	private static final long serialVersionUID = 1L;
	private Map <String, String> mapa = new TreeMap<String, String>();
	
	
	public String ejecutar(String cmd) {
		Scanner s = new Scanner(cmd);
		Guardar_Cargar Guardar_Cargar = new Guardar_Cargar();
		int estado = 0;
		String mensaje = "";
		String token;
		String nombre = null;
		
		while (estado != 5) {
			switch (estado) {
			case 0:
				try {
					mensaje = cmd;
					token = s.skip("buscar|cargar|guardar|\\p{L}+(\\s+\\p{L}+)*").match().group();
					if (token.equals("buscar")) {
						estado = 2;
						
					}
					else if(token.equals("cargar")) {
						mensaje = Guardar_Cargar.Cargar(cmd);
						estado= 5;
					}
					 else if(token.equals("guardar")) {
						mensaje = Guardar_Cargar.Guardar();
						estado =5;
					}
					else {
						nombre = token;
						estado = 1;
					}
				} catch (NoSuchElementException e) {
					mensaje = "Se esperaba 'buscar' o un nombre";
					estado = 5;
				}
				break;
			case 1:
				try {
					s.skip("-");
					estado = 3;
				}catch (NoSuchElementException e) {
					mensaje = "Se esperaba '-'";
					estado = 5;
				}
				break;
			case 2:
				try {
					s.skip(":");
					estado = 4;
				}catch (NoSuchElementException e) {
					mensaje = "Se esperaba ':'";
					estado = 5;
				}
				break;
			case 3:
				try {
					token = s.skip("\\d{9}").match().group();
					if (mapa.containsKey(nombre)) {
						mensaje = nombre + " Ya esta esta en la agenda, se modificaran los nuevos datos ";
						mapa.put(nombre,token);
					}
					else {
					mapa.put(nombre, token);
					estado = 5;
					mensaje = "El telefono de " + nombre + " fue GUARDADO CORRECTAMENTE ";
					}
					
				}catch (NoSuchElementException e) {
					mensaje = "Se esperaba un teléfono";
					estado = 5;
				}
				break;
			case 4:
				try {
					token = s.skip("\\p{L}+(\\s+\\p{L}+)*").match().group();
					String telefono = mapa.get(token);
					if (telefono != null)
						mensaje = token + " -> " + telefono;
					else
						mensaje = token + " no se encuentra en la agenda";
					estado = 5;
				} catch (NoSuchElementException e) {
					mensaje = "Se esperaba un nombre";
					estado = 5;
				}
				break;
			
			}
		}
		return mensaje;
	}


	public Map<String, String> getMapa() {
		
		return mapa;
	}


	public void setMapa(String nombre, String telefono) {
		 mapa.put(nombre,telefono);
	}
	
	public String sacarClave(String nombre) {
		
		return mapa.get(nombre);
	}
	
}
