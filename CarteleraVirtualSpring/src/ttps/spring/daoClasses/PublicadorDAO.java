package ttps.spring.daoClasses;

import ttps.spring.modelCartelera.Publicador;

public interface PublicadorDAO extends GenericDAO<Publicador> {

	public Publicador recuperarUsuario(String email);
	public boolean existeUsuario(String email);
	public Publicador borrarLogico(String email);
	
}
