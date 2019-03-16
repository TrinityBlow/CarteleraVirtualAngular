package ttps.spring.restful;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.daoClasses.AdministradorDAO;
import ttps.spring.daoClasses.CarteleraDAO;
import ttps.spring.daoClasses.CategoriaDAO;
import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.modelCartelera.Administrador;
import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Categoria;
import ttps.spring.modelCartelera.Publicacion;
import ttps.spring.modelCartelera.Usuario;
import ttps.spring.restful.jsonDTO.AdministradorJsonDTO;
import ttps.spring.restful.jsonDTO.CarteleraJsonDTO;
import ttps.spring.restful.jsonDTO.CategoriaJsonDTO;
import ttps.spring.restful.jsonDTO.PublicacionJsonDTO;
import ttps.spring.restfulClasses.CarteleraRestDTO;
import ttps.spring.restfulClasses.TokenValidation;

@CrossOrigin(origins = "*")
@RestController
public class CartelerasRestController {
	
	@Autowired
	CarteleraDAO carteleraDAO;
	@Autowired
	AdministradorDAO administradorDAO;
	@Autowired
	CategoriaDAO categoriaDAO;
	@Autowired
	UsuarioDAO usuarioDAO;

	@GetMapping(path = "/carteleras")
	public ResponseEntity<List<CarteleraJsonDTO>> listAllCarteleras(@RequestHeader("token") String token) {
		if(habilitado(token)) {
			List<CarteleraJsonDTO> cartelerasDTO = new ArrayList<CarteleraJsonDTO>();
			List<Cartelera> carteleras = carteleraDAO.recuperarTodosJoin();
			for(Cartelera cartelera: carteleras) {
				cartelerasDTO.add(carteleraDTOTradduction(cartelera));
			}
			return new ResponseEntity<List<CarteleraJsonDTO>>(cartelerasDTO, HttpStatus.OK);
		}
		return new ResponseEntity<List<CarteleraJsonDTO>>(null,null, HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(path = "/carteleras/cantidad")
	public ResponseEntity<List<CarteleraJsonDTO>> listCantCategorias(@RequestHeader("token") String token, @RequestBody int cant) {
		if(habilitado(token)) {
			int cartelerasSize;
			List<CarteleraJsonDTO> cartelerasDTO = new ArrayList<CarteleraJsonDTO>();
			List<Cartelera> carteleras = carteleraDAO.recuperarTodosJoin();
			Cartelera dbCartelera;
			cartelerasSize = carteleras.size();

			for(int x = 1; x <= cant; x++ ) {
				try {
					dbCartelera = carteleras.get(cartelerasSize - x);
					cartelerasDTO.add(carteleraDTOTradduction(dbCartelera));
				} catch (IndexOutOfBoundsException e){
				}
			}
			return new ResponseEntity<List<CarteleraJsonDTO>>(cartelerasDTO, HttpStatus.OK);
		}
		return new ResponseEntity<List<CarteleraJsonDTO>>(null,null, HttpStatus.UNAUTHORIZED);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/carteleras/{id}")
	public ResponseEntity<CarteleraJsonDTO> getCategoria(@PathVariable("id") long id) {
		CarteleraJsonDTO carteleraDTO = buscarCarteleraDTO(id);
		return new ResponseEntity<CarteleraJsonDTO>(carteleraDTO, HttpStatus.OK);
	}

	@PostMapping(path = "/carteleras")
	public ResponseEntity<CarteleraJsonDTO> postCategoria(@RequestBody CarteleraRestDTO cartelera){

		Administrador userAdmin = administradorDAO.recuperarUsuario(cartelera.getAdmin());
		if(userAdmin != null) {
			Cartelera agregar = new Cartelera(); 
			agregar.setTitulo(cartelera.getTitulo());
			agregar.setFecha(new Date());
			agregar.setCreador(userAdmin);
			agregar.setCategoria(categoriaDAO.recuperarCategoria(cartelera.getCategoria()));
			carteleraDAO.persistir(agregar);
			CarteleraJsonDTO agregado = buscarCarteleraDTO(agregar.getId());
			return new ResponseEntity<CarteleraJsonDTO>(agregado, HttpStatus.OK);
		}

		return new ResponseEntity<CarteleraJsonDTO>(null,null, HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping(path = "/carteleras/{id}")
	public ResponseEntity<Cartelera> deleteCategoria(@PathVariable("id") long id){
		Cartelera cartelera = carteleraDAO.borrarLogico(id);
		return new ResponseEntity<Cartelera>(cartelera, HttpStatus.OK);
	}
	

	@PutMapping(path = "/carteleras/{id}")
	public ResponseEntity<Cartelera> updateCategoria(@PathVariable("id") long id, @RequestBody String titulo){ 
		Cartelera cartelera = carteleraDAO.recuperar(id);
		if(cartelera != null) {
			if(titulo != null) cartelera.setTitulo(titulo);
			cartelera = carteleraDAO.actualizar(cartelera);
		}
		return new ResponseEntity<Cartelera>(cartelera, HttpStatus.OK);
	}
	
	private boolean habilitado(String token) {
		if(TokenValidation.tokenValidation(token)) {
			Usuario user = usuarioDAO.recuperar(Long.parseLong(TokenValidation.getTokenId(token)));
			if(user != null) {
			 return true;
			}
		}
		return false;
	}
	
	private CarteleraJsonDTO buscarCarteleraDTO(long id) {
		Cartelera cartelera = carteleraDAO.recuperarJoin(id);
		return carteleraDTOTradduction(cartelera);
	}
	
	private CarteleraJsonDTO carteleraDTOTradduction(Cartelera cartelera){
		CarteleraJsonDTO carteleraDTO = null;
		if(cartelera != null) {
			carteleraDTO = new CarteleraJsonDTO();
			Categoria categoria = cartelera.getCategoria();
			Administrador creador = cartelera.getCreador();
			List<Publicacion> publicaciones = cartelera.getPublicaciones();
			CategoriaJsonDTO categoriaDTO = new CategoriaJsonDTO();
			AdministradorJsonDTO administradorDTO = new AdministradorJsonDTO();
			PublicacionJsonDTO publicacionDTO;
			
			//cartelera set
			carteleraDTO.setId(cartelera.getId());
			carteleraDTO.setTitulo(cartelera.getTitulo());
			carteleraDTO.setFecha(cartelera.getFecha());
			carteleraDTO.setCategoria(categoriaDTO);
			carteleraDTO.setCreador(administradorDTO);
			
			//categoria set
			categoriaDTO.setId(categoria.getId());
			categoriaDTO.setTipo(categoria.getTipo());
			
			//creador set
			administradorDTO.setId(creador.getId());
			administradorDTO.setNombre(creador.getNombre());
			administradorDTO.setApellido(creador.getApellido());
			administradorDTO.setEmail(creador.getEmail());
			administradorDTO.setPass(creador.getPass());
			
			//lista publicaciones add
			for(Publicacion publicacion: publicaciones) {
				publicacionDTO = new PublicacionJsonDTO();
				
				publicacionDTO.setId(publicacion.getId());
				publicacionDTO.setTitulo(publicacion.getTitulo());
				publicacionDTO.setNota(publicacion.getNota());
				publicacionDTO.setFecha(publicacion.getFecha());
				publicacionDTO.setImagen(publicacion.getImagen());
				publicacionDTO.setComHabilitado(publicacion.getComHabilitado());
			
				carteleraDTO.addPublicacionDTO(publicacionDTO);
			}
		}
		
		return carteleraDTO;
	}
	
}