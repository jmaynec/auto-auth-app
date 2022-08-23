package cl.mayne.muvo.auth.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document("user")
@Data
@ToString
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@Column(length = 30, unique = true)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	private Boolean enabled;
	
	private List<Role> roles;
	
	private String nombres;
	
	private String apellidos;
	
	private String celular;
	
	private String email;
	
	private Long rut;
	
	private String dv;
	
	
	public Usuario() {
		
	}
	
	public Usuario(String id, String username, String password, Boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
}
