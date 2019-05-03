package ttps.spring.restful.jsonDTO;

public class PublicacionJsonCrear {

	private String titulo;
	private String creador;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCreador() {
		return creador;
	}
	public void setCreador(String creador) {
		this.creador = creador;
	}
	public long getCarteleraId() {
		return carteleraId;
	}
	public void setCarteleraId(long carteleraId) {
		this.carteleraId = carteleraId;
	}
	private long carteleraId;
	
}
