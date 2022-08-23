package cl.mayne.muvo.auth.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.mayne.muvo.auth.entity.Usuario;
import cl.mayne.muvo.auth.exception.UsuarioNotFoundException;
import cl.mayne.muvo.auth.service.UsuarioService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Usuario>> listUsers() {

		List<Usuario> usuarioList = usuarioService.findAll();
		System.out.println(usuarioList);
		if(usuarioList != null) {
			return ResponseEntity.ok().body(usuarioList);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

    @GetMapping("/username/{username}")
    public Usuario findByUsername(@PathVariable String username) {
        return usuarioService.findByUsername(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
    	
    	if(Strings.isEmpty(id)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    	}
    	
        try {
			Usuario usuario = usuarioService.findById(id);
			return ResponseEntity.ok(usuario);
		} catch (UsuarioNotFoundException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
			usuarioService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (UsuarioNotFoundException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Usuario no encontrado");
		}
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario, @PathVariable String id) {
        try {
			Usuario usuarioUpdated = usuarioService.updateUsuario(usuario, id);
			return ResponseEntity.ok(usuarioUpdated);
		} catch (UsuarioNotFoundException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
}
