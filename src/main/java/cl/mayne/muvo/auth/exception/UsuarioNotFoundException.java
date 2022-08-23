package cl.mayne.muvo.auth.exception;

public class UsuarioNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(){
		super();
	}
	
	public UsuarioNotFoundException(String mensaje) {
		super(mensaje);
	}
}
