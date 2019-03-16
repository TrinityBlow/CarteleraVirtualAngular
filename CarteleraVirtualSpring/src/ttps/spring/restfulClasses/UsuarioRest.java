package ttps.spring.restfulClasses;

import java.io.Serializable;


/**
 * @author Rats
 *
 */
public class  UsuarioRest  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	 private String nombre;
	 private String pass;
	 private String apellido;
	 private String email;
	 private Integer rol;


	public String toString() {
		return datos();
		
	}
	
	public String datos() {
		return "Usuario: " + nombre + "||" + apellido + "||" + email + "||" + pass;
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

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public boolean notNull() {
		if (this.nombre == null) return false;
		if (this.apellido == null) return false;
		if (this.pass == null) return false;
		if (this.email == null) return false;
		if (this.rol == null) return false;
		return true;
	}
	
	
}