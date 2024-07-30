package com.openclassrooms.yourcaryourwayapi.services;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.openclassrooms.yourcaryourwayapi.dto.*;
import com.openclassrooms.yourcaryourwayapi.repositories.ChatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.yourcaryourwayapi.models.UserModel;
import com.openclassrooms.yourcaryourwayapi.repositories.UserRepository;
import com.openclassrooms.yourcaryourwayapi.services.Interfaces.IAuthService;


/**
 * The type Auth service.
 */
@Service
public class AuthService implements IAuthService{
	
	 @Autowired
	 private UserRepository userRepository;



	@Autowired
	private ChatRepository chatRepository;
	
	 @Autowired
	 private ModelMapper modelMapper;
	
	 private final PasswordEncoder passwordEncoder;
	 private final JWTokenService jwtService;
	 private final AuthenticationManager authenticationManager;

	/**
	 * Instantiates a new Auth service.
	 *
	 * @param passwordEncoder       the password encoder
	 * @param jwtService            the jwt service
	 * @param authenticationManager the authentication manager
	 */
	public AuthService(PasswordEncoder passwordEncoder, JWTokenService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
	 }




	public UserProfileResponseDTO me(Principal user){
		 UserLoggedDTO userLoggedDTO = modelMapper.map(this.userRepository.findByEmail(user.getName()), UserLoggedDTO.class);
		 // Récupérer tous les theme_id des abonnements de l'utilisateur


		 UserProfileResponseDTO userProfile = new UserProfileResponseDTO();
		 userProfile.setUser(userLoggedDTO);


		 return userProfile;

     }

	public TokenDTO register(UserRegisterDTO request) {
		System.out.println("userRegisterDTO:"+request.toString());
		UserModel user = new UserModel(
				request.getEmail(),
				request.getFirstname(),
				request.getLastname(),
				passwordEncoder.encode(request.getPassword()),
				request.getDate_naissance(),
				Timestamp.from(Instant.now())
				);
		String jwt = jwtService.generateToken(request.getEmail());
		// vérifie si existe déjà
		if(userRepository.findByEmail(request.getEmail()).isPresent()) {
			return null;
		}
		else {
			userRepository.save(user);
		}
		TokenDTO token = new TokenDTO(jwt);
		return token;
	}
    


     public String authenticating(UserLoginDTO request) {
    	    try {
				Optional<UserModel> userOptional = userRepository.findByEmail(request.getEmail());
				if (userOptional.isPresent()) {
					UserModel user = userOptional.get();
					if  (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
						return jwtService.generateToken(user.getEmail());
					} else {
						return null;
					}

				} else {
					/*
					//System.out.println(jwt);
					UserModel user = new UserModel(
							request.getEmail(), "Julien", "Faujanet", passwordEncoder.encode(request.getPassword()),
							Timestamp.from(Instant.now()),
							Timestamp.from(Instant.now().minus(Duration.ofDays((long)(Math.random() * 365 * (65 - 18) + 365 * 18))))


					);
					String jwt = jwtService.generateToken(request.getEmail());
					// vérifie si existe déjà


					if(userRepository.findByEmail(request.getEmail()).isPresent()) {
						return null;
					}
					else {
						userRepository.save(user);
					}
					return jwtService.generateToken(request.getEmail());

					 */
				}


    	    }
			catch (AuthenticationException e) {
				e.printStackTrace(); // Pour obtenir plus de détails sur l'erreur
				return null;
			}
			return null;
     }
}
