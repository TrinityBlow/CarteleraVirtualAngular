package ttps.spring.modelCartelera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="cartelera")
public class Cartelera extends Activable implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cartelera")
	 private Long id;	
	 private String titulo;	
	 private Date fecha;	
	 private int activo = 1;	
	 
	 @ManyToOne(optional = true)
	 @JoinColumn(name="id_administrador")
	 private Administrador creador;
	 
	 @ManyToMany(mappedBy="fav")
	 @JsonIgnore
	 private List<Alumno> alumnos = new ArrayList<Alumno>();
	 
	 @ManyToMany(mappedBy="carteleras")
	 @JsonIgnore
	 private List<Usuario> miembros = new ArrayList<Usuario>();
	 
	 @OneToMany(mappedBy="cartelera", orphanRemoval=true)
	 @JsonIgnore
	 private List<Publicacion> publicaciones = new ArrayList<Publicacion>();
	

	 @ManyToOne(optional = true)
	 @JoinColumn(name="id_categoria")
	 @JsonIgnore
	 private Categoria categoria;
	 
	 public String toString() {
		 return  datos() 
				 + "\n" + creador.datos()
				 + "\n" + categoria.datos();
 	 }
	 
	 public String datos() {
		 return "Cartelera: " + id + "||" + titulo + "||" + fecha + "||" + activo;
	 }


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


	public Administrador getCreador() {
		return creador;
	}


	public void setCreador(Administrador creador) {
		this.creador = creador;
	}


	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}


	public List<Usuario> getMiembros() {
		return miembros;
	}


	public void setMiembros(List<Usuario> miembros) {
		this.miembros = miembros;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public int getActivo() {
		return activo;
	}


	public void setActivo(int activo) {
		this.activo = activo;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	} 
	
	
	
	
}
