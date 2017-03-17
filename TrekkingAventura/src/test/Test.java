package test;

import java.util.ArrayList;

import dao.Excursion;
import dao.Opinion;
import dao.Usuario;
import utilities.ClientManager;

public class Test {

	public static void main(String[] args) {
		// USUARIOS
		// Insertar usuarios
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
		// Insertar excursiones
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
		System.out.println("Eliminamos la excursión 'Ruta de los Pantaneros':");
		ClientManager.getInstance().eliminarExcursion(7);
		System.out.println("Excursión eliminada correctamente.");
		System.out.println();
		
		// OPINIONES
		// Insertar opiniones
		Opinion opinion1 = new Opinion("Usuario 1", 1, "Opinion Usuario 1 sobre Ruta del Cares", "foto Usuario 1 Ruta del Cares");
		Opinion opinion2 = new Opinion("Usuario 1", 2, "Opinion Usuario 1 sobre Faro del Caballo", "foto Usuario 1 Faro del Caballo");
		Opinion opinion3 = new Opinion("Usuario 1", 3, "Opinion Usuario 1 sobre Ventana Relux", "foto Usuario 1 Ventana Relux");
		Opinion opinion4 = new Opinion("Usuario 2", 1, "Opinion Usuario 2 sobre Ruta del Cares", "foto Usuario 2 Ruta del Cares");
		Opinion opinion5 = new Opinion("Usuario 3", 1, "Opinion Usuario 3 sobre Ruta del Cares", "foto Usuario 3 Ruta del Cares");
		Opinion opinionExistente = new Opinion();
		opinionExistente.setIdOpinion(1);
		opinionExistente.setIdUsuario("Usuario 1");
		opinionExistente.setIdExcursion(1);
		opinionExistente.setOpinion("opinion");
		opinionExistente.setFoto("foto");
		
		if (ClientManager.getInstance().insertarOpinion(opinion1)) {
			System.out.println("Opinion 1 insertada correctamente.");
		} else {
			System.out.println("No se ha podido insertar la Opinion 1");
		}
		
		if (ClientManager.getInstance().insertarOpinion(opinion2)) {
			System.out.println("Opinion 2 insertada correctamente.");
		} else {
			System.out.println("No se ha podido insertar la Opinion 2");
		}
		
		if (ClientManager.getInstance().insertarOpinion(opinion3)) {
			System.out.println("Opinion 3 insertada correctamente.");
		} else {
			System.out.println("No se ha podido insertar la Opinion 3");
		}
		
		if (ClientManager.getInstance().insertarOpinion(opinion4)) {
			System.out.println("Opinion 4 insertada correctamente.");
		} else {
			System.out.println("No se ha podido insertar la Opinion 4");
		}
		
		if (ClientManager.getInstance().insertarOpinion(opinion5)) {
			System.out.println("Opinion 5 insertada correctamente.");
		} else {
			System.out.println("No se ha podido insertar la Opinion 5");
		}
		
		if (ClientManager.getInstance().insertarOpinion(opinionExistente)) {
			System.out.println("Opinion Existente insertada correctamente.");
		} else {
			System.out.println("No se ha podido insertar la Opinion porque ya existe en la BD");
		}
		System.out.println();
		
		// Obtener Opiniones Usuario
		System.out.println("Opiniones del Usuario 'Usuario 1':");
		ArrayList<Opinion> opinionesObtenidas = ClientManager.getInstance().obtenerOpinionesUsuario("Usuario 1");
		if (opinionesObtenidas.size() == 0) {
			System.out.println("No existen opiniones para este usuario.");
		} else {
			for (Opinion o : opinionesObtenidas) {
				System.out.println(o.toString());
			}
		}
		System.out.println();
		
		System.out.println("Opiniones del Usuario 'Usuario 5':");
		opinionesObtenidas = ClientManager.getInstance().obtenerOpinionesUsuario("Usuario 5");
		if (opinionesObtenidas.size() == 0) {
			System.out.println("No existen opiniones para este usuario.");
		} else {
			for (Opinion o : opinionesObtenidas) {
				System.out.println(o.toString());
			}
		}
		System.out.println();
		
		// Obtener Opiniones Excursion
		System.out.println("Opiniones de la excursion 'Ruta del Cares':");
		opinionesObtenidas = ClientManager.getInstance().obtenerOpinionesExcursion(1);
		if (opinionesObtenidas.size() == 0) {
			System.out.println("No existen opiniones sobre esta excursión");
		} else {
			for (Opinion o : opinionesObtenidas) {
				System.out.println(o.toString());
			}
		}
		System.out.println();
		
		System.out.println("Opiniones de la excursion 'Gorbea':");
		opinionesObtenidas = ClientManager.getInstance().obtenerOpinionesExcursion(4);
		if (opinionesObtenidas.size() == 0) {
			System.out.println("No existen opiniones sobre esta excursión");
		} else {
			for (Opinion o : opinionesObtenidas) {
				System.out.println(o.toString());
			}
		}
		System.out.println();
		
		// Editar opinion
		System.out.println("Editamos la opinion del Usuario 1 sobre la Ventana Relux:");
		opinion3.setIdOpinion(3);
		opinion3.setOpinion("Hemos editado esta opinión");
		if (ClientManager.getInstance().editarOpinion(opinion3)) {
			System.out.println("La Opinion 3 se ha editado correctamente.");
		} else {
			System.out.println("NO se ha podido editar la Opinion 3");
		}
		System.out.println();
		
		// Eliminar opinion
		System.out.println("Eliminamos la opinion del Usuario 3 sobre la ruta del Cares:");
		ClientManager.getInstance().eliminarOpinion(5);
		System.out.println("La Opinion 5 se ha eliminado correctamente.");
	}
}