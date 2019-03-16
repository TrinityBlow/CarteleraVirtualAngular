package ttps.spring.model;

/**
 * @author manuel
 */
public class Credentials {

    private String token;
    private int exp;
    private String username;
    private String rol;

    public Credentials(String token, int exp, String username, String rol) {
        this.token = token;
        this.exp = exp;
        this.username = username;
        this.rol = rol;
    }

    public Credentials(String token, int exp, String username) {
        this.token = token;
        this.exp = exp;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
