package resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.Usuario;
import database.DatabaseManager;

@Path("/usuarios")
public class UsuariosResource {
	
	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newUsuario(Usuario usuario) {
		Response res = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerUsuarioPorId(usuario.getIdUsuario()) != null) {
				res = Response.status(409).entity("Post: Usuario '" + usuario.getIdUsuario() + "' already exists").build();
			} else {
				URI uri = uriInfo.getAbsolutePathBuilder().path(usuario.getIdUsuario()).build();
				res = Response.created(uri).entity(usuario).build();
				DatabaseManager.getInstance().insertarUsuario(usuario);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("{idusuario}")
	public UsuarioResource getUsuario(@PathParam("idusuario") String idusuario) {
		return new UsuarioResource(idusuario);
	}
}
