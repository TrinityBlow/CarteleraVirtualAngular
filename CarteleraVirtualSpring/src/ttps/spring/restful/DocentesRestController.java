package ttps.spring.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.daoClasses.DocenteDAO;
import ttps.spring.modelCartelera.Docente;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DocentesRestController {

	@Autowired
	DocenteDAO docenteDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/docentes")
	public ResponseEntity<List<Docente>> listAllCategorias() {
		List<Docente> docentes = docenteDAO.recuperarTodos();
		return new ResponseEntity<List<Docente>>(docentes, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/docentes/{id}")
	public ResponseEntity<Docente> getCategoria(@PathVariable("id") long id) {
		Docente docente = docenteDAO.recuperar(id);
		return new ResponseEntity<Docente>(docente, HttpStatus.OK);
	}

	@PostMapping(path = "/docentes")
	public ResponseEntity<Docente> postCategoria(@RequestBody Docente docente){
		docenteDAO.persistir(docente);
		return new ResponseEntity<Docente>(docente, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/docentes/{id}")
	public ResponseEntity<Docente> deleteCategoria(@PathVariable("id") long id){
		Docente docente = docenteDAO.borrarLogico(id);
		return new ResponseEntity<Docente>(docente, HttpStatus.OK);
	}
	

	@PutMapping(path = "/docentes/{id}")
	public ResponseEntity<Docente> updateCategoria(@PathVariable("id") long id, @RequestBody Docente docenteUpdate){ 
		Docente docente = docenteDAO.recuperar(id);
		if(docente != null) {
			if(docenteUpdate.getNombre() != null) docente.setNombre(docenteUpdate.getNombre());
			if(docenteUpdate.getApellido() != null) docente.setApellido(docenteUpdate.getApellido());
			if(docenteUpdate.getEmail() != null) docente.setEmail(docenteUpdate.getEmail());
			if(docenteUpdate.getPass() != null) docente.setPass(docenteUpdate.getPass());
			docente = docenteDAO.actualizar(docente);
		}
		return new ResponseEntity<Docente>(docente, HttpStatus.OK);
	}
	
}
