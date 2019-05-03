package ttps.spring.restful.jsonDTO;

import java.util.Date;

public class PublicacionJsonDTO {

	 private Long id;	
	 private String titulo;	
	 private String nota;	
	 private int comHabilitado;	
	 private Date fecha;	
	 private String imagen;	
	 

	 private UsuarioJsonDTO creador;	
	 
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public int getComHabilitado() {
		return comHabilitado;
	}
	public void setComHabilitado(int comHabilitado) {
		this.comHabilitado = comHabilitado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public UsuarioJsonDTO getCreador() {
		return creador;
	}
	public void setCreador(UsuarioJsonDTO creador) {
		this.creador = creador;
	}
	 
	 
	 
}
