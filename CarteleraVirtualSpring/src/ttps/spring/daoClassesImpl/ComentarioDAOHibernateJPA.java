package ttps.spring.daoClassesImpl;

import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.ComentarioDAO;
import ttps.spring.modelCartelera.Comentario;

@Repository("comentarioDAO")
public class ComentarioDAOHibernateJPA extends GenericDAOHibernateJPA<Comentario> implements ComentarioDAO {

	public ComentarioDAOHibernateJPA() {
		super(Comentario.class);
	}

}
