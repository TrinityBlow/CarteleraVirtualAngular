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

import ttps.spring.daoClasses.AdministradorDAO;
import ttps.spring.modelCartelera.Administrador;

@RestController
public class AdministradoresRestController {

	@Autowired
	AdministradorDAO administradorDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/administradores")
	public ResponseEntity<List<Administrador>> listAllCategorias() {
		List<Administrador> administradores = administradorDAO.recuperarTodos();
		return new ResponseEntity<List<Administrador>>(administradores, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/administradores/{id}")
	public ResponseEntity<Administrador> getCategoria(@PathVariable("id") long id) {
		Administrador administrador = administradorDAO.recuperar(id);
		return new ResponseEntity<Administrador>(administrador, HttpStatus.OK);
	}

	@PostMapping(path = "/administradores")
	public ResponseEntity<Administrador> postCategoria(@RequestBody Administrador administrador){
		administradorDAO.persistir(administrador);
		return new ResponseEntity<Administrador>(administrador, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/administradores/{id}")
	public ResponseEntity<Administrador> deleteCategoria(@PathVariable("id") long id){
		Administrador administrador = administradorDAO.borrarLogico(id);
		return new ResponseEntity<Administrador>(administrador, HttpStatus.OK);
	}
	

	@PutMapping(path = "/administradores/{id}")
	public ResponseEntity<Administrador> updateCategoria(@PathVariable("id") long id, @RequestBody Administrador administradorUpdate){ 
		Administrador administrador = administradorDAO.recuperar(id);
		if(administrador != null) {
			if(administradorUpdate.getNombre() != null) administrador.setNombre(administradorUpdate.getNombre());
			if(administradorUpdate.getApellido() != null) administrador.setApellido(administradorUpdate.getApellido());
			if(administradorUpdate.getEmail() != null) administrador.setEmail(administradorUpdate.getEmail());
			if(administradorUpdate.getPass() != null) administrador.setPass(administradorUpdate.getPass());
			administrador = administradorDAO.actualizar(administrador);
		}
		return new ResponseEntity<Administrador>(administrador, HttpStatus.OK);
	}
	
}
