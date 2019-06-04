import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class Agendas extends TreeMap<String, String> {

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
					token = s.skip("buscar|\\p{L}+(\\s+\\p{L}+)*").match().group();
					if (token.equals("buscar"))
						estado = 2;
					else {
						nombre = token;
						estado = 1;
					}
				} catch (NoSuchElementException e) {
					mensaje = "Se esperaba 'buscar' o 'fin' o un nombre";
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
					put(nombre, token);
					estado = 5;
				}catch (NoSuchElementException e) {
					mensaje = "Se esperaba un telÈfono";
					estado = 5;
				}
				break;
			case 4:
				try {
					token = s.skip("\\p{L}+(\\s+\\p{L}+)*").match().group();
					String telefono = get(token);
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