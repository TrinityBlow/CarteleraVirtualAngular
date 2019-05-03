package ttps.spring.restful;

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

import ttps.spring.daoClasses.AlumnoDAO;
import ttps.spring.daoClasses.ComentarioDAO;
import ttps.spring.daoClasses.PublicacionDAO;
import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.modelCartelera.Administrador;
import ttps.spring.modelCartelera.Alumno;
import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Comentario;
import ttps.spring.modelCartelera.Publicacion;
import ttps.spring.modelCartelera.Usuario;
import ttps.spring.restful.jsonDTO.CarteleraJsonDTO;
import ttps.spring.restful.jsonDTO.ComentarioJsonDTO;
import ttps.spring.restfulClasses.ComentarioDeRespuestaRest;


@CrossOrigin(origins = "*")
@RestController
public class ComentariosRestController {
	
	@Autowired
	ComentarioDAO comentarioDAO;
	@Autowired
	PublicacionDAO publicacionDAO;
	@Autowired
	UsuarioDAO usuarioDAO;
	@Autowired
	AlumnoDAO alumnoDAO;
	
	//Recupero todos los usuarios
	@GetMapping(path = "/comentarios")
	public ResponseEntity<List<Comentario>> listAllComentarios() {
		List<Comentario> comentarios = comentarioDAO.recuperarTodos();
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/comentarios/{id}")
	public ResponseEntity<Comentario> getPublicacion(@PathVariable("id") long id)  {
		Comentario comentario = comentarioDAO.recuperarJoin(id);
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
	}

	@PostMapping(path = "/comentarios/comentar")
	public ResponseEntity<Comentario> postPublicacion(@RequestBody ComentarioJsonDTO comentario){
		
		Alumno alumno = alumnoDAO.recuperarUsuario(comentario.getUsername());
		Publicacion publicacion = publicacionDAO.recuperar(comentario.getPublicacion());
		if(alumno != null && publicacion != null) {
			Comentario agregar = new Comentario(); 
			agregar.preguntar(alumno,comentario.getComentario());
			agregar.setPublicacion(publicacion);
			Comentario agregado = comentarioDAO.persistir(agregar);
			return new ResponseEntity<Comentario>(agregado, HttpStatus.OK);
		}

		return new ResponseEntity<Comentario>(null,null, HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping(path = "/comentarios/{id}")
	public ResponseEntity<Comentario> deletePublicacion(@PathVariable("id") long id){
		Comentario comentario = comentarioDAO.borrarLogico(id);
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
	}
	

	@PutMapping(path = "/comentarios/{id}")
	public ResponseEntity<Comentario> updatePublicacion(@PathVariable("id") long id, @RequestBody Comentario comentarioUpdate){
		Comentario comentario = comentarioDAO.recuperarJoin(id);
		if(comentario != null) {
			if(comentarioUpdate.getRespuesta() != null) comentario.setRespuesta(comentarioUpdate.getRespuesta());
			if(comentarioUpdate.getFechaRespuesta() != null) comentario.setFechaRespuesta(comentarioUpdate.getFechaRespuesta());
			if(comentarioUpdate.getRespondio() != null) comentario.setRespondio(comentarioUpdate.getRespondio());
			if(comentarioUpdate.getComentario() != null) comentario.setComentario(comentarioUpdate.getComentario());
			if(comentarioUpdate.getFechaComentario() != null) comentario.setFechaComentario(comentarioUpdate.getFechaComentario());
			if(comentarioUpdate.getAlumno() != null) comentario.setAlumno(comentarioUpdate.getAlumno());
			comentario = comentarioDAO.actualizar(comentario);
		}
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
	}

	
	@GetMapping(path = "/comentarios/publicacion/{id}")
	public ResponseEntity<List<Comentario>> listAllComentariosOfPublicacion(@PathVariable("id") long publicacionId) {
		Publicacion publicacion = publicacionDAO.recuperar(publicacionId);
		List<Comentario> comentarios = comentarioDAO.recuperarDePublicacionJoin(publicacion);
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/comentarios/responder")
	public ResponseEntity<Comentario> postComentarioRespuesta(@RequestBody ComentarioDeRespuestaRest comentarioRespuesta){
		Comentario comentario = null;
		if(comentarioRespuesta != null) {
			if(comentarioRespuesta.notNull()) {
				Usuario usuario = usuarioDAO.recuperarUsuario(comentarioRespuesta.getUsuarioEmail());
				comentario = comentarioDAO.recuperar((comentarioRespuesta.getComentarioId()));
				
				comentario.responder(usuario, comentarioRespuesta.getRespuesta());
				
				comentario = comentarioDAO.actualizar(comentario);
			}
		}
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
	}
	
	
	
	
}
