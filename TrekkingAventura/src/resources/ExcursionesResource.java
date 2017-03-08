package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.Usuario;
import database.DatabaseManager;

@Path("/excursiones")
public class ExcursionesResource {
	
	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		Usuario u = null;
		try {
			DatabaseManager.getInstance().establecerConexion();
			u = DatabaseManager.getInstance().obtenerUsuarioPorId(msg);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String output;
		if (u != null) {
			output = "Existe en la BD un usuario con el id: " + u.getIdUsuario();
		} else {
			output = "No existe en la BD un usuario con ese id";
		}

		return Response.status(200).entity(output).build();

	}
}
