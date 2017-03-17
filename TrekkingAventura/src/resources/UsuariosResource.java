package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Usuario;
import database.DatabaseManager;

@Path("/usuarios")
public class UsuariosResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usuario/{idusuario}")
	public Usuario getUsuario(@PathParam("idusuario") String idusuario) {
		Usuario u = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			u = DatabaseManager.getInstance().obtenerUsuarioPorId(idusuario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@GET
	@Path("/insertar/{idUsuario}")
	public Usuario newUsuario(@PathParam("idUsuario") String idUsuario) {
		try {
			DatabaseManager.getInstance().establecerConexion();
			return DatabaseManager.getInstance().insertarUsuario(idUsuario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
