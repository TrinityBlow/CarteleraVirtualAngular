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

import ttps.spring.daoClasses.PublicadorDAO;
import ttps.spring.modelCartelera.Publicador;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PublicadoresRestController {

	@Autowired
	PublicadorDAO publicadorDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/publicadores")
	public ResponseEntity<List<Publicador>> listAllCategorias() {
		List<Publicador> publicadores = publicadorDAO.recuperarTodos();
		return new ResponseEntity<List<Publicador>>(publicadores, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/publicadores/{id}")
	public ResponseEntity<Publicador> getCategoria(@PathVariable("id") long id) {
		Publicador publicador = publicadorDAO.recuperar(id);
		return new ResponseEntity<Publicador>(publicador, HttpStatus.OK);
	}

	@PostMapping(path = "/publicadores")
	public ResponseEntity<Publicador> postCategoria(@RequestBody Publicador publicador){
		publicadorDAO.persistir(publicador);
		return new ResponseEntity<Publicador>(publicador, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/publicadores/{id}")
	public ResponseEntity<Publicador> deleteCategoria(@PathVariable("id") long id){
		Publicador publicador = publicadorDAO.borrarLogico(id);
		return new ResponseEntity<Publicador>(publicador, HttpStatus.OK);
	}
	

	@PutMapping(path = "/publicadores/{id}")
	public ResponseEntity<Publicador> updateCategoria(@PathVariable("id") long id, @RequestBody Publicador publicadorUpdate){ 
		Publicador publicador = publicadorDAO.recuperar(id);
		if(publicador != null) {
			if(publicadorUpdate.getNombre() != null) publicador.setNombre(publicadorUpdate.getNombre());
			if(publicadorUpdate.getApellido() != null) publicador.setApellido(publicadorUpdate.getApellido());
			if(publicadorUpdate.getEmail() != null) publicador.setEmail(publicadorUpdate.getEmail());
			if(publicadorUpdate.getPass() != null) publicador.setPass(publicadorUpdate.getPass());
			publicador = publicadorDAO.actualizar(publicador);
		}
		return new ResponseEntity<Publicador>(publicador, HttpStatus.OK);
	}
	
}
