package ttps.spring.modelCartelera;

import java.io.Serializable;
import java.util.Calendar;
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
@Table(name="comentario")
public class Comentario extends Activable implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comentario")
	 private Long id;	
	 private String comentario;	
	 private String respuesta;	
	 private Date fechaComentario;	
	 private Date fechaRespuesta;	
	 private int activo = 1;	

	 @ManyToOne(optional = true)
	 @JoinColumn(name="id_alumno")
	 private Alumno alumno;
	 @ManyToOne(optional = true)
	 @JoinColumn(name="id_publicacion")
	 private Publicacion publicacion;
	 @ManyToOne(optional = true)
	 @JoinColumn(name="id_respuesta")
	 private Usuario respondio;


	 public String toString() {
		 return datos()
				 + "\n" + alumno
				 + "\n" + publicacion
				 + "\n" + respondio;
	 }
	 
	 public String datos() {
		 return "Comentario: " + id + "||" + comentario + "||" + respuesta + "||" + fechaComentario + "||" + fechaRespuesta + "||" + activo;
	 }

	public void responder(Usuario respondio, String respuesta) {
		setRespondio(respondio);
		setRespuesta(respuesta);
	}
	
	public void preguntar(Alumno preguntando, String pregunta) {
		this.setAlumno(preguntando);
		this.setComentario(pregunta);
	}
 
	 
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
		this.fechaComentario = Calendar.getInstance().getTime();
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
		this.fechaRespuesta = Calendar.getInstance().getTime();
	}

	public Date getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Usuario getRespondio() {
		return respondio;
	}

	public void setRespondio(Usuario respondio) {
		this.respondio = respondio;
	}

	
	 
	 
	 
	 
}
