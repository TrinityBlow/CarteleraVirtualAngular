package ttps.spring.modelCartelera;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="test")
public class Test extends Activable implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_test")
	 private Long id;	
	 private String nom;	
	 private int activo = 1;	
	 
	
	 
	 public String toString() {
		 return  datos();
 	 }
	 
	 public String datos() {
		 return "Cartelera: " + id + "||" + nom + "||" + "||" + activo;
	 }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getActivo() {
		return activo;
	}


	public void setActivo(int activo) {
		this.activo = activo;
	} 
	
	
	
	 
}
