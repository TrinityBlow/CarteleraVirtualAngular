package ttps.spring.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.daoClasses.CategoriaDAO;
import ttps.spring.modelCartelera.Categoria;

@CrossOrigin(origins = "*")
@RestController
public class CategoriasRestController {
	
	@Autowired
	CategoriaDAO categoriaDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/categorias")
	public ResponseEntity<List<Categoria>> listAllCategorias() {
		List<Categoria> categorias = categoriaDAO.recuperarTodos();
		return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/categorias/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable("id") long id)  {
		Categoria categoria = categoriaDAO.recuperarJoin(id);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}

	@PostMapping(path = "/categorias")
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
		categoriaDAO.persistir(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/categorias/{id}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable("id") long id){
		Categoria categoria = categoriaDAO.borrarLogico(id);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	

	@PutMapping(path = "/categorias/{id}")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable("id") long id, @RequestBody Categoria categoriaUpdate){
		Categoria categoria = categoriaDAO.recuperarJoin(id);
		if(categoria != null) {
			if(categoriaUpdate.getTipo() != null) categoria.setTipo(categoriaUpdate.getTipo());
			categoria = categoriaDAO.actualizar(categoria);
		}
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	
}
