package ttps.spring.daoClasses;

import ttps.spring.modelCartelera.Docente;

public interface DocenteDAO extends GenericDAO<Docente> {

	public Docente recuperarUsuario(String email);
	public boolean existeUsuario(String email);
	public Docente borrarLogico(String email);
	
}
