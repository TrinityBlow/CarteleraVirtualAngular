package ttps.spring.restfulClasses;

public class ComentarioDeRespuestaRest {

    private String respuesta;
    private long comentarioId;
    private String usuarioEmail;
    
    
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public long getComentarioId() {
		return comentarioId;
	}
	public void setComentarioId(long comentarioId) {
		this.comentarioId = comentarioId;
	}
	public String getUsuarioEmail() {
		return usuarioEmail;
	}
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
    
	public boolean notNull() {
		return (this.getRespuesta() != null) && (this.getComentarioId() > 0) && (this.getUsuarioEmail() != null);
	}
    
}
