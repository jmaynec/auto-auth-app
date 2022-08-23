package cl.mayne.muvo.auth.filter;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class KeySecretConstant {
	public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
