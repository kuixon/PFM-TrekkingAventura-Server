package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.NotFoundException;

import dao.Opinion;
import database.DatabaseManager;

public class OpinionResource {

	private int idOpinion;
	
	public OpinionResource(int idopinion) {
		this.idOpinion = idopinion;
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
			o = DatabaseManager.getInstance().obtenerOpinionPorId(idOpinion);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/idopinion={idopinion}&idusuario={idusuario}&idexcursion={idexcursion}&opinion={opinion}&imgpath={imgpath}")
	public Response updateOpinion(@PathParam("idopinion") String idopinion, @PathParam("idusuario") String idusuario,
			@PathParam("idexcursion") String idexcursion, @PathParam("opinion") String opinion,
			@PathParam("imgpath") String imgpath) {
		Response res = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (idOpinion != Integer.parseInt(idopinion)) {
				res = Response.status(409).entity("Put: Opinion '" + idopinion + "' does not match with current opinion").build();
			} else {
				res = Response.noContent().build();
				DatabaseManager.getInstance().editarOpinion(Integer.parseInt(idopinion), idusuario, Integer.parseInt(idexcursion), opinion, imgpath);
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
			DatabaseManager.getInstance().eliminarOpinion(idOpinion);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
