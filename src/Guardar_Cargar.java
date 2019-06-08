import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;
import java.util.Scanner;

public class Guardar_Cargar {

	public String Guardar() {
		String mensaje = "";
		Writer out = null;
		Agendas mapa = new Agendas();
	
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Recursos/contactos.txt"), "UTF-8"));

			// Escribimos linea a linea en el fichero
			for (Map.Entry<String, String> entry : mapa.getMapa().entrySet()) {

				try {

					out.write(entry.getKey() + " - " + entry.getValue() + "\n");

					mensaje = "EL contacto " + entry.getKey() + " - " + entry.getValue() + " fué guardado con exito";
	
					
					
				} catch (IOException ex) {
					mensaje ="Mensaje excepcion escritura: " + ex.getMessage();
					
				}
			}
		

	} catch (UnsupportedEncodingException | FileNotFoundException ex2) {
		mensaje = "Mensaje error 2: " + ex2.getMessage();
		
	} finally {
		
		try {
			out.close();
		} catch (IOException ex3) {
			mensaje ="Mensaje error cierre fichero: " + ex3.getMessage();
			
		}
	}

		
	return mensaje;	
		
	}

	public String Cargar(String pregunta) {
		String mensaje = "";
		Agendas mapa = new Agendas();
		File fichero = new File("/Recursos/contactos.txt");
		Scanner s = null;
		Scanner r = new Scanner(System.in);

		fichero = new File("/Recursos/contactos.txt");
		

		try {
			// Leemos el contenido del fichero
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String linea = s.nextLine(); // Guardamos la linea en un String
				String[] partes2 = linea.split("-");
				String nombre2 = partes2[0].trim();
				String numero2 = partes2[1].trim();

				if (!mapa.getMapa().containsKey(nombre2)) {
					mapa.setMapa(nombre2, numero2);
					mensaje =("El contacto se ha cargado correctamente en la agenda desde el fichero");
				
					nombre2 = null;
					numero2 = null;
				} else {

					mensaje="El número ya está en la Agenda";
				
					mensaje="¿Qué desea hacer? Escriba el numero de la orden";
					
					mensaje = "1. Desea guardar " + nombre2 + "-" + mapa.sacarClave(nombre2);
					
					mensaje = "2. Desea guardar " + nombre2 + "-" + numero2;
					
			


					pregunta = r.nextLine();

					if (pregunta.contains("1")) {
						mensaje = "El teléfono que estaba en la Agenda ha sido guardado con exito";
						
						nombre2 = null;
						numero2 = null;

					} else if (pregunta.contains("2")) {
						mapa.setMapa(nombre2, numero2);
						nombre2 = null;
						numero2 = null;
						mensaje="Se ha sobrescribido el número que estaba en la Agenda por el del fichero";
						

					}

					else {
						do {
							mensaje = "El número ya está en la Agenda";
							
							mensaje="¿Qué desea hacer? Escriba el numero de la orden";
							
							mensaje = "1. Desea guardar " + nombre2 + "-" + mapa.sacarClave(nombre2);
							
							mensaje = "2. Desea guardar " + nombre2 + "-" + numero2;
							
							mensaje = "Se esperaba un 1 o un 2";
							

						
							pregunta = r.nextLine();
							if (pregunta.contains("1") || pregunta.contains("2")) {
								break;
							}

						} while (!pregunta.contains("1") || !pregunta.contains("2"));

						// cargar:./prueba.txt

					}

				}

			}

		} catch (Exception ex) {
			mensaje = "Mensaje: " + ex.getMessage();
			
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				mensaje = "Mensaje 2: " + ex2.getMessage();
				
			}
		}
		
		return mensaje;
	}
	
	

	
}
