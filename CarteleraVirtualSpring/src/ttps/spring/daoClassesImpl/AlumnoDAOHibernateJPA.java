package ttps.spring.daoClassesImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.AlumnoDAO;
import ttps.spring.modelCartelera.Alumno;

@Repository("alumnoDAO")
public class AlumnoDAOHibernateJPA extends GenericDAOHibernateJPA<Alumno> implements AlumnoDAO {

	public AlumnoDAOHibernateJPA() {
		super(Alumno.class);
	}
	
	/** esté método es a modo de ejemplo, se agregaría para cosas
	* particulares de la entidad Persona
	**/
	public Alumno recuperarUsuario(String email) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Usuario p where p.email =? AND p.activo =?");
		consulta.setParameter(1, email);
		consulta.setParameter(2, 1);
		Alumno resultado;
		try {
			resultado = (Alumno)consulta.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	public boolean existeUsuario(String email)  {
		Alumno entity = null;
		entity = recuperarUsuario(email);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	public Alumno borrarLogico(String email) {
		Alumno entity = recuperarUsuario(email);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}




}
