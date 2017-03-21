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
import dao.ExcursionDestacada;
import dao.Opinion;
import dao.OpinionExtendida;
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
	
	public Usuario insertarUsuario(String idUsuario) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO usuario VALUES('" + idUsuario + "')");
			log.info("Usuario insertado correctamente en la base de datos.");
			return new Usuario(idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR insertarUsuario: " + e.getMessage());
		}
		return null;
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
	
	public List<ExcursionDestacada> getExcursionesDestacadas() {
		List<ExcursionDestacada> aled = new ArrayList<ExcursionDestacada>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT e.idexcursion, e.nombre, e.nivel, e.lugar, e.distancia, e.imgpath, e.latitud, e.longitud, count(o.idexcursion) "
							+ "FROM excursion e, opinion o "
							+ "WHERE e.idexcursion = o.idexcursion "
							+ "GROUP BY o.idexcursion");
			while (rs.next()) {
				ExcursionDestacada ed = new ExcursionDestacada();
				ed.setIdExcursion(rs.getInt(1));
				ed.setNombre(rs.getString(2));
				ed.setNivel(rs.getString(3));
				ed.setLugar(rs.getString(4));
				ed.setDistancia(rs.getDouble(5));
				ed.setFoto(rs.getString(6));
				ed.setLatitud(rs.getFloat(7));
				ed.setLongitud(rs.getFloat(8));
				ed.setNumOpiniones(rs.getInt(9));
				
				log.info("ExcursionDestacada '" + ed.getIdExcursion() + "' añadida al arrayList.");
				aled.add(ed);
			}
			return aled;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR getExcursionesDestacadas: " + e.getMessage());
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
	
	public List<Excursion> obtenerExcursionesPorCriterio(String nombre, String lugar, 
			String distancia, String nivel) {
		
		List<Excursion> ale = new ArrayList<Excursion>();
		
		final String condNombre = nombre.equals("nulo") ? "" : " nombre = '" + nombre + "'";
		final String condNombreComa = condNombre.isEmpty() ? "" : " AND";
		final String condLugar = lugar.equals("nulo") ? "" : " lugar = '" + lugar + "'";
		final String condLugarComa = condLugar.isEmpty() ? "" : " AND";
		final String condDistancia = distancia.equals("nulo") ? "" : " distancia = " + distancia;
		final String condDistanciaComa = condDistancia.isEmpty() ? "" : " AND";
		final String condNivel = nivel.equals("nulo") ? "" : " nivel = '" + nivel + "'";
		final String condNivelComa = condNivel.isEmpty() ? "" : " AND";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			if (condNombre.isEmpty() && condLugar.isEmpty() && condDistancia.isEmpty() && condNivel.isEmpty()) {
				rs = stmt.executeQuery("SELECT * FROM excursion");
			} else {
				final String query = "SELECT * FROM excursion "
						+ "WHERE" + condNombre + condNombreComa + condLugar + condLugarComa + 
						condDistancia + condDistanciaComa + condNivel + condNivelComa;
				rs = stmt.executeQuery(query.substring(0, query.length() - 3));
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
	
	public Excursion insertarExcursion(String nombre, String nivel, String lugar, double distancia, 
			String imgpath, float latitud, float longitud) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO excursion(nombre,nivel,lugar,"
					+ "distancia,imgpath,latitud,longitud) "
					+ "VALUES('" + nombre + "','" + nivel + "','"
					+ lugar + "'," + distancia +
					",'" + imgpath + "'," + latitud
					+ "," + longitud + ")");
			log.info("Excursion insertada correctamente en la base de datos.");
			return new Excursion(0, nombre, nivel, lugar, distancia, imgpath, latitud, longitud);
		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warning("ERROR insertarExcursion: " + exc.getMessage());
		}
		return null;
	}
	
	public Excursion eliminarExcursion(int id) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM excursion WHERE idexcursion = " + id);
			log.info("La excursion con id '" + id + "' fue eliminada correctamente.");
			return new Excursion(id, "", "", "", 0, "", 0, 0);
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR eliminarExcursion: " + e.getMessage());
		}
		return null;
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
	
	public List<OpinionExtendida> obtenerOpinionesUsuario(String idusuario) {
		List<OpinionExtendida> aloe = new ArrayList<OpinionExtendida>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT o.idopinion, o.opinion, o.imgpath, u.idusuario, e.idexcursion, "
			+"e.nombre, e.nivel, e.lugar, e.distancia, e.imgpath, e.latitud, e.longitud "
			+"FROM opinion o, usuario u, excursion e "
			+"WHERE o.idusuario = u.idusuario AND o.idexcursion = e.idexcursion AND o.idusuario = '" + idusuario + "'");
			while (rs.next()) {
				OpinionExtendida oe = new OpinionExtendida();
				oe.setIdOpinion(rs.getInt(1));
				oe.setOpinion(rs.getString(2));
				oe.setImgPath(rs.getString(3));
				oe.setUsuario(new Usuario(rs.getString(4)));
				oe.setExcursion(new Excursion(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getDouble(9), rs.getString(10), rs.getFloat(11), rs.getFloat(12)));
				
				log.info("OpinionExtendida '" + oe.getIdOpinion() + "' añadida al arrayList.");
				aloe.add(oe);
			}
			return aloe;
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
	
	public Opinion insertarOpinion(String idusuario, int idexcursion, String opinion, String imgpath) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO opinion(idusuario,idexcursion,opinion,imgpath) "
					+ "VALUES('" + idusuario + "'," + idexcursion + ",'"
					+ opinion + "','" + imgpath +"')");
			log.info("Opinion insertada correctamente en la base de datos.");
			return new Opinion(0, idusuario, idexcursion, opinion, imgpath);
		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warning("ERROR insertarOpinion: " + exc.getMessage());
		}
		return null;
	}
	
	public Opinion editarOpinion(int idopinion, String idusuario, int idexcursion, String opinion, 
			String imgpath) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE opinion SET idusuario = '" + idusuario + "',"
					+ " idexcursion = " + idexcursion + ","
					+ " opinion = '" + opinion + "',"
					+ " imgpath = '" + imgpath + "'" 
					+ " WHERE idopinion = " + idopinion);
			log.info("Opinion '" + idopinion + "' editada correctamente.");
			return new Opinion(idopinion, idusuario, idexcursion, opinion, imgpath);
		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warning("ERROR editarOpinion: " + exc.getMessage());
		}
		return null;
	}
	
	public Opinion eliminarOpinion(int id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM opinion "
					+ "WHERE idexcursion = (SELECT idexcursion FROM opinion WHERE idopinion = " + id + ")");
			
			boolean deleteExcursion = false;
			int idDeleteExcursion = 0;
			int rowcount = 0;
			if (rs.last()) {
			  rowcount = rs.getRow();
			  if (rowcount == 1) {
				  deleteExcursion = true;
			  }
			  rs.beforeFirst();
			}
			
			if (rs.next() && deleteExcursion) {
			  idDeleteExcursion = rs.getInt("idexcursion");
			}
			
			Statement stmt2 = con.createStatement();
			stmt2.executeUpdate("DELETE FROM opinion WHERE idopinion = " + id);
			
			if (deleteExcursion) {
				Statement stmt3 = con.createStatement();
				stmt3.executeUpdate("DELETE FROM excursion WHERE idexcursion = " + idDeleteExcursion);
			}
			
			return new Opinion(id, "", 0, "", "");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR eliminarOpinion: " + e.getMessage());
		}
		return null;
	}
}
