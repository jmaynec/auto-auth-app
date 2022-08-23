package cl.mayne.muvo.auth.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.mayne.muvo.auth.entity.Usuario;

@Repository
public interface IUsuarioDao extends MongoRepository<Usuario, String>{

	public Usuario findByUsername(String username);
}
