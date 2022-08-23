package cl.mayne.muvo.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.mayne.muvo.auth.dao.IUsuarioDao;
import cl.mayne.muvo.auth.entity.Role;
import cl.mayne.muvo.auth.entity.Usuario;

@RestController
public class RegisterController {

	private Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {

		try {

			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuario.setEnabled(true);

			List<Role> roles = new ArrayList<Role>();
			Role rol = new Role();
			rol.setAuthority("ROLE_USER");
			roles.add(rol);
			usuario.setRoles(roles);

			usuarioDao.save(usuario);
		} catch (Exception e) {
			logger.error("Error al crear o actualizar usuario " + usuario, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok().body(usuario);
	}
}
