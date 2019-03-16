package ttps.spring.daoClassesImpl;


import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.daoClasses.TestDAO;
import ttps.spring.modelCartelera.Test;



@Repository("testDAO")
@Transactional
public class TestDAOHibernateJPA extends GenericDAOHibernateJPA<Test> implements TestDAO {

	public TestDAOHibernateJPA() {
		super(Test.class);
	}

	public Test recuperarTest(String nom) {
		Query consulta = this.getEntityManager().createQuery("select p from Test p where p.nom =? AND p.activo =?");
		consulta.setParameter(1, nom);
		consulta.setParameter(2, 1);
		Test resultado;
		try {
			resultado = (Test)consulta.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	public boolean existeTest(String nom) {
		Test entity = null;
		entity = recuperarTest(nom);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	public Test borrarLogico(String nom) {
		Test entity = recuperarTest(nom);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}

}
