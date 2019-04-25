package fr.upem.captcha.images;

import java.net.URL;
import java.util.List;

public interface Images {
	
	List<URL> getPhotos();
	
	List<URL> getRandomPhotosURL();
	
	URL getRandomPhotoURL();
	
	boolean isPhotoCorrect(URL url);

}
