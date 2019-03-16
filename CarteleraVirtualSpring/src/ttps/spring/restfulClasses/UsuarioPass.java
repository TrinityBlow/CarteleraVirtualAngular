package ttps.spring.restfulClasses;

import java.io.Serializable;


public class UsuarioPass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String email, pass;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	
}
