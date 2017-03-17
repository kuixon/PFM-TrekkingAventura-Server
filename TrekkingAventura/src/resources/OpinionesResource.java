package resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.Opinion;
import database.DatabaseManager;

@Path("/opiniones")
public class OpinionesResource {
	
	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usuario/{idusuario}")
	public List<Opinion> getOpinionesUsuario(@PathParam("idusuario") String idusuario) {
		try {
			DatabaseManager.getInstance().establecerConexion();
			return DatabaseManager.getInstance().obtenerOpinionesUsuario(idusuario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/excursion/{idexcursion}")
	public List<Opinion> getOpinionesExcursion(@PathParam("idexcursion") String idexcursion) {
		try {
			DatabaseManager.getInstance().establecerConexion();
			return DatabaseManager.getInstance().obtenerOpinionesExcursion(Integer.parseInt(idexcursion));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/insertar/idopinion={idopinion}&idusuario={idusuario}&idexcursion={idexcursion}&opinion={opinion}&imgpath={imgpath}")
	public Response newOpinion(@PathParam("idopinion") String idopinion, @PathParam("idusuario") String idusuario,
			@PathParam("idexcursion") String idexcursion, @PathParam("opinion") String opinion,
			@PathParam("imgpath") String imgpath) {
		Response res = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerOpinionPorId(Integer.parseInt(idopinion)) != null) {
				res = Response.status(409).entity("Post: Opinion '" + idopinion + "' already exists").build();
			} else {
				URI uri = uriInfo.getAbsolutePathBuilder().path("opinion").path(idopinion).build();
				res = Response.created(uri).entity(new Opinion(Integer.parseInt(idopinion), idusuario, Integer.parseInt(idexcursion), opinion, imgpath)).build();
				DatabaseManager.getInstance().insertarOpinion(idusuario, Integer.parseInt(idexcursion), opinion, imgpath);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("/opinion/{id}")
	public OpinionResource getOpinion(@PathParam("id") String id) {
		return new OpinionResource(Integer.parseInt(id));
	}
}
