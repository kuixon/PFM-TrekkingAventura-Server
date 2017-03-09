package resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.NotFoundException;

import dao.Excursion;
import database.DatabaseManager;

public class ExcursionResource {
	
	private int idexcursion;
	
	public ExcursionResource(int idexcursion) {
		this.idexcursion = idexcursion;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerExcursionPorId(idexcursion) == null)
				throw new NotFoundException("Get: Excursion '" + idexcursion + "' not found");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Excursion getExcursion() {
		Excursion ex = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			ex = DatabaseManager.getInstance().obtenerExcursionPorId(idexcursion);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ex;
	}
	
	@DELETE
	public void deleteExcursion() {
		try {
			DatabaseManager.getInstance().establecerConexion();
			DatabaseManager.getInstance().eliminarExcursion(idexcursion);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
