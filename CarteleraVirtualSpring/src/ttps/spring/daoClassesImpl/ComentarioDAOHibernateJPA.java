package ttps.spring.daoClassesImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.ComentarioDAO;
import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Comentario;
import ttps.spring.modelCartelera.Publicacion;

@Repository("comentarioDAO")
public class ComentarioDAOHibernateJPA extends GenericDAOHibernateJPA<Comentario> implements ComentarioDAO {

	public ComentarioDAOHibernateJPA() {
		super(Comentario.class);
	}
	
	public Comentario recuperarJoin(Serializable id) {
		Comentario comentario = super.recuperar(id);
		if(comentario != null) {
			Hibernate.initialize(comentario.getAlumno());
			Hibernate.initialize(comentario.getRespondio());
		}
		return comentario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comentario> recuperarDePublicacionJoin(Publicacion publicacion) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Comentario p where p.publicacion = :publicacion AND p.activo = :activo");
		consulta.setParameter("publicacion", publicacion);
		consulta.setParameter("activo", 1);
		List<Comentario> resultado;
		try {
			resultado = (List<Comentario>)consulta.getResultList();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

}
