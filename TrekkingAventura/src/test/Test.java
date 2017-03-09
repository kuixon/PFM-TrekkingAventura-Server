package test;

import dao.Usuario;
import utilities.ClientManager;

public class Test {

	public static void main(String[] args) {
		// USUARIOS
		
		// Insertar
		Usuario us1 = new Usuario("Usuario 1");
		Usuario us2 = new Usuario("Usuario 2");
		Usuario us3 = new Usuario("Usuario 3");
		
		if (ClientManager.getInstance().insertarUsuario(us1)) {
			System.out.println("Usuario 1 insertado correctamente.");
		} else {
			System.out.println("No se ha podido insertar el Usuario 1");
		}
		
		if (ClientManager.getInstance().insertarUsuario(us2)) {
			System.out.println("Usuario 2 insertado correctamente.");
		} else {
			System.out.println("No se ha podido insertar el Usuario 2");
		}
		
		if (ClientManager.getInstance().insertarUsuario(us3)) {
			System.out.println("Usuario 3 insertado correctamente.");
		} else {
			System.out.println("No se ha podido insertar el Usuario 3");
		}
		
		// Obtener Usuarios
		us1 = ClientManager.getInstance().obtenerUsuarioPorId("Usuario 1");
		us2 = ClientManager.getInstance().obtenerUsuarioPorId("Usuario 2");
		us3 = ClientManager.getInstance().obtenerUsuarioPorId("Usuario 3");
		System.out.println(us1.toString());
		System.out.println(us2.toString());
		System.out.println(us3.toString());
	}

}
