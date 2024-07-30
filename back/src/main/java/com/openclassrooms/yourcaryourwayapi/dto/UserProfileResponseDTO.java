package com.openclassrooms.yourcaryourwayapi.dto;

import java.util.List;

/**
 * The type User profile response dto.
 */
public class UserProfileResponseDTO {
    private UserLoggedDTO user;


    /**
     * Gets user.
     *
     * @return the user
     */
    public UserLoggedDTO getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserLoggedDTO user) {
        this.user = user;
    }



    /**
     * Instantiates a new User profile response dto.
     */
    public UserProfileResponseDTO(){}


}
