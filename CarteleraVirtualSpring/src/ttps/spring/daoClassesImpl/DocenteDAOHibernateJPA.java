package ttps.spring.daoClassesImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.DocenteDAO;
import ttps.spring.modelCartelera.Docente;


@Repository("docenteDAO")
public class DocenteDAOHibernateJPA extends GenericDAOHibernateJPA<Docente> implements DocenteDAO {

	public DocenteDAOHibernateJPA() {
		super(Docente.class);
	}
	
	/** esté método es a modo de ejemplo, se agregaría para cosas
	* particulares de la entidad Persona
	**/
	public Docente recuperarUsuario(String email) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Usuario p where p.email =? AND p.activo =?");
		consulta.setParameter(1, email);
		consulta.setParameter(2, 1);
		Docente resultado;
		try {
			resultado = (Docente)consulta.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	public boolean existeUsuario(String email)  {
		Docente entity = null;
		entity = recuperarUsuario(email);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	public Docente borrarLogico(String email) {
		Docente entity = recuperarUsuario(email);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}




}
