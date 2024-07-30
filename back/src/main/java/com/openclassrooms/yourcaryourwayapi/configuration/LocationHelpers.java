package com.openclassrooms.yourcaryourwayapi.configuration;

import java.io.File;

/**
 * Classe qui répertorie tous les chemins et répertoires nécessaires à l'application.
 */
public class LocationHelpers {
	
	public final static String ROOT_DIR = System.getProperty("user.dir");
	public final static String ROOT_URI = "/api";
	public final static String STATIC_URI =  LocationHelpers.ROOT_URI+"/static";
	public final static String STATIC_URI_RECURSIVE =  LocationHelpers.ROOT_URI+"/static/**";
	public final static String AUTH_URI = LocationHelpers.ROOT_URI+"/auth";
	public final static String REGISTER_URI = LocationHelpers.AUTH_URI+"/register";
	public final static String LOGIN_URI = LocationHelpers.AUTH_URI+"/login";
	public final static String ARTICLES_URI = LocationHelpers.ROOT_URI+"/article/**";


	
	public static String GeneratePath(String folder_names) {
		String folder_tree_path = folder_names.replace(",", File.separator);
		String fullPath = ROOT_DIR+File.separator+folder_tree_path;
		return fullPath;
	}
	
	
	
	
	

}
