package ttps.spring.daoClassesImpl;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.daoClasses.CarteleraDAO;
import ttps.spring.modelCartelera.Cartelera;


@Repository("carteleraDAO")
@Transactional
public class CarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<Cartelera> implements CarteleraDAO {

	public CarteleraDAOHibernateJPA() {
		super(Cartelera.class);
	}

	public Cartelera recuperarCartelera(String nombre) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Cartelera p where p.nombre =? AND p.activo =?");
		consulta.setParameter(1, nombre);
		consulta.setParameter(2, 1);
		Cartelera resultado;
		try {
			resultado = (Cartelera)consulta.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	public boolean existeCartelera(String nombre) {
		Cartelera entity = null;
		entity = recuperarCartelera(nombre);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	public Cartelera borrarLogico(String nombre) {
		Cartelera entity = recuperarCartelera(nombre);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}

	public Cartelera recuperarJoin(Serializable id) {
		Cartelera cartelera = super.recuperar(id);
		if(cartelera != null) {
			Hibernate.initialize(cartelera.getPublicaciones());
		}
		return cartelera;
	}

	@Override
	public List<Cartelera> recuperarTodosJoin() {
		List<Cartelera> carteleras = super.recuperarTodos();
		for(Cartelera cartelera: carteleras) {
			Hibernate.initialize(cartelera.getPublicaciones());
		}
		return carteleras;
	}
	

	
}
