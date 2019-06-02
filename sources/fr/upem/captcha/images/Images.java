package fr.upem.captcha.images;

import java.net.URL;
import java.util.List;

/**
 * Interface defining what Images object are able to do. Its primary use is implemented in the class Theme.
 * 
 * @see Theme
 * 
 * @author Jeremy Ratsimandresy
 * @author Julian Bruxelle
 * 
 */

public interface Images {
	
	/**
	 * Getter on the imagesURL field, to access all the images URL.
	 * 
	 * @return the field imagesURL.
	 */
	
	public List<URL> getPhotos();
	
	/**
	 * Get randomly multiple URLs of the images inside its directory, inside a List object.
	 * 
	 * @param photosNumber	number of random images we are looking for.
	 * 
	 * @return the List object with random URLs inside.
	 */
	
	public List<URL> getRandomPhotosURL(int photosNumber);
	
	/**
	 * Get randomly one URL out of of the images inside its directory.
	 * 
	 * @return the URL randomly selected.
	 */
	
	public URL getRandomPhotoURL();
	
	/**
	 * Check if an URL belongs to the imagesURL field and so to the directory.
	 * 
	 * @param url	the url to check.
	 * 
	 * @return true if the images is in the directory, false otherwise.
	 */
	
	public boolean isPhotoCorrect(URL url);

}
