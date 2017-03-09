package utilities;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import dao.Excursion;
import dao.Opinion;
import dao.Usuario;

public class ClientManager {
	
	public static ClientManager instance;
	
	public static int SUCCESS_CODE = 201;
	public static int EDIT_SUCCESS_CODE = 204;
	public static int ERROR_CODE = 205;
	public static int ALREADY_EXIST_CODE = 206;
	
	private static String URL = "http://www.trekkingaventura-160709.appspot.com/";
	
	private DefaultClientConfig		config;
	private Client					client;
	private WebResource				service;
	
	public ClientManager() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(UriBuilder.fromUri(URL).build());
	}
	
	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}
	
	// USUARIOS
	public Usuario obtenerUsuarioPorId(String id) {
		return service.path("rest").path("usuarios").path(id).get(Usuario.class);
	}
	
	public boolean insertarUsuario(Usuario u) {
		ClientResponse response = service.path("rest").path("usuarios").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, u);
		return response.getStatus() == SUCCESS_CODE ? true : false;
	}
	
	// EXCURSIONES
	public Excursion obtenerExcursionPorId(int id) {
		return service.path("rest").path("excursiones").path("excursion").path(Integer.toString(id)).get(Excursion.class);
	}
	
	public Excursion obtenerExcursionPorNombre(String nombre) {
		return service.path("rest").path("excursiones").path("nombre").path(nombre).get(Excursion.class);
	}
	
	public ArrayList<Excursion> getExcursionesPorCriterio(String criterio) {
		Excursion[] array = service.path("rest").path("excursiones").path("criterio").path(criterio).get(Excursion[].class);
		return new ArrayList<Excursion>(Arrays.asList(array));
	}
	
	public boolean insertarExcursion(Excursion e) {
		ClientResponse response = service.path("rest").path("excursiones").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, e);
		return response.getStatus() == SUCCESS_CODE ? true : false;
	}
	
	public void eliminarExcursion(int id) {
		service.path("rest").path("excursiones").path("excursion").path(Integer.toString(id)).delete();
	}
	
	// OPINIONES
	public ArrayList<Opinion> obtenerOpinionesUsuario(String idusuario) {
		Opinion[] array = service.path("rest").path("opiniones").path("usuario").path(idusuario).get(Opinion[].class);
		return new ArrayList<Opinion>(Arrays.asList(array));
	}
	
	public ArrayList<Opinion> obtenerOpinionesExcursion(int idexcursion) {
		Opinion[] array = service.path("rest").path("opiniones").path("excursion").path(Integer.toString(idexcursion)).get(Opinion[].class);
		return new ArrayList<Opinion>(Arrays.asList(array));
	}
	
	public boolean insertarOpinion(Opinion o) {
		ClientResponse response = service.path("rest").path("opiniones").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, o);
		return response.getStatus() == SUCCESS_CODE ? true : false;
	}
	
	public boolean editarOpinion(Opinion o) {
		ClientResponse response = service.path("rest").path("opiniones").path("opinion").path(Integer.toString(o.getIdOpinion()))
				.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, o);
		return response.getStatus() == EDIT_SUCCESS_CODE ? true : false;
	}
	
	public void eliminarOpinion(int id) {
		service.path("rest").path("opiniones").path("opinion").path(Integer.toString(id)).delete();
	}
}
