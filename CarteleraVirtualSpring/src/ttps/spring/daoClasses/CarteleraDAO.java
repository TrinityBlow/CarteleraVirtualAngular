package ttps.spring.daoClasses;

import java.io.Serializable;
import java.util.List;

import ttps.spring.modelCartelera.Cartelera;

public interface CarteleraDAO extends GenericDAO<Cartelera> {

	public Cartelera recuperarCartelera(String email);
	public boolean existeCartelera(String email);
	public Cartelera borrarLogico(String email);
	public Cartelera recuperarJoin(Serializable id);
	public List<Cartelera> recuperarTodosJoin();

}
