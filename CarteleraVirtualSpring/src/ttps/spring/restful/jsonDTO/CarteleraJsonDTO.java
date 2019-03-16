package ttps.spring.restful.jsonDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.spring.modelCartelera.Alumno;

public class CarteleraJsonDTO implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private Long id;	
	 private String titulo;	
	 private Date fecha;	
	 
	 private AdministradorJsonDTO creador;
	 private CategoriaJsonDTO categoria;

	 private List<PublicacionJsonDTO> publicaciones = new ArrayList<PublicacionJsonDTO>();
	 
	 @JsonIgnore
	 private List<Alumno> alumnos = new ArrayList<Alumno>();
	 @JsonIgnore
	 private List<UsuarioJsonDTO> miembros = new ArrayList<UsuarioJsonDTO>();
	 
	 
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	public AdministradorJsonDTO getCreador() {
		return creador;
	}
	public void setCreador(AdministradorJsonDTO creador) {
		this.creador = creador;
	}
	public List<UsuarioJsonDTO> getMiembros() {
		return miembros;
	}
	public void setMiembros(List<UsuarioJsonDTO> miembros) {
		this.miembros = miembros;
	}
	public CategoriaJsonDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaJsonDTO categoria) {
		this.categoria = categoria;
	}
	public List<PublicacionJsonDTO> getPublicaciones() {
		return publicaciones;
	}
	public void setPublicaciones(List<PublicacionJsonDTO> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public void addPublicacionDTO(PublicacionJsonDTO publicacionDTO) {
		publicaciones.add(publicacionDTO);
	}
	 
	 
	
}
