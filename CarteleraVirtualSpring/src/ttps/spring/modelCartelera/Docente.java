package ttps.spring.modelCartelera;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Docente extends Usuario implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public String toString() {
		 return super.toString()
				 + "\n" + "Docente";
	 }
	 
	@Override
	public String getRol() {
		return "docente";
	}

}
