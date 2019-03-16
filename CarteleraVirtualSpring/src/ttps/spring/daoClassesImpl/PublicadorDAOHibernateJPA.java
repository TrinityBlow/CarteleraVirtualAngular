package ttps.spring.daoClassesImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.PublicadorDAO;
import ttps.spring.modelCartelera.Publicador;


@Repository("publicadorDAO")
public class PublicadorDAOHibernateJPA extends GenericDAOHibernateJPA<Publicador> implements PublicadorDAO {

	public PublicadorDAOHibernateJPA() {
		super(Publicador.class);
	}
	
	/** esté método es a modo de ejemplo, se agregaría para cosas
	* particulares de la entidad Persona
	**/
	public Publicador recuperarUsuario(String email) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Usuario p where p.email =? AND p.activo =?");
		consulta.setParameter(1, email);
		consulta.setParameter(2, 1);
		Publicador resultado;
		try {
			resultado = (Publicador)consulta.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	public boolean existeUsuario(String email)  {
		Publicador entity = null;
		entity = recuperarUsuario(email);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	public Publicador borrarLogico(String email) {
		Publicador entity = recuperarUsuario(email);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}




}
