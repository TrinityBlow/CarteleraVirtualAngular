package ttps.spring.restful.jsonDTO;

import ttps.spring.modelCartelera.Usuario;
import ttps.spring.modelCartelera.Publicacion;

public class TranslateClassToJSON {
	
	public static PublicacionJsonDTO publicacionDTOTraduction(Publicacion publicacion) {
		

		PublicacionJsonDTO publicacionDTO = null;
		
		if(publicacion != null) {
			publicacionDTO = new PublicacionJsonDTO();
			Usuario creador = publicacion.getCreador();
			UsuarioJsonDTO creadorDTO = new UsuarioJsonDTO();
			
			//cartelera set
			publicacionDTO.setId(publicacion.getId());
			publicacionDTO.setTitulo(publicacion.getTitulo());
			publicacionDTO.setNota(publicacion.getNota());
			publicacionDTO.setComHabilitado(publicacion.getComHabilitado());
			publicacionDTO.setImagen(publicacion.getImagen());
			publicacionDTO.setFecha(publicacion.getFecha());
			
			//creador set
			creadorDTO.setId(creador.getId());
			creadorDTO.setNombre(creador.getNombre());
			creadorDTO.setApellido(creador.getApellido());
			creadorDTO.setEmail(creador.getEmail());
			creadorDTO.setPass(creador.getPass());
			
		}
		
		return publicacionDTO;
	}

}
