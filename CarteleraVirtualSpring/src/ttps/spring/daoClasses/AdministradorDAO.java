package ttps.spring.daoClasses;

import ttps.spring.modelCartelera.Administrador;

public interface AdministradorDAO extends GenericDAO<Administrador> {

	public Administrador recuperarUsuario(String email);
	public boolean existeUsuario(String email);
	public Administrador borrarLogico(String email);

	
}
