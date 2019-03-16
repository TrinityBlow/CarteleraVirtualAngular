package ttps.spring.modelCartelera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class  Usuario extends Activable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="id_usuario")
	 private Long id;
	 private String nombre;
	 private String pass;
	 private String apellido;
	 private String email;
	 private int activo = 1;

	 @ManyToMany
	 @JoinTable(name="usuario_cartelera",
	 joinColumns = @JoinColumn(name="id_usuario",referencedColumnName="id_usuario"),
	 inverseJoinColumns=@JoinColumn(name="id_cartelera",referencedColumnName="id_cartelera")
	 )
	 @JsonIgnore
	private List<Cartelera> carteleras = new ArrayList<Cartelera>();

	@OneToMany(mappedBy="respondio", orphanRemoval=true)
	 @JsonIgnore
	private List<Comentario> respuestas = new ArrayList<Comentario>();
	 

	public String toString() {
		return datos();
		
	}
	
	public String datos() {
		return "Usuario: " + id + "||" + nombre + "||" + apellido + "||" + email + "||" + pass + "||" + activo;
	}
	
	 	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public List<Cartelera> getCarteleras() {
		return carteleras;
	}
	public void setCarteleras(List<Cartelera> carteleras) {
		this.carteleras = carteleras;
	}
	
	public String getRol() {
		return "usuario";
	}

	 
}
