package dao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Excursion {
	
	private int idExcursion;
    private String nombre;
    private String nivel;
    private String lugar;
    private double distancia;
    private String foto;
    private float latitud;
    private float longitud;
	
    public Excursion() {
		
	}

	public Excursion(int idExcursion, String nombre, String nivel, String lugar, double distancia, String foto, float latitud,
			float longitud) {
		this.idExcursion = idExcursion;
		this.nombre = nombre;
		this.nivel = nivel;
		this.lugar = lugar;
		this.distancia = distancia;
		this.foto = foto;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public int getIdExcursion() {
		return idExcursion;
	}

	public void setIdExcursion(int idExcursion) {
		this.idExcursion = idExcursion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "Excursion [idExcursion=" + idExcursion + ", nombre=" + nombre + ", nivel=" + nivel + ", lugar=" + lugar
				+ ", distancia=" + distancia + ", foto=" + foto + ", latitud=" + latitud + ", longitud=" + longitud
				+ "]";
	}
}
