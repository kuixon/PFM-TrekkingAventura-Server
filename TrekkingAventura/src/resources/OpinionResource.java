package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.NotFoundException;

import dao.Opinion;
import database.DatabaseManager;

public class OpinionResource {

	private int idopinion;
	
	public OpinionResource(int idopinion) {
		this.idopinion = idopinion;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerOpinionPorId(idopinion) == null)
				throw new NotFoundException("Get: Opinion '" + idopinion + "' not found");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Opinion getOpinion() {
		Opinion o = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			o = DatabaseManager.getInstance().obtenerOpinionPorId(idopinion);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOpinion(@Context UriInfo uriInfo, Opinion opinion) {
		Response res = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (idopinion != opinion.getIdOpinion()) {
				res = Response.status(409).entity("Put: Opinion '" + opinion.getIdOpinion() + "' does not match with current opinion").build();
			} else {
				res = Response.noContent().build();
				DatabaseManager.getInstance().editarOpinion(opinion);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@DELETE
	public void deleteOpinion() {
		try {
			DatabaseManager.getInstance().establecerConexion();
			DatabaseManager.getInstance().eliminarOpinion(idopinion);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
