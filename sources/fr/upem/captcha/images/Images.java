package fr.upem.captcha.images;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Images {
	
	protected List<URL> imagesURL; 
	protected int numberURL;
	
	public List<URL> getPhotos() {
		return this.imagesURL;
	}

	public List<URL> getRandomPhotosURL() {
		List<URL> randomLink = new ArrayList<URL>();
		Random rand = new Random();
		int number = 0;
		
		// Combien d'image vont s'afficher
		while (number == 0) {
			number = rand.nextInt(5);
		}
		// Quelles images vont s'afficher
		for (int i = 0; i < number; i++) {
			randomLink.add(this.getRandomPhotoURL());
		}
		
		return randomLink;
	}

	public URL getRandomPhotoURL() {
		Random rand = new Random();
		int value = rand.nextInt(numberURL);
		while (value == 0) {
			value = rand.nextInt(numberURL);
		}
		return this.imagesURL.get(value);
	}

	public boolean isPhotoCorrect(URL url) {
		if(this.imagesURL.contains(url)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getPackageName() {
		return this.getClass().getPackage().getName();
	}

}
