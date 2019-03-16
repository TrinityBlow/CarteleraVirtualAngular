package ttps.spring.daoClasses;

import java.io.Serializable;
import java.util.List;

import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Categoria;

public interface CategoriaDAO extends GenericDAO<Categoria> {

	public Categoria recuperarCategoria(String tipo);
	public boolean existeCategoria(String tipo);
	public Categoria borrarLogico(String tipo);
	public List<Cartelera> recuperarCarteleras(long id);
	public Categoria recuperarJoin(Serializable id);
}
