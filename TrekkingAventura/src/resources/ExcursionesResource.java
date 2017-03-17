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
	@Path("/criterio/nombre={nombre}&lugar={lugar}&distancia={distancia}&nivel={nivel}")
	public List<Excursion> getExcursionesPorCriterio(@PathParam("nombre") String nombre,
			@PathParam("lugar") String lugar, @PathParam("distancia") String distancia,
			@PathParam("nivel") String nivel) {
		try {
			DatabaseManager.getInstance().establecerConexion();
			return DatabaseManager.getInstance().obtenerExcursionesPorCriterio(nombre, lugar, distancia, nivel);
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
	@Path("/insertar/idexcursion={idexcursion}&nombre={nombre}&nivel={nivel}&lugar={lugar}&distancia={distancia}&imgpath={imgpath}&latitud={latitud}&longitud={longitud}")
	public Response newExcursion(@PathParam("idexcursion") String idexcursion, @PathParam("nombre") String nombre, 
			@PathParam("nivel") String nivel, @PathParam("lugar") String lugar, @PathParam("distancia") String distancia, 
			@PathParam("imgpath") String imgpath, @PathParam("latitud") String latitud, 
			@PathParam("longitud") String longitud) {
		Response res = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerExcursionPorId(Integer.parseInt(idexcursion)) != null) {
				res = Response.status(409).entity("Post: Excursion '" + idexcursion + "' already exists").build();
			} else {
				URI uri = uriInfo.getAbsolutePathBuilder().path("excursion").path(idexcursion).build();
				res = Response.created(uri).entity(new Excursion(Integer.parseInt(idexcursion), nombre, nivel, lugar, Double.parseDouble(distancia), imgpath, Float.parseFloat(latitud), Float.parseFloat(longitud))).build();
				DatabaseManager.getInstance().insertarExcursion(nombre, nivel, lugar, Double.parseDouble(distancia), imgpath, Float.parseFloat(latitud), Float.parseFloat(longitud));
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
