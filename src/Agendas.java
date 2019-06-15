import java.io.BufferedWriter;
import java.io.File;
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
		int estado = 0;
		String mensaje = "";
		String token;
		String nombre = null;
		
		while (estado != 5) {
			switch (estado) {
			case 0:
				try {
					mensaje = cmd;
					token = s.skip("buscar|cargar|guardar|borrar:|\\p{L}+(\\s+\\p{L}+)*").match().group();
					if (token.equals("buscar")) {
						estado = 2;
						
					}
					else if(token.equals("cargar")) {
						
						
						File fichero = new File("src/Recursos/contactos.txt");
						Scanner s2 = null;
						Scanner r = new Scanner(System.in);


						try {
							// Leemos el contenido del fichero
							s2 = new Scanner(fichero);

							// Leemos linea a linea el fichero
							while (s2.hasNextLine()) {
								String linea = s2.nextLine(); // Guardamos la linea en un String
								String[] partes2 = linea.split("-");
								String nombre2 = partes2[0].trim();
								String numero2 = partes2[1].trim();

							mapa.put(nombre2, numero2);
							mensaje =  " Fue cargado con exito con exito" +  System.lineSeparator();
							}

						} catch (Exception ex) {
							mensaje = "Mensaje: " + ex.getMessage();
							
						} finally {
							// Cerramos el fichero tanto si la lectura ha sido correcta o no
							try {
								if (s2 != null)
									s2.close();
							} catch (Exception ex2) {
								mensaje = "Mensaje 2: " + ex2.getMessage();
								
							}
						}
						
						
						
						
						
						estado= 5;
					}
					 else if(token.equals("guardar")) {
						
						 Writer out = null;
							try {
								out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/Recursos/contactos.txt"), "UTF-8"));

								// Escribimos linea a linea en el fichero
								for (Map.Entry<String, String> entry : mapa.entrySet()) {

									try {

										out.write(entry.getKey() + " - " + entry.getValue() + "\n");

										mensaje =  " Fué guardado con exito" + System.lineSeparator();
										
										
									} catch (IOException ex) {
										mensaje = "Mensaje excepción escritura: " + ex.getMessage();
									}
								}

							} catch (UnsupportedEncodingException | FileNotFoundException ex2) {
								mensaje = "Mensaje error 2: " + ex2.getMessage();
							} finally {
								try {
									out.close();
								} catch (IOException ex3) {
									mensaje = "Mensaje error cierre fichero: " + ex3.getMessage();
								}
							}
						 
						estado =5;
					}
					
					 else if(token.equals("borrar:")) {
						 token = s.skip("[\\w]*").match().group();
						 nombre=token;
						mapa.remove(nombre);
						mensaje = nombre + "Borrado con exito";
						estado = 5;
					 }
					else {
						nombre = token;
						estado = 1;
					}
				} catch (NoSuchElementException e) {
					mensaje = "Se esperaba 'buscar:' 'borrar:'  'guardar'  'cargar'  'nombre-telefono' ";
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
						mensaje = nombre + " Ya esta esta en la agenda, se modificarán los nuevos datos ";
						mapa.put(nombre,token);
					}
					else {
					mapa.put(nombre, token);
					estado = 5;
					mensaje = " GUARDADO CORRECTAMENTE ";
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


	
}
