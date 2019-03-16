package ttps.spring.daoClasses;

import ttps.spring.modelCartelera.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario recuperarUsuario(String email);
	public boolean existeUsuario(String email);
	public Usuario borrarLogico(String email);
	public Usuario agregarUnico(Usuario usuario);
	
}
