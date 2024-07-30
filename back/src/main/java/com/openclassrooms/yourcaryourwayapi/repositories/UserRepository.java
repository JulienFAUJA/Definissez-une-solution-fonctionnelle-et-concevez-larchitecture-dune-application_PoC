package com.openclassrooms.yourcaryourwayapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.yourcaryourwayapi.models.UserModel;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository  extends CrudRepository<UserModel, Integer> {
	/**
	 * Find by email optional.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<UserModel> findByEmail(String email);


	 Optional<UserModel> findById(Integer id);

}
