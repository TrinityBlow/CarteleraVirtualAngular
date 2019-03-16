package ttps.spring.modelCartelera;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Publicador extends Usuario implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public String toString() {
		 return super.toString()
				 + "\n" + "Publicador";
	 }
	
	@Override
	public String getRol() {
		return "publicador";
	}
	 
}
