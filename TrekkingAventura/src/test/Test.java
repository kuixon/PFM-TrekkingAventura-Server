package test;

import dao.Usuario;
import utilities.ClientManager;

public class Test {

	public static void main(String[] args) {
		// USUARIOS
		Usuario us1 = new Usuario("Usuario 1");
		Usuario us2 = new Usuario("Usuario 2");
		Usuario u;
		ClientManager.getInstance().insertarUsuario(us1);
		ClientManager.getInstance().insertarUsuario(us2);
		u = ClientManager.getInstance().obtenerUsuarioPorId(us1.getIdUsuario());
		System.out.println("Id: " + u.getIdUsuario());
		u = ClientManager.getInstance().obtenerUsuarioPorId(us2.getIdUsuario());
		System.out.println("Id: " + u.getIdUsuario());
	}

}
