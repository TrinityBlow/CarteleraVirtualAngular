package ttps.spring.daoClasses;

import ttps.spring.modelCartelera.Alumno;

public interface AlumnoDAO extends GenericDAO<Alumno> {

	public Alumno recuperarUsuario(String email);
	public boolean existeUsuario(String email);
	public Alumno borrarLogico(String email);

	
}
