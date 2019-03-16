package ttps.spring.daoClassesImpl;


import org.springframework.stereotype.Repository;

import ttps.spring.daoClasses.PublicacionDAO;
import ttps.spring.modelCartelera.Publicacion;

@Repository("publicacionDAO")
public class PublicacionDAOHibernateJPA extends GenericDAOHibernateJPA<Publicacion> implements PublicacionDAO {

	public PublicacionDAOHibernateJPA() {
		super(Publicacion.class);
	}

}
