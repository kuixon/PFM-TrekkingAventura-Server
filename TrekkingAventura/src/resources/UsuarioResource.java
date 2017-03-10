package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.NotFoundException;

import dao.Usuario;
import database.DatabaseManager;

public class UsuarioResource {
	
	private String idusuario;
	
	public UsuarioResource(String idusuario) {
		this.idusuario = idusuario;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerUsuarioPorId(idusuario) == null)
				throw new NotFoundException("Get: Usuario '" + idusuario + "' not found");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario() {
		try {
			DatabaseManager.getInstance().establecerConexion();
			return DatabaseManager.getInstance().obtenerUsuarioPorId(idusuario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
