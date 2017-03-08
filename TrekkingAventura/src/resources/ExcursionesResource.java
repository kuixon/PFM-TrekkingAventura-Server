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
		Usuario u = new Usuario();
		try {
			DatabaseManager.getInstance().establecerConexion();
			u = DatabaseManager.getInstance().obtenerUsuarioPorId(msg);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String output = "Id usuario " + u.getIdUsuario();

		return Response.status(200).entity(output).build();

	}
}
