package ttps.spring.modelCartelera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="administrador")
public class Administrador extends Usuario implements Serializable  {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		 
	@OneToMany(mappedBy="creador", orphanRemoval=true)
	@JsonIgnore
	private List<Cartelera> creadas = new ArrayList<Cartelera>();

	 public String toString() {
		 return super.toString()
				 + "\n" + "Administrador";
	 }
	

	public List<Cartelera> getCreadas() {
		return creadas;
	}

	public void setCreadas(List<Cartelera> creadas) {
		this.creadas = creadas;
	}
	
	@Override
	public String getRol() {
		return "admin";
	}
	
	 
}
