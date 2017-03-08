package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.appengine.api.utils.SystemProperty;

import dao.Usuario;
import database.DatabaseManager;

@Path("/excursiones")
public class ExcursionesResource {
	
	private static final Logger log = Logger.getLogger(ExcursionesResource.class.getName());
	
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
