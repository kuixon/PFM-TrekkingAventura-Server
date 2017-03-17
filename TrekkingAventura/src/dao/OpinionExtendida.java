package dao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpinionExtendida {
	
	private int idOpinion;
	private Usuario usuario;
	private Excursion excursion;
	private String opinion;
	private String imgPath;
	
	public OpinionExtendida() {
		
	}

	public OpinionExtendida(int idOpinionExtendida, Usuario usuario, Excursion excursion, String opinion,
			String imgPath) {
		super();
		this.idOpinion = idOpinionExtendida;
		this.usuario = usuario;
		this.excursion = excursion;
		this.opinion = opinion;
		this.imgPath = imgPath;
	}

	public int getIdOpinion() {
		return idOpinion;
	}

	public void setIdOpinion(int idOpinion) {
		this.idOpinion = idOpinion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Excursion getExcursion() {
		return excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "OpinionExtendida [idOpinionExtendida=" + idOpinion + ", usuario=" + usuario + ", excursion="
				+ excursion + ", opinion=" + opinion + ", imgPath=" + imgPath + "]";
	}
}
