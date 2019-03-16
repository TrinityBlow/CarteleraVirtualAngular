package ttps.spring.daoClassesImpl;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.daoClasses.CategoriaDAO;
import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Categoria;

@Repository("categoriaDAO")
@Transactional
public class CategoriaDAOHibernateJPA extends GenericDAOHibernateJPA<Categoria> implements CategoriaDAO {

	public CategoriaDAOHibernateJPA() {
		super(Categoria.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cartelera> recuperarCarteleras(long id) {
		List<Cartelera> carteleras = null;
		Categoria categoria = this.recuperar(id);
		if(categoria != null) {
			Query consulta = this.getEntityManager().createQuery("select p from Cartelera p where p.categoria = :id_categoria AND p.activo = :activo",Cartelera.class);
			consulta.setParameter("id_categoria", categoria);
			consulta.setParameter("activo", 1 );
			try {
				carteleras = (List<Cartelera>)consulta.getResultList();
			} catch (NoResultException e) {
				
			}
		}
		
		
		return carteleras;
	}

	public Categoria recuperarCategoria(String tipo) {
		EntityManager mf = this.getEntityManager();
		Query consulta = mf.createQuery("select p from Categoria p where p.tipo = :tipo AND p.activo = :activo");
		consulta.setParameter("tipo", tipo);
		consulta.setParameter("activo", 1);
		Categoria resultado;
		try {
			resultado = (Categoria)consulta.getSingleResult();
			Hibernate.initialize(resultado.getCarteleras());
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	public boolean existeCategoria(String tipo) {
		Categoria entity = null;
		entity = recuperarCategoria(tipo);
		
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	public Categoria borrarLogico(String tipo) {
		Categoria entity = recuperarCategoria(tipo);
		if (entity != null) {
			this.borrarLogico(entity);
		}
			return entity;
	}

	public List<Categoria> recuperarTodos(){
		List<Categoria>  todos = super.recuperarTodos();
		for(Categoria categoria: todos) {
			Hibernate.initialize(categoria.getCarteleras());
		}
		return todos;
		
	}

	public Categoria recuperarJoin(Serializable id) {
		Categoria categoria = super.recuperar(id);
		if(categoria != null) {
			Hibernate.initialize(categoria.getCarteleras());
		}
		return categoria;
	}
	
//	public Categoria recuperarJoin(Serializable id) {
//		Categoria categoria = null;
//		Query consulta = this.getEntityManager().createQuery("select p from Categoria p LEFT JOIN p.carteleras r"
//											+ " where p.id = :id AND p.activo = :activo",Categoria.class);
//		consulta.setParameter("id", id);
//		consulta.setParameter("activo", 1 );
//		try {
//			categoria = (Categoria)consulta.getSingleResult();
//			categoria.getCarteleras();
//		} catch (NoResultException e) {
//			
//		}
//		return categoria;
//	}



	
}
