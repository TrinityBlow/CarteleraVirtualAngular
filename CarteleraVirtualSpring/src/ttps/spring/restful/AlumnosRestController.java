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

import ttps.spring.daoClasses.AlumnoDAO;
import ttps.spring.modelCartelera.Alumno;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AlumnosRestController {

	@Autowired
	AlumnoDAO alumnoDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/alumnos")
	public ResponseEntity<List<Alumno>> listAllCategorias() {
		List<Alumno> alumnos = alumnoDAO.recuperarTodos();
		return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/alumnos/{id}")
	public ResponseEntity<Alumno> getCategoria(@PathVariable("id") long id) {
		Alumno alumno = alumnoDAO.recuperar(id);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

	@PostMapping(path = "/alumnos")
	public ResponseEntity<Alumno> postCategoria(@RequestBody Alumno alumno){
		alumnoDAO.persistir(alumno);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/alumnos/{id}")
	public ResponseEntity<Alumno> deleteCategoria(@PathVariable("id") long id){
		Alumno alumno = alumnoDAO.borrarLogico(id);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}
	

	@PutMapping(path = "/alumnos/{id}")
	public ResponseEntity<Alumno> updateCategoria(@PathVariable("id") long id, @RequestBody Alumno alumnoUpdate){ 
		Alumno alumno = alumnoDAO.recuperar(id);
		if(alumno != null) {
			if(alumnoUpdate.getNombre() != null) alumno.setNombre(alumnoUpdate.getNombre());
			if(alumnoUpdate.getApellido() != null) alumno.setApellido(alumnoUpdate.getApellido());
			if(alumnoUpdate.getEmail() != null) alumno.setEmail(alumnoUpdate.getEmail());
			if(alumnoUpdate.getPass() != null) alumno.setPass(alumnoUpdate.getPass());
			alumno = alumnoDAO.actualizar(alumno);
		}
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}
	
}
