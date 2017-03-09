package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.utils.SystemProperty;

import dao.Excursion;
import dao.Opinion;
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
	
	// USUARIOS
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
	
	public void insertarUsuario(Usuario u) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO usuario VALUES('" + u.getIdUsuario() + "')");
			log.info("Usuario insertado correctamente en la base de datos.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR insertarUsuario: " + e.getMessage());
		}
	}
	
	// EXCURSIONES
	public Excursion obtenerExcursionPorId(int id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM excursion WHERE idexcursion = " + id);
			if (rs.next()) {
				Excursion e = new Excursion();
				e.setIdExcursion(rs.getInt("idexcursion"));
				e.setNombre(rs.getString("nombre"));
				e.setNivel(rs.getString("nivel"));
				e.setLugar(rs.getString("lugar"));
				e.setDistancia(rs.getDouble("distancia"));
				e.setFoto(rs.getString("imgpath"));
				e.setLatitud(rs.getFloat("latitud"));
				e.setLongitud(rs.getFloat("longitud"));
				log.info("Excursion: " + e.getIdExcursion());
				return e;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerExcursionPorId: " + e.getMessage());
			return null;
		}
	}
	
	public Excursion obtenerExcursionPorNombre(String nombre) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM excursion WHERE nombre = '" + nombre + "'");
			if (rs.next()) {
				Excursion e = new Excursion();
				e.setIdExcursion(rs.getInt("idexcursion"));
				e.setNombre(rs.getString("nombre"));
				e.setNivel(rs.getString("nivel"));
				e.setLugar(rs.getString("lugar"));
				e.setDistancia(rs.getDouble("distancia"));
				e.setFoto(rs.getString("imgpath"));
				e.setLatitud(rs.getFloat("latitud"));
				e.setLongitud(rs.getFloat("longitud"));
				log.info("Excursion: " + e.getIdExcursion());
				return e;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerExcursionPorNombre: " + e.getMessage());
			return null;
		}
	}
	
	public List<Excursion> obtenerExcursionesPorCriterio(String criterio) {
		final String[] criterios = criterio.split("|");
		final String critNombre = criterios[0];
		final String critLugar = criterios[1];
		final double critDistancia = Double.parseDouble(criterios[2]);
		final String critNivel = criterios[3];
		
		List<Excursion> ale = new ArrayList<Excursion>();
		
		final String condNombre = critNombre.equals("nulo") ? "" : "nombre = '" + critNombre + "'";
		final String condNombreComa = condNombre.isEmpty() ? "" : ",";
		final String condLugar = critLugar.equals("nulo") ? "" : "lugar = '" + critLugar + "'";
		final String condLugarComa = condLugar.isEmpty() ? "" : ",";
		final String condDistancia = critDistancia == 0 ? "" : "distancia = " + critDistancia;
		final String condDistanciaComa = condDistancia.isEmpty() ? "" : ",";
		final String condNivel = critNivel.equals("nulo") ? "" : "nivel = '" + critNivel + "'";
		final String condNivelComa = condNivel.isEmpty() ? "" : ",";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			if (condNombre.isEmpty() && condLugar.isEmpty() && condDistancia.isEmpty() && condNivel.isEmpty()) {
				rs = stmt.executeQuery("SELECT * FROM opinion");
			} else {
				final String query = "SELECT * FROM opinion "
						+ "WHERE " + condNombre + condNombreComa + condLugar + condLugarComa + 
						condDistancia + condDistanciaComa + condNivel + condNivelComa;
				rs = stmt.executeQuery(query.substring(0, query.length() - 1));
			}
			
			while (rs.next()) {
				Excursion e = new Excursion();
				e.setIdExcursion(rs.getInt("idexcursion"));
				e.setNombre(rs.getString("nombre"));
				e.setNivel(rs.getString("nivel"));
				e.setLugar(rs.getString("lugar"));
				e.setDistancia(rs.getDouble("distancia"));
				e.setFoto(rs.getString("imgpath"));
				e.setLatitud(rs.getFloat("latitud"));
				e.setLongitud(rs.getFloat("longitud"));
				log.info("Excursion " + e.getNombre() + " añadida al arrayList.");
				ale.add(e);
			}
			return ale;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerExcursionesPorCriterio: " + e.getMessage());
			return null;
		}
	}
	
	public void insertarExcursion(Excursion e) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO excursion(nombre,nivel,lugar,"
					+ "distancia,imgpath,latitud,longitud) "
					+ "VALUES('" + e.getNombre() + "','" + e.getNivel() + "','"
					+ e.getLugar() + "'," + e.getDistancia() +
					",'" + e.getFoto() + "'," + e.getLatitud()
					+ "," + e.getLongitud() + ")");
			log.info("Excursion insertada correctamente en la base de datos.");
		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warning("ERROR insertarExcursion: " + exc.getMessage());
		}
	}
	
	public void eliminarExcursion(int id) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM excursion WHERE idexcursion = " + id);
			log.info("La excursion con id '" + id + "' fue eliminada correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR eliminarExcursion: " + e.getMessage());
		}
	}
	
	// OPINIONES
	public Opinion obtenerOpinionPorId(int id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM opinion WHERE idopinion = " + id);
			if (rs.next()) {
				Opinion o = new Opinion();
				o.setIdOpinion(rs.getInt("idopinion"));
				o.setIdUsuario(rs.getString("idusuario"));
				o.setIdExcursion(rs.getInt("idexcursion"));
				o.setOpinion(rs.getString("opinion"));
				o.setFoto(rs.getString("imgpath"));
				log.info("Opinion: " + o.getIdOpinion());
				return o;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerOpinionPorId: " + e.getMessage());
			return null;
		}
	}
	
	public List<Opinion> obtenerOpinionesUsuario(String idusuario) {
		List<Opinion> alo = new ArrayList<Opinion>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM opinion WHERE idusuario = '" + idusuario + "'");
			while (rs.next()) {
				Opinion o = new Opinion();
				o.setIdOpinion(rs.getInt("idopinion"));
				o.setIdUsuario(rs.getString("idusuario"));
				o.setIdExcursion(rs.getInt("idexcursion"));
				o.setOpinion(rs.getString("opinion"));
				o.setFoto(rs.getString("imgpath"));
				log.info("Opinion '" + o.getIdOpinion() + "' añadida al arrayList.");
				alo.add(o);
			}
			return alo;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerOpinionesUsuario: " + e.getMessage());
			return null;
		}
	}
	
	public List<Opinion> obtenerOpinionesExcursion(int idexcursion) {
		List<Opinion> alo = new ArrayList<Opinion>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM opinion WHERE idexcursion = " + idexcursion);
			while (rs.next()) {
				Opinion o = new Opinion();
				o.setIdOpinion(rs.getInt("idopinion"));
				o.setIdUsuario(rs.getString("idusuario"));
				o.setIdExcursion(rs.getInt("idexcursion"));
				o.setOpinion(rs.getString("opinion"));
				o.setFoto(rs.getString("imgpath"));
				log.info("Opinion '" + o.getIdOpinion() + "' añadida al arrayList.");
				alo.add(o);
			}
			return alo;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerOpinionesExcursion: " + e.getMessage());
			return null;
		}
	}
	
	public void insertarOpinion(Opinion o) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO opinion(idusuario,idexcursion,opinion,imgpath) "
					+ "VALUES('" + o.getIdUsuario() + "'," + o.getIdExcursion() + ",'"
					+ o.getOpinion() + "','" + o.getFoto() +"')");
			log.info("Opinion insertada correctamente en la base de datos.");
		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warning("ERROR insertarOpinion: " + exc.getMessage());
		}
	}
	
	public void editarOpinion(Opinion o) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE opinion SET idusuario = '" + o.getIdUsuario() + "',"
					+ " idexcursion = " + o.getIdExcursion() + ","
					+ " opinion = '" + o.getOpinion() + "',"
					+ " imgpath = '" + o.getFoto() + "'" 
					+ " WHERE idopinion = " + o.getIdOpinion());
			log.info("Opinion '" + o.getIdOpinion() + "' editada correctamente.");
		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warning("ERROR editarOpinion: " + exc.getMessage());
		}
	}
	
	public void eliminarOpinion(int id) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM opinion WHERE idopinion = " + id);
			log.info("La opinion con id '" + id + "' fue eliminada correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR eliminarOpinion: " + e.getMessage());
		}
	}
}
