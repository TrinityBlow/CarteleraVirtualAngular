package ttps.spring.daoClasses;

import java.io.Serializable;
import java.util.List;

import ttps.spring.modelCartelera.Activable;



public interface GenericDAO <T extends Activable> {
	
	public T actualizar(T entity);
	public void borrar(T entity);
	public T borrar(Serializable id);
	public boolean existe(Serializable id);
	public T persistir(T entity);
	public T recuperar(Serializable id);
	public List<T> recuperarTodos();
	public void borrarLogico(T entity);
	public T borrarLogico(Serializable id);

	
}
