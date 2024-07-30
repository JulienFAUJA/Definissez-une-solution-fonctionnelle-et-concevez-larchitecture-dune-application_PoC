package com.openclassrooms.yourcaryourwayapi.controllers;

import java.security.Principal;

import com.openclassrooms.yourcaryourwayapi.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.yourcaryourwayapi.services.Interfaces.IAuthService;


import jakarta.validation.Valid;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	


@Autowired
private IAuthService authService;




	/**
	 * Login response method.
	 *
	 * @param userLoginDTO the user login dto
	 * @return the token
	 */
	@PostMapping(value ="/login", consumes={"application/json"})
    public ResponseEntity<?> login(@Valid @RequestBody(required = true) UserLoginDTO userLoginDTO) {
		System.out.println("USER:"+userLoginDTO.toString());
		String token = authService.authenticating(userLoginDTO);
		if (token == null) {
			String errorResponse= "Unauthorized: Erreur d'dentifiants...";
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		else {
			return ResponseEntity.ok(new TokenDTO(token));
		} 
    }


	/**
	 * Post register method.
	 *
	 * @param userRegisterDTO the user register dto
	 * @return the token
	 */
	@PostMapping(value ="/register")
	@ResponseBody
	public ResponseEntity<?> postRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		TokenDTO token =  authService.register(userRegisterDTO);
		if (token == null) {
			String errorResponse= "Erreur dans le formulaire...";
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		else {
			return ResponseEntity.ok(token);
		}
	}




	/**
	 * Gets profile page.
	 *
	 * @param user the user
	 * @return the user data
	 */
	@GetMapping(value ="/profile")
	public ResponseEntity<?> getMe(Principal user) {
		return ResponseEntity.status(HttpStatus.OK).body(this.authService.me(user));
	}

}
