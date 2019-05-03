package ttps.spring.daoClasses;

import java.io.Serializable;
import java.util.List;

import ttps.spring.modelCartelera.Cartelera;

public interface CarteleraDAO extends GenericDAO<Cartelera> {

	public Cartelera recuperarCartelera(String titulo);
	public boolean existeCartelera(String titulo);
	public Cartelera borrarLogico(String titulo);
	public Cartelera recuperarJoin(Serializable id);
	public List<Cartelera> recuperarTodosJoin();
	public Cartelera actualizarJoin(Cartelera cartelera);

}
