package ttps.spring.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.daoClasses.AdministradorDAO;
import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.modelCartelera.Administrador;
import ttps.spring.modelCartelera.Alumno;
import ttps.spring.modelCartelera.Docente;
import ttps.spring.modelCartelera.Publicador;
import ttps.spring.modelCartelera.Usuario;
import ttps.spring.restfulClasses.TokenValidation;
import ttps.spring.restfulClasses.UsuarioPass;
import ttps.spring.restfulClasses.UsuarioRest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UsuariosRestController {

	@Autowired
	UsuarioDAO usuarioDAO;
	@Autowired
	AdministradorDAO administradorDAO;
	
	private Boolean canChange(String token, long id) {
		if(TokenValidation.tokenValidation(token,id)) {
			return true;
		}else {
			Administrador userAdmin = administradorDAO.recuperar(TokenValidation.getTokenId(token));
			if(userAdmin != null) {
				return true;
			}
		}
		return false;
	}
	
	@GetMapping(path = "/usuarios/{id}")
	public ResponseEntity<Usuario> getCategoria(@RequestHeader("token") String token, @PathVariable("id") long id){
	   Usuario usuario = usuarioDAO.recuperar(id);
	   if(usuario != null) {
		   if(TokenValidation.tokenValidation(token,id)) {
			   return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		   }
		   return new ResponseEntity<Usuario>(null,null, HttpStatus.UNAUTHORIZED);
	   }
	   return new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "/usuarios")
	public ResponseEntity<Usuario> postUsuario(@RequestBody UsuarioRest usuario){
		Usuario creado = null;
		if(usuario.notNull()) {
			switch (usuario.getRol()) {
            case 1:  
            	creado = new Administrador();
            	break;
            case 2:
            	creado = new Docente();
            	break;
            case 3:
            	creado = new Publicador();
            	break;
            default:
            	creado = new Alumno();
            	break;
			}
			creado.setNombre(usuario.getNombre());
			creado.setApellido(usuario.getApellido());
			creado.setEmail(usuario.getEmail());
			creado.setPass(usuario.getPass());
			creado = usuarioDAO.agregarUnico(creado);
			if(creado == null) {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("error", "El email ya esta siendo utilizado");
				return new ResponseEntity<Usuario>(creado, responseHeaders, HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<Usuario>(creado, HttpStatus.CREATED);
	}
	
	
	@PostMapping(path = "/autenticacion")
	public ResponseEntity<String> autenticar(@RequestBody UsuarioPass userPass){
	   HttpHeaders responseHeaders = new HttpHeaders();
	   Usuario usuario = usuarioDAO.recuperarUsuario(userPass.getEmail());
	   if(usuario != null) {
		   if(usuario.getPass().equals(userPass.getPass())) {
			   responseHeaders.set("token", usuario.getId() + "123456");
			   return new ResponseEntity<String>("Usuario autorizado",responseHeaders ,HttpStatus.OK);
		   }   
	   }
		return new ResponseEntity<String>("Usuario no autorizado" ,HttpStatus.FORBIDDEN);
	}
	
	@PutMapping(path = "/usuarios/{id}")
	public ResponseEntity<Usuario> updateUsuario(@RequestHeader("token") String token, @RequestBody UsuarioRest usuario, @PathVariable("id") long id){
	   Usuario usuarioBuscado = usuarioDAO.recuperar(id);
	   if(usuarioBuscado != null) {
		   if(canChange(token,id)){
			   if(usuario.getNombre() != null)usuarioBuscado.setNombre(usuario.getNombre());
			   if(usuario.getApellido() != null)usuarioBuscado.setApellido(usuario.getApellido());
			   if(usuario.getEmail() != null)usuarioBuscado.setEmail(usuario.getEmail());
			   if(usuario.getPass() != null)usuarioBuscado.setPass(usuario.getPass());
			   usuarioDAO.actualizar(usuarioBuscado);
			   return new ResponseEntity<Usuario>(usuarioBuscado, HttpStatus.OK);	   
		   }
		   return new ResponseEntity<Usuario>(null,null, HttpStatus.UNAUTHORIZED);
	   }
	   return new ResponseEntity<Usuario>(usuarioBuscado, HttpStatus.NOT_FOUND);
	}
	

}
