package ttps.spring.daoClassesImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import ttps.spring.daoClasses.GenericDAO;
import ttps.spring.modelCartelera.Activable;


@Transactional
public class GenericDAOHibernateJPA<T extends Activable> implements GenericDAO<T> {

	 private EntityManager entityManager;
	 
	 @PersistenceContext
	 public void setEntityManager(EntityManager em){
		 this.entityManager = em;
	 }
	 public EntityManager getEntityManager() {
		 return entityManager;
	 }

	
	protected Class<T> persistentClass;
	
	public GenericDAOHibernateJPA(Class<T> classToken) {
		super();
		persistentClass = classToken;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public T persistir(T entity) {
		this.getEntityManager().persist(entity);
		return entity;
	} 
	
	public T actualizar(T entity) {
		T entityUpdate = this.getEntityManager().merge(entity);
		return entityUpdate;
	} 
	

	public void borrar(T entity) {
		this.getEntityManager().remove(entity);
	}

	
	public T borrar(Serializable id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		return entity;
	} 
	

	@SuppressWarnings("unchecked")
	public List<T> recuperarTodos(String columnOrder) {
		Query consulta= this.getEntityManager().createQuery("select e from " + getPersistentClass().getSimpleName()+" e order bye."+columnOrder);
		List<T> resultado = (List<T>)consulta.getResultList();
		List<T> resultadoActivable = new ArrayList<T>();
		for(T activable : resultado) {
			if(activable.getActivo() == 1) {
				resultadoActivable.add(activable);
			}
		}
		return resultadoActivable;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<T> recuperarTodos() {
		Query consulta= this.getEntityManager().createQuery("select e from " + getPersistentClass().getSimpleName() + " e");
		
		List<T> resultado = (List<T>)consulta.getResultList();
		List<T> resultadoActivable = new ArrayList<T>();
		for(T activable : resultado) {
			if(activable.getActivo() == 1) {
				resultadoActivable.add(activable);
			}
		}
		return resultadoActivable;
	}

	public boolean existe(Serializable id)  {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null) {
			return true;
		}
			return false;
	}

	public T recuperar(Serializable id) {

		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null) {
			if(entity.getActivo() == 1) {
				return entity;
			}
		}
			return null;
	}

	public void borrarLogico(T entity) {
		entity.setActivo(0);
		actualizar(entity);
	}
	
	public T borrarLogico(Serializable id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null && (entity.getActivo() == 1)) {
			this.borrarLogico(entity);
			return entity;
		}
		return null;
	} 
	
}
