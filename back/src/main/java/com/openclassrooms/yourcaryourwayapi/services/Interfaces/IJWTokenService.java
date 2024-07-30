package com.openclassrooms.yourcaryourwayapi.services.Interfaces;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

/**
 * The interface Ijw token service.
 */
public interface IJWTokenService {

	/**
	 * Extract email string.
	 *
	 * @param token the token
	 * @return the string
	 */
	String extractEmail(String token);

	/**
	 * Is valid boolean.
	 *
	 * @param token the token
	 * @param email the email
	 * @return the boolean
	 */
	boolean isValid(String token, String email);

	/**
	 * Is token expired boolean.
	 *
	 * @param token the token
	 * @return the boolean
	 */
	boolean isTokenExpired(String token);

	/**
	 * Extract expiration date.
	 *
	 * @param token the token
	 * @return the date
	 */
	Date extractExpiration(String token);

	/**
	 * Extract claim t.
	 *
	 * @param <T>      the type parameter
	 * @param token    the token
	 * @param resolver the resolver
	 * @return the t
	 */
	<T> T extractClaim(String token, Function<Claims, T> resolver);

	/**
	 * Extract all claims claims.
	 *
	 * @param token the token
	 * @return the claims
	 */
	Claims extractAllClaims(String token);

	/**
	 * Generate token string.
	 *
	 * @param email the email
	 * @return the string
	 */
	String generateToken(String email);

	/**
	 * Gets signin key.
	 *
	 * @return the signin key
	 */
	SecretKey getSigninKey();
	

}
