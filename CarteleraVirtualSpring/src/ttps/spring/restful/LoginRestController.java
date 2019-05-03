package ttps.spring.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttps.spring.daoClasses.UsuarioDAO;
import ttps.spring.model.Credentials;
import ttps.spring.model.UsernaneAndPassword;
import ttps.spring.modelCartelera.Usuario;
import ttps.spring.services.TokenServices;

/**
 * @author manuel
 */
@CrossOrigin(origins = "*")
@RestController
public class LoginRestController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private TokenServices tokenServices;

    // un dia
    private final int EXPIRATION_IN_SEC = 86400;
    
    private String rol;

    @PostMapping(path = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody UsernaneAndPassword userpass) {

        if(isLoginSuccess(userpass.getUsername(), userpass.getPassword())) {
            String token = tokenServices.generateToken(userpass.getUsername(), EXPIRATION_IN_SEC);
            return ResponseEntity.ok(new Credentials(token, EXPIRATION_IN_SEC, userpass.getUsername(),rol));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o password incorrecto");
        }
    }

    private boolean isLoginSuccess(String username, String password) {
        // recupero el usuario de la base de usuarios
        Usuario u = usuarioDAO.recuperarUsuario(username);
        rol = u.getRol();

        System.out.println("ASDASD");    
        // chequeo que el usuario exista y el password sea correcto
        return (u != null && u.getPass().equals(password));
    }
}
