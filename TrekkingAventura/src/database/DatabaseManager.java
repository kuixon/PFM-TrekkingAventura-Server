package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.google.appengine.api.utils.SystemProperty;

import dao.Usuario;

public class DatabaseManager {
	
	private static final Logger log = Logger.getLogger(DatabaseManager.class.getName());
	private static DatabaseManager instance = null;
	private Connection con = null;
	
	private DatabaseManager() {
		
	}
	
	public static DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	public boolean establecerConexion() throws ClassNotFoundException {
		try {
			String url = null;
			if (SystemProperty.environment.value() ==
			    SystemProperty.Environment.Value.Production) {
				// Connecting from App Engine.
				// Load the class that provides the "jdbc:google:mysql://"
				// prefix.
				Class.forName("com.mysql.jdbc.GoogleDriver");
				url = System.getProperty("ae-cloudsql.cloudsql-database-url");
			} else {
				// Connecting from an external network.
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				Class.forName("com.mysql.jdbc.Driver");
				url = System.getProperty("ae-cloudsql.local-database-url");
			}

			con = DriverManager.getConnection(url);
			log.info("Se establece la conexión con la base de datos");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR establecerConexion: " + e.getMessage());
			return false;
		}
	}
	
	public Usuario obtenerUsuarioPorId(String id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE idusuario = '" + id + "'");
			if (rs.next()) {
				Usuario u = new Usuario(rs.getString("idusuario"));
				log.info("Usuario: " + u.getIdUsuario());
				return u;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerUsuarioPorId: " + e.getMessage());
			return null;
		}
	}
}
