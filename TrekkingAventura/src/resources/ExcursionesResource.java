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

import dao.Excursion;
import database.DatabaseManager;

@Path("/excursiones")
public class ExcursionesResource {
	
	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/criterio/{criterio}")
	public List<Excursion> getExcursionesPorCriterio(@PathParam("criterio") String criterio) {
		try {
			DatabaseManager.getInstance().establecerConexion();
			return DatabaseManager.getInstance().obtenerExcursionesPorCriterio(criterio);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nombre/{nombre}")
	public Excursion getExcursionPorNombre(@PathParam("nombre") String nombre) {
		try {
			DatabaseManager.getInstance().establecerConexion();
			return DatabaseManager.getInstance().obtenerExcursionPorNombre(nombre);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newExcursion(Excursion excursion) {
		Response res = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerExcursionPorId(excursion.getIdExcursion()) != null) {
				res = Response.status(409).entity("Post: Excursion '" + excursion.getIdExcursion() + "' already exists").build();
			} else {
				URI uri = uriInfo.getAbsolutePathBuilder().path("excursion").path(Integer.toString(excursion.getIdExcursion())).build();
				res = Response.created(uri).entity(excursion).build();
				DatabaseManager.getInstance().insertarExcursion(excursion);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("/excursion/{id}")
	public ExcursionResource getExcursion(@PathParam("id") String id) {
		return new ExcursionResource(Integer.parseInt(id));
	}
}
