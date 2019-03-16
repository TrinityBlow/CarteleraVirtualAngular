package ttps.spring.restful;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.daoClasses.TestDAO;
import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.modelCartelera.Test;
import ttps.spring.modelCartelera.Usuario;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestController {
	
	@Autowired
	UsuarioDAO testDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/test")
	public ResponseEntity<String> listAllUsers() {
	
		String strings = "asdzxc";
	
		Usuario u = testDAO.recuperarUsuario("asd@asd.com");
		
		System.out.println(u.getRol());
		
		return new ResponseEntity<String>(strings, HttpStatus.OK);
	}
	
	
//	@GetMapping(path = "/test/{id}")

	
//	@PostMapping(path = "/test")

	
//	@DeleteMapping

//	@PutMapping
	
}
	

