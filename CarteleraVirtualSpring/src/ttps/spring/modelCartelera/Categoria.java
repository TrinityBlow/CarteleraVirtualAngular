package ttps.spring.modelCartelera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ttps.spring.itemsSerializers.CategoriaItemSerializer;

@Entity
@Table(name="categoria")
public class Categoria extends Activable implements Serializable{


	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categoria")
	 private Long id;	
	 private String tipo;	

	 private int activo = 1;
	 
	@OneToMany(mappedBy="categoria", orphanRemoval=true)
	@JsonIgnore
	private List<Cartelera> carteleras = new ArrayList<Cartelera>();
	 
	public String toString() {
		return datos() ;
	}
	
	public String datos() {
		return id + "||" + tipo + "||" + activo;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	
	
}
