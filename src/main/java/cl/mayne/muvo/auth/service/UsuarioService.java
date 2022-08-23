package cl.mayne.muvo.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mayne.muvo.auth.dao.IUsuarioDao;
import cl.mayne.muvo.auth.entity.Usuario;
import cl.mayne.muvo.auth.exception.UsuarioNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }
    
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Usuario findById(String id) throws UsuarioNotFoundException {
        return usuarioDao.findById(id)
          .orElseThrow(UsuarioNotFoundException::new);
    }

    public Usuario save (Usuario usuario) {
        return usuarioDao.save(usuario);         
    }
	
	public void delete(String id) throws UsuarioNotFoundException {
        usuarioDao.findById(id)
          .orElseThrow(UsuarioNotFoundException::new);
        usuarioDao.deleteById(id);
    }

    public Usuario updateUsuario(Usuario usuario, String id) throws UsuarioNotFoundException {
//        if (car.getId() != id) {
//            throw new CarIdMismatchException();
//        }
        usuarioDao.findById(id)
          .orElseThrow(UsuarioNotFoundException::new);
        return usuarioDao.save(usuario);
    }
}
