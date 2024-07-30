package com.openclassrooms.yourcaryourwayapi.services.Interfaces;

import java.security.Principal;

import com.openclassrooms.yourcaryourwayapi.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface Auth service.
 */
public interface IAuthService {

	/**
	 * Me user profile response dto.
	 *
	 * @param user the user
	 * @return the user profile response dto
	 */
	UserProfileResponseDTO me(Principal user);


	/**
	 * Authenticating string.
	 *
	 * @param request the request
	 * @return the string
	 */
	String authenticating(UserLoginDTO request);


	TokenDTO register(UserRegisterDTO request);

}
