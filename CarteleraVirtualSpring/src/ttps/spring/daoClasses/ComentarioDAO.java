package ttps.spring.daoClasses;

import java.io.Serializable;
import java.util.List;

import ttps.spring.modelCartelera.Comentario;
import ttps.spring.modelCartelera.Publicacion;

public interface ComentarioDAO extends GenericDAO<Comentario> {

	public Comentario recuperarJoin(Serializable id);
	public List<Comentario> recuperarDePublicacionJoin(Publicacion publicacion);


}
