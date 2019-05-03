package ttps.spring.daoClassesImpl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.modelCartelera.Categoria;
import ttps.spring.modelCartelera.Usuario;


@Repository("usuarioDAO")
public class UsuarioDAOHibernateJPA extends GenericDAOHibernateJPA<Usuario> implements UsuarioDAO {

	public UsuarioDAOHibernateJPA() {
		super(Usuario.class);
	}
	
	/** esté método es a modo de ejemplo, se agregaría para cosas
	* particulares de la entidad Persona
	**/
	@Override
	public Usuario recuperarUsuario(String email) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Usuario p where p.email = :email AND p.activo = :activo");
		consulta.setParameter("email", email);
		consulta.setParameter("activo", 1);
		Usuario resultado;
		try {
			resultado = (Usuario)consulta.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}
	
	@Override
	public Usuario agregarUnico(Usuario usuario) {
		Usuario buscado = this.recuperarUsuario(usuario.getEmail());
		if(buscado == null) {
			this.persistir(usuario);
			return usuario;
		}
		return null;
	}

	@Override
	public boolean existeUsuario(String email)  {
		Usuario entity = null;
		entity = recuperarUsuario(email);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Usuario borrarLogico(String email) {
		Usuario entity = recuperarUsuario(email);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}
	
	public void initializeCartelera(Usuario cartelera) {
		if(cartelera != null) {
			Hibernate.initialize(cartelera.getCarteleras());
		}
	}
	
	public Usuario recuperarUsuarioJoin(String email) {
		Usuario usuario = this.recuperarUsuario(email);
		if(usuario != null) {
			Hibernate.initialize(usuario.getCarteleras());
		}
		return usuario;
	}




}
