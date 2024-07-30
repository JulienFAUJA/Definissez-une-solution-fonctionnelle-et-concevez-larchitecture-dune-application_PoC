package com.openclassrooms.yourcaryourwayapi.services;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.openclassrooms.yourcaryourwayapi.repositories.UserRepository;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {


	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return  userRepository.findByEmail(username).map(user -> new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList()))
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé..."));
	}



}