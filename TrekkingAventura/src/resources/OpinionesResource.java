package resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Opinion;
import dao.OpinionExtendida;
import database.DatabaseManager;

@Path("/opiniones")
public class OpinionesResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/opinion/{id}")
	public Opinion getOpinion(@PathParam("id") String id) {
		Opinion o = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			o = DatabaseManager.getInstance().obtenerOpinionPorId(Integer.parseInt(id));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usuario/{idusuario}")
	public List<OpinionExtendida> getOpinionesUsuario(@PathParam("idusuario") String idusuario) {
		List<OpinionExtendida> lo = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			lo = DatabaseManager.getInstance().obtenerOpinionesUsuario(idusuario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lo;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/excursion/{idexcursion}")
	public List<Opinion> getOpinionesExcursion(@PathParam("idexcursion") String idexcursion) {
		List<Opinion> lo = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			lo = DatabaseManager.getInstance().obtenerOpinionesExcursion(Integer.parseInt(idexcursion));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lo;
	}
	
	@GET
	@Path("/insertar/idusuario={idusuario}&idexcursion={idexcursion}&opinion={opinion}&imgpath={imgpath}")
	public Opinion newOpinion(@PathParam("idusuario") String idusuario,
			@PathParam("idexcursion") String idexcursion, @PathParam("opinion") String opinion,
			@PathParam("imgpath") String imgpath) {
		Opinion o = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			o = DatabaseManager.getInstance().insertarOpinion(idusuario, Integer.parseInt(idexcursion), opinion, imgpath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@GET
	@Path("/editar/idopinion={idopinion}&idusuario={idusuario}&idexcursion={idexcursion}&opinion={opinion}&imgpath={imgpath}")
	public Opinion updateOpinion(@PathParam("idopinion") String idopinion, @PathParam("idusuario") String idusuario,
			@PathParam("idexcursion") String idexcursion, @PathParam("opinion") String opinion,
			@PathParam("imgpath") String imgpath) {
		Opinion o = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			o = DatabaseManager.getInstance().editarOpinion(Integer.parseInt(idopinion), idusuario, Integer.parseInt(idexcursion), opinion, imgpath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@GET
	@Path("/eliminar/{id}")
	public Opinion deleteOpinion(@PathParam("id") String id) {
		Opinion o = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			o = DatabaseManager.getInstance().eliminarOpinion(Integer.parseInt(id));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
}
