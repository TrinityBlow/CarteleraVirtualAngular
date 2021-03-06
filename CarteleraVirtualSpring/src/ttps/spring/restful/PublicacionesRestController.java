package ttps.spring.restful;

import java.util.Calendar;
import java.util.Date;
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
import ttps.spring.daoClasses.CarteleraDAO;
import ttps.spring.daoClasses.DocenteDAO;
import ttps.spring.daoClasses.PublicacionDAO;
import ttps.spring.daoClasses.PublicadorDAO;
import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Publicacion;
import ttps.spring.modelCartelera.Usuario;
import ttps.spring.restful.jsonDTO.PublicacionJsonCrear;
import ttps.spring.restfulClasses.PublicacionRestDTO;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PublicacionesRestController {
	
	@Autowired
	PublicacionDAO publicacionDAO;
	@Autowired
	AdministradorDAO administradorDAO;
	@Autowired
	DocenteDAO docenteDAO;
	@Autowired
	PublicadorDAO publicadorDAO;
	@Autowired
	UsuarioDAO usuarioDAO;
	@Autowired
	CarteleraDAO carteleraDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/publicaciones")
	public ResponseEntity<List<Publicacion>> listAllPublicacion() {
		List<Publicacion> publicaciones = publicacionDAO.recuperarTodos();
		return new ResponseEntity<List<Publicacion>>(publicaciones, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/publicaciones/{id}")
	public ResponseEntity<Publicacion> getPublicacion(@PathVariable("id") long id) {
		Publicacion publicacion = publicacionDAO.recuperar(id);
		return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
	}
	
	private Usuario usuarioNoAlumno(long id) {
		Usuario user = null;
		if(administradorDAO.existe(id)) {
			user = administradorDAO.recuperar(id);
		}
		if(docenteDAO.existe(id)) {
			user = docenteDAO.recuperar(id);
		}
		if(publicadorDAO.existe(id)) {
			user = publicadorDAO.recuperar(id);
		}
		return user;
	}
	
	
	//implementar mas control para los usuarios que no son alumnos  usuarioNoAlumno(id)
	@PostMapping(path = "/publicaciones")
	public ResponseEntity<Publicacion> postPublicacionCustom(@RequestBody PublicacionJsonCrear publicacionDTO){
		Publicacion publicacion = null;
		Usuario user = usuarioDAO.recuperarUsuario(publicacionDTO.getCreador());
		Cartelera cartelera = carteleraDAO.recuperar(publicacionDTO.getCarteleraId());
		if(user != null && cartelera != null) {
			publicacion = new Publicacion();
			publicacion.setTitulo(publicacionDTO.getTitulo());
			publicacion.setNota("");
			publicacion.setImagen("");
			publicacion.setComHabilitado(1);
			publicacion.setFecha(Calendar.getInstance().getTime());
			publicacion.setCreador(user);
			publicacion.setCartelera(cartelera);
			publicacionDAO.persistir(publicacion);	
		}
		return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
	}

	@PostMapping(path = "/publicaciones/old")
	public ResponseEntity<Publicacion> postPublicacion(@RequestBody PublicacionRestDTO publicacionDTO){
		Publicacion publicacion = null;
		Usuario user = usuarioNoAlumno(publicacionDTO.getCreador());
		Cartelera cartelera = carteleraDAO.recuperar(publicacionDTO.getCartelera());
		if(user != null && cartelera != null) {
			publicacion = new Publicacion();
			publicacion.setTitulo(publicacionDTO.getTitulo());
			publicacion.setNota(publicacionDTO.getNota());
			publicacion.setImagen(publicacionDTO.getImagen());
			publicacion.setComHabilitado(publicacionDTO.getComHabilitado());
			publicacion.setFecha(new Date());
			publicacion.setCreador(user);
			publicacion.setCartelera(cartelera);
			publicacionDAO.persistir(publicacion);	
		}
		return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/publicaciones/{id}")
	public ResponseEntity<Publicacion> deletePublicacion(@PathVariable("id") long id){
		Publicacion publicacion = publicacionDAO.borrarLogico(id);
		return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
	}
	

	@PutMapping(path = "/publicaciones/{id}")
	public ResponseEntity<Publicacion> updatePublicacion(@PathVariable("id") long id, @RequestBody PublicacionRestDTO publicacionDTO){
		Publicacion publicacion = publicacionDAO.recuperar(id);
		if(publicacion != null) {
			if(publicacionDTO.getTitulo() != null) publicacion.setTitulo(publicacionDTO.getTitulo());
			if(publicacionDTO.getNota() != null) publicacion.setNota(publicacionDTO.getNota());
			if(publicacionDTO.getImagen() != null) publicacion.setImagen(publicacionDTO.getImagen());
			if(publicacionDTO.getComHabilitado() != null) publicacion.setComHabilitado(publicacionDTO.getComHabilitado());
			publicacion = publicacionDAO.actualizar(publicacion);
		}
		return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
	}
	
	@PutMapping(path = "/publicaciones/toggleComentarios/{id}")
	public ResponseEntity<Publicacion> toggleComentarios(@PathVariable("id") long id){
		Publicacion publicacion = publicacionDAO.recuperar(id);
		if(publicacion != null) {
			publicacion.toggleComentarios();
			publicacion = publicacionDAO.actualizar(publicacion);
		}
		return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
	}
	
}
