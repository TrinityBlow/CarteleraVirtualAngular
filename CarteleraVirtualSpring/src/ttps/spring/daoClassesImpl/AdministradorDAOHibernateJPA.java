package ttps.spring.daoClassesImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.AdministradorDAO;
import ttps.spring.modelCartelera.Administrador;


@Repository("administradorDAO")
public class AdministradorDAOHibernateJPA extends GenericDAOHibernateJPA<Administrador> implements AdministradorDAO {

	public AdministradorDAOHibernateJPA() {
		super(Administrador.class);
	}
	
	/** esté método es a modo de ejemplo, se agregaría para cosas
	* particulares de la entidad Persona
	**/
	public Administrador recuperarUsuario(String email) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Usuario p where p.email = :email AND p.activo = :activo");
		consulta.setParameter("email", email);
		consulta.setParameter("activo", 1);
		Administrador resultado;
		try {
			resultado = (Administrador)consulta.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	public boolean existeUsuario(String email)  {
		Administrador entity = null;
		entity = recuperarUsuario(email);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	public Administrador borrarLogico(String email) {
		Administrador entity = recuperarUsuario(email);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}




}
