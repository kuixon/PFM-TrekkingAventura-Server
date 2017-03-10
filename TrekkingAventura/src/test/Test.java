package test;

import java.util.ArrayList;

import dao.Excursion;
import dao.Usuario;
import utilities.ClientManager;

public class Test {

	public static void main(String[] args) {
		// USUARIOS
		// Insertar
		Usuario us1 = new Usuario("Usuario 1");
		Usuario us2 = new Usuario("Usuario 2");
		Usuario us3 = new Usuario("Usuario 3");
		Usuario usAux = new Usuario("Usuario 3");
		
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
		
		if (ClientManager.getInstance().insertarUsuario(usAux)) {
			System.out.println("Usuario existente insertado correctamente.");
		} else {
			System.out.println("No se ha podido insertar el usuario porque ya existe en la BD");
		}
		System.out.println();
		
		// Obtener Usuarios
		us1 = ClientManager.getInstance().obtenerUsuarioPorId("Usuario 1");
		us2 = ClientManager.getInstance().obtenerUsuarioPorId("Usuario 2");
		us3 = ClientManager.getInstance().obtenerUsuarioPorId("Usuario 3");
		System.out.println("Usuarios obtenidos:");
		System.out.println(us1.toString());
		System.out.println(us2.toString());
		System.out.println(us3.toString());
		System.out.println();
		
		// EXCURSIONES
		// Insertar
		Excursion exc1 = new Excursion("Ruta del Cares", "Medio", "Arenas de Cabrales", 12, "http://res.cloudinary.com/trekkingaventura/image/upload/c2a61b1cd1ac1d22_1_1.jpg", 43.253143f, -4.844181f);
		Excursion exc2 = new Excursion("Ventana Relux", "Facil", "Karrantza Harana", 2.7, "http://res.cloudinary.com/trekkingaventura/image/upload/c2a61b1cd1ac1d22_2_2.jpg", 43.250062f, -3.411184f);
		Excursion exc3 = new Excursion("Faro del Caballo", "Medio", "Santoña", 12, "http://res.cloudinary.com/trekkingaventura/image/upload/c2a61b1cd1ac1d22_3_3.jpg", 43.451618f, -3.425712f);
		Excursion exc4 = new Excursion("Gorbea", "Dificil", "Areatza", 12, "http://res.cloudinary.com/trekkingaventura/image/upload/c2a61b1cd1ac1d22_4_4.jpg", 43.034985f, -2.779891f);
		Excursion exc5 = new Excursion("Ruta del Río Borosa", "Facil", "Jaén", 20, "http://res.cloudinary.com/trekkingaventura/image/upload/c2a61b1cd1ac1d22_5_5.jpg", 38.00972f, -2.858513f);
		Excursion exc6 = new Excursion("Cahorros del Río Chiller", "Facil", "Neria", 15, "http://res.cloudinary.com/trekkingaventura/image/upload/c2a61b1cd1ac1d22_6_6.jpg", 36.831615f, -3.853639f);
		Excursion exc7 = new Excursion("Ruta de los Pantaneros", "Dificil", "Chulilla", 5, "http://res.cloudinary.com/trekkingaventura/image/upload/c2a61b1cd1ac1d22_7_7.jpg", 39.670967f, -0.888563f);
		Excursion excExistente = new Excursion();
		excExistente.setIdExcursion(1);
		excExistente.setNombre("Existente");
		excExistente.setNivel("Facil");
		excExistente.setLugar("No tiene");
		excExistente.setDistancia(0);
		excExistente.setFoto("fotoPath");
		excExistente.setLatitud(0f);
		excExistente.setLongitud(0f);
		
		if (ClientManager.getInstance().insertarExcursion(exc1)) {
			System.out.println("Excursion 'Ruta del Cares' insertada correctamente.");
		} else {
			System.out.println("NO ha podido insertarse la excursion 'Ruta del Cares'.");
		}
		
		if (ClientManager.getInstance().insertarExcursion(exc2)) {
			System.out.println("Excursion 'Ventana Relux' insertada correctamente.");
		} else {
			System.out.println("NO ha podido insertarse la excursion 'Ventana Relux'.");
		}
		
		if (ClientManager.getInstance().insertarExcursion(exc3)) {
			System.out.println("Excursion 'Faro del Caballo' insertada correctamente.");
		} else {
			System.out.println("NO ha podido insertarse la excursion 'Faro del Caballo'.");
		}
		
		if (ClientManager.getInstance().insertarExcursion(exc4)) {
			System.out.println("Excursion 'Gorbea' insertada correctamente.");
		} else {
			System.out.println("NO ha podido insertarse la excursion 'Gorbea'.");
		}
		
		if (ClientManager.getInstance().insertarExcursion(exc5)) {
			System.out.println("Excursion 'Ruta del Río Borosa' insertada correctamente.");
		} else {
			System.out.println("NO ha podido insertarse la excursion 'Ruta del Río Borosa'.");
		}
		
		if (ClientManager.getInstance().insertarExcursion(exc6)) {
			System.out.println("Excursion 'Cahorros del Río Chiller' insertada correctamente.");
		} else {
			System.out.println("NO ha podido insertarse la excursion 'Cahorros del Río Chiller'.");
		}
		
		if (ClientManager.getInstance().insertarExcursion(exc7)) {
			System.out.println("Excursion 'Ruta de los Pantaneros' insertada correctamente.");
		} else {
			System.out.println("NO ha podido insertarse la excursion 'Ruta de los Pantaneros'.");
		}
		
		if (ClientManager.getInstance().insertarExcursion(excExistente)) {
			System.out.println("Excursion existente insertada correctamente.");
		} else {
			System.out.println("NO se ha podido insertar esa excursión porque ya existe en la BD.");
		}
		System.out.println();
		
		// Obtener excursion por nombre
		Excursion excObtenida = ClientManager.getInstance().obtenerExcursionPorNombre("Ruta del Cares");
		System.out.println("Excursión obtenida por nombre:");
		System.out.println(excObtenida.toString());
		System.out.println();
		
		// Obtener excursion por id
		excObtenida = ClientManager.getInstance().obtenerExcursionPorId(excObtenida.getIdExcursion());
		System.out.println("Excursión obtenida por id:");
		System.out.println(excObtenida.toString());
		System.out.println();

		// Obtener excursiones por criterio
		String criterio = "nulo-nulo-nulo-nulo";
		System.out.println("Excursiones que cumplen el criterio 1: '" + criterio + "'.");
		ArrayList<Excursion> excursionesCrit = ClientManager.getInstance().obtenerExcursionesPorCriterio(criterio);
		for (Excursion e : excursionesCrit) {
			System.out.println(e.toString());
		}
		System.out.println();
		
		criterio = "nulo-nulo-nulo-Facil";
		System.out.println("Excursiones que cumplen el criterio 2: '" + criterio + "'.");
		excursionesCrit = ClientManager.getInstance().obtenerExcursionesPorCriterio(criterio);
		for (Excursion e : excursionesCrit) {
			System.out.println(e.toString());
		}
		System.out.println();
		
		criterio = "nulo-nulo-12-Medio";
		System.out.println("Excursiones que cumplen el criterio 3: '" + criterio + "'.");
		excursionesCrit = ClientManager.getInstance().obtenerExcursionesPorCriterio(criterio);
		for (Excursion e : excursionesCrit) {
			System.out.println(e.toString());
		}
		System.out.println();
		
		criterio = "Zalama-nulo-nulo-nulo";
		System.out.println("Excursiones que cumplen el criterio 4: '" + criterio + "'.");
		excursionesCrit = ClientManager.getInstance().obtenerExcursionesPorCriterio(criterio);
		if (excursionesCrit.size() == 0) {
			System.out.println("No existen excursiones que cumplan con esos criterios");
		} else {
			for (Excursion e : excursionesCrit) {
				System.out.println(e.toString());
			}
		}
		System.out.println();
		
		// Eliminar excursion
		System.out.println("Eliminamos la excursión 'Ruta del Cares':");
		ClientManager.getInstance().eliminarExcursion(excObtenida.getIdExcursion());
		System.out.println("Excursión eliminada correctamente.");
		System.out.println();
	}
}
