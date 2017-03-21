package resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Excursion;
import dao.ExcursionDestacada;
import database.DatabaseManager;

@Path("/excursiones")
public class ExcursionesResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/excursion/{id}")
	public Excursion getExcursion(@PathParam("id") String id) {
		Excursion ex = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			ex = DatabaseManager.getInstance().obtenerExcursionPorId(Integer.parseInt(id));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ex;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/destacadas")
	public List<ExcursionDestacada> getExcursionesDestacadas() {
		List<ExcursionDestacada> led = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			led = DatabaseManager.getInstance().getExcursionesDestacadas();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return led;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/criterio/nombre={nombre}&lugar={lugar}&distancia={distancia}&nivel={nivel}")
	public List<Excursion> getExcursionesPorCriterio(@PathParam("nombre") String nombre,
			@PathParam("lugar") String lugar, @PathParam("distancia") String distancia,
			@PathParam("nivel") String nivel) {
		List<Excursion> le = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			le = DatabaseManager.getInstance().obtenerExcursionesPorCriterio(nombre, lugar, distancia, nivel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return le;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nombre/{nombre}")
	public Excursion getExcursionPorNombre(@PathParam("nombre") String nombre) {
		Excursion ex = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			ex = DatabaseManager.getInstance().obtenerExcursionPorNombre(nombre);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ex;
	}
	
	@GET
	@Path("/insertar/nombre={nombre}&nivel={nivel}&lugar={lugar}&distancia={distancia}&imgpath={imgpath}&latitud={latitud}&longitud={longitud}")
	public Excursion newExcursion(@PathParam("nombre") String nombre, @PathParam("nivel") String nivel, 
			@PathParam("lugar") String lugar, @PathParam("distancia") String distancia, 
			@PathParam("imgpath") String imgpath, @PathParam("latitud") String latitud, 
			@PathParam("longitud") String longitud) {
		Excursion ex = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			ex = DatabaseManager.getInstance().insertarExcursion(nombre, nivel, lugar, Double.parseDouble(distancia), imgpath, Float.parseFloat(latitud), Float.parseFloat(longitud));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ex;
	}
}
