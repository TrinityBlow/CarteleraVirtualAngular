package ttps.spring.restful.jsonDTO;

public class ComentarioJsonDTO {

	private String comentario;
	private String username;
	private long publicacion;
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(long publicacion) {
		this.publicacion = publicacion;
	}
    
    
}
