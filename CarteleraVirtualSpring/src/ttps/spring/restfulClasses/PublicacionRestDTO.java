package ttps.spring.restfulClasses;

import java.io.Serializable;

public class PublicacionRestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private String nota;
	private Integer comHabilitado;
	private String imagen;
	private Long creador;
	private Long cartelera;
	
	public PublicacionRestDTO() {
		
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	

	public Long getCreador() {
		return creador;
	}

	public void setCreador(Long creador) {
		this.creador = creador;
	}

	public Long getCartelera() {
		return cartelera;
	}

	public void setCartelera(Long cartelera) {
		this.cartelera = cartelera;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getComHabilitado() {
		return comHabilitado;
	}

	public void setComHabilitado(Integer comHabilitado) {
		this.comHabilitado = comHabilitado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	
}
