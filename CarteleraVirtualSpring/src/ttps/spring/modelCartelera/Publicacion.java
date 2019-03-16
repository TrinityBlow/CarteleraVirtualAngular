package ttps.spring.modelCartelera;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="publicacion")
public class Publicacion extends Activable implements Serializable {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_publicacion")
	 private Long id;	
	 private String titulo;	
	 private String nota;	
	 private int comHabilitado;	
	 private Date fecha;	
	 private String imagen;	
	 private int activo = 1;	

	 @ManyToOne(optional = true)
	 @JoinColumn(name="id_usuario")
	 private Usuario creador;	
	 @ManyToOne(optional = true)
	 @JoinColumn(name="id_cartelera")
	 private Cartelera cartelera;	

	 
	public String toString() {
		return datos() 
				+ "\n" + creador.datos() 
				+ "\n" + cartelera.datos();
	}
	
	public String datos() {
		return "Publicacion: " + id + "||" + titulo + "||" + nota + "||" + comHabilitado + "||" + fecha + "||" + imagen + "||" + activo;
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


	public int getActivo() {
		return activo;
	}


	public void setActivo(int activo) {
		this.activo = activo;
	}


	public Usuario getCreador() {
		return creador;
	}


	public void setCreador(Usuario usuario) {
		this.creador = usuario;
	}


	public Cartelera getCartelera() {
		return cartelera;
	}


	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}
	
	
	 
	
}
