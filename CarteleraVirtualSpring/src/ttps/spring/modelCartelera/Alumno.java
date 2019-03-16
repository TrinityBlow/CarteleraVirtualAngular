package ttps.spring.modelCartelera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="alumno")
public class Alumno extends Usuario implements Serializable  {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 


	@OneToMany(mappedBy="alumno", orphanRemoval=true)
	@JsonIgnore
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	 @ManyToMany
	 @JoinTable(name="alumno_cartelera",
	 joinColumns = @JoinColumn(name="id_usuario"),
	 inverseJoinColumns=@JoinColumn(name="id_cartelera")
	 )
	@JsonIgnore
	private List<Cartelera> fav = new ArrayList<Cartelera>();

	 public String toString() {
		 return super.toString()
				 + "\n" + "Alumno";
	 }

	public List<Cartelera> getFav() {
		return fav;
	}

	public void setFav(List<Cartelera> fav) {
		this.fav = fav;
	}
	
	@Override
	public String getRol() {
		return "alumno";
	}
	 
}
