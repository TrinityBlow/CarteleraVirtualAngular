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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.daoClasses.AdministradorDAO;
import ttps.spring.daoClasses.CarteleraDAO;
import ttps.spring.daoClasses.CategoriaDAO;
import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.modelCartelera.Administrador;
import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Categoria;
import ttps.spring.modelCartelera.Docente;
import ttps.spring.modelCartelera.Publicacion;
import ttps.spring.modelCartelera.Usuario;
import ttps.spring.restful.jsonDTO.AdministradorJsonDTO;
import ttps.spring.restful.jsonDTO.CarteleraJsonDTO;
import ttps.spring.restful.jsonDTO.CarteleraOwnerJsonDTO;
import ttps.spring.restful.jsonDTO.CategoriaJsonDTO;
import ttps.spring.restful.jsonDTO.PublicacionJsonDTO;
import ttps.spring.restful.jsonDTO.TranslateClassToJSON;
import ttps.spring.restful.jsonDTO.UsuarioJsonDTO;
import ttps.spring.restfulClasses.CarteleraRestDTO;


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
	public ResponseEntity<List<CarteleraJsonDTO>> listAllCarteleras() {
		List<CarteleraJsonDTO> cartelerasDTO = new ArrayList<CarteleraJsonDTO>();
		List<Cartelera> carteleras = carteleraDAO.recuperarTodosJoin();
		for(Cartelera cartelera: carteleras) {
			cartelerasDTO.add(carteleraDTOTraduction(cartelera));
		}
		return new ResponseEntity<List<CarteleraJsonDTO>>(cartelerasDTO, HttpStatus.OK);
	}

	@PostMapping(path = "/carteleras/cantidad")
	public ResponseEntity<List<CarteleraJsonDTO>> listCantCategorias(@RequestBody int cant) {
		int cartelerasSize;
		List<CarteleraJsonDTO> cartelerasDTO = new ArrayList<CarteleraJsonDTO>();
		List<Cartelera> carteleras = carteleraDAO.recuperarTodosJoin();
		Cartelera dbCartelera;
		cartelerasSize = carteleras.size();

		for(int x = 1; x <= cant; x++ ) {
			try {
				dbCartelera = carteleras.get(cartelerasSize - x);
				cartelerasDTO.add(carteleraDTOTraduction(dbCartelera));
			} catch (IndexOutOfBoundsException e){
			}
		}
		return new ResponseEntity<List<CarteleraJsonDTO>>(cartelerasDTO, HttpStatus.OK);
	}
	
	//Recupero un test dado
	@GetMapping(path = "/carteleras/{id}")
	public ResponseEntity<CarteleraJsonDTO> getCategoria(@PathVariable("id") long id) {
		CarteleraJsonDTO carteleraDTO = buscarCarteleraDTO(id);
		return new ResponseEntity<CarteleraJsonDTO>(carteleraDTO, HttpStatus.OK);
	}
	

	@GetMapping(path = "/carteleras/owners/{id}")
	public ResponseEntity<List<Usuario>> getOwnersCartelera(@PathVariable("id") long id) {
		Cartelera cartelera = carteleraDAO.recuperarJoin(id);
		List<Usuario> owners = cartelera.getMiembros();
		return new ResponseEntity<List<Usuario>>(owners, HttpStatus.OK);
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
	
	@PatchMapping(path = "/carteleras")
	public ResponseEntity<Cartelera> deleteCategoria(@RequestBody CarteleraRestDTO cartelera){
		if( cartelera.getTitulo() != null ) {
			Cartelera carteleraBorrada = carteleraDAO.borrarLogico(cartelera.getTitulo());
			return new ResponseEntity<Cartelera>(carteleraBorrada, HttpStatus.OK);
		}
		return new ResponseEntity<Cartelera>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(path = "/carteleras/{id}")
	public ResponseEntity<Cartelera> deleteCategoria(@PathVariable("id") long id){
		Cartelera cartelera = carteleraDAO.borrarLogico(id);
		return new ResponseEntity<Cartelera>(cartelera, HttpStatus.OK);
	}
	

	@PutMapping(path = "/carteleras/{id}")
	public ResponseEntity<Cartelera> updateCartelera(@PathVariable("id") long id, @RequestBody CarteleraRestDTO carteleraActualizada){
		Cartelera cartelera = carteleraDAO.recuperar(id);
		if(carteleraActualizada.getTitulo() != null) {
			Categoria buscarCategoria = categoriaDAO.recuperarCategoria(carteleraActualizada.getCategoria());
			if(buscarCategoria != null) cartelera.setCategoria(buscarCategoria);
			if(carteleraActualizada.getTitulo() != null) cartelera.setTitulo(carteleraActualizada.getTitulo());
			cartelera = carteleraDAO.actualizar(cartelera);
		}
		return new ResponseEntity<Cartelera>(cartelera, HttpStatus.OK);
	}
	
	@GetMapping(path = "/carteleras/publicaciones/{id}")
	public ResponseEntity<List<PublicacionJsonDTO>> publicacionesCartelera(@PathVariable("id") long id){
		Cartelera cartelera = carteleraDAO.recuperarJoin(id);
		if(cartelera != null) {
			List<PublicacionJsonDTO> publicacionesDTO = new ArrayList<PublicacionJsonDTO>();
			for(Publicacion publicacion: cartelera.getPublicaciones()) {
				publicacionesDTO.add(TranslateClassToJSON.publicacionDTOTraduction(publicacion));
			}
			return new ResponseEntity<List<PublicacionJsonDTO>>(publicacionesDTO, HttpStatus.OK);
		}
		return new ResponseEntity<List<PublicacionJsonDTO>>(HttpStatus.BAD_REQUEST);
	}


	@PostMapping(path = "/carteleras/owners")
	public ResponseEntity<List<Usuario>> agregarOwner(@RequestBody CarteleraOwnerJsonDTO usuarioAgregar){
		Cartelera cartelera = carteleraDAO.recuperarJoin(usuarioAgregar.getId_cartelera());
		Usuario nuevoOwner = usuarioDAO.recuperarUsuario(usuarioAgregar.getEmailUsuario());
		if(  (cartelera != null) && (nuevoOwner != null)  ) {
			List<Usuario> miembros = cartelera.getMiembros();
			miembros.add(nuevoOwner);
			return new ResponseEntity<List<Usuario>>(miembros, HttpStatus.OK);
		}
		return new ResponseEntity<List<Usuario>>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(path = "/carteleras/owners/{id}")
	public ResponseEntity<Cartelera> updateOwnersCartelera(@PathVariable("id") long id, @RequestBody String[] usuariosCambiar){
		Cartelera cartelera = carteleraDAO.recuperarJoin(id);
		if(cartelera != null && usuariosCambiar != null) {
			List<Usuario> nuevosOwners = new ArrayList<Usuario>();
			Usuario usuarioAgregar;
			for(String usuarioEmail :usuariosCambiar) {
				usuarioAgregar = usuarioDAO.recuperarUsuarioJoin(usuarioEmail);
				if(usuarioAgregar != null) {
					nuevosOwners.add(usuarioAgregar);	
				}
			}
			cartelera.setMiembros(nuevosOwners);
			cartelera = carteleraDAO.actualizar(cartelera);
			return new ResponseEntity<Cartelera>(cartelera, HttpStatus.OK);
		}
		return new ResponseEntity<Cartelera>(HttpStatus.BAD_REQUEST);
	}
	
	private CarteleraJsonDTO buscarCarteleraDTO(long id) {
		Cartelera cartelera = carteleraDAO.recuperarJoin(id);
		return carteleraDTOTraduction(cartelera);
	}
	
	static public CarteleraJsonDTO carteleraDTOTraduction(Cartelera cartelera){
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
				if(publicacion.getActivo() == 1) {

					Usuario publicacionCreador = publicacion.getCreador();
					UsuarioJsonDTO creadorPublicacionDTO = new UsuarioJsonDTO();
					publicacionDTO = new PublicacionJsonDTO();
					
					publicacionDTO.setId(publicacion.getId());
					publicacionDTO.setTitulo(publicacion.getTitulo());
					publicacionDTO.setNota(publicacion.getNota());
					publicacionDTO.setFecha(publicacion.getFecha());
					publicacionDTO.setImagen(publicacion.getImagen());
					publicacionDTO.setComHabilitado(publicacion.getComHabilitado());
					
					creadorPublicacionDTO.setId(publicacionCreador.getId());
					creadorPublicacionDTO.setNombre(publicacionCreador.getNombre());
					creadorPublicacionDTO.setApellido(publicacionCreador.getApellido());
					creadorPublicacionDTO.setEmail(publicacionCreador.getEmail());
					publicacionDTO.setCreador(creadorPublicacionDTO);
				
					carteleraDTO.addPublicacionDTO(publicacionDTO);
				}
			}
		}
		
		return carteleraDTO;
	}
	
}