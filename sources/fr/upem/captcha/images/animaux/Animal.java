package fr.upem.captcha.images.animaux;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upem.captcha.images.Images;


public class Animal implements Images {
	
	protected List<URL> imagesURL; 
	protected int numberURL;
	
	public Animal() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package
		imagesURL.add(Animal.class.getResource("animal1.jpg"));
		numberURL++;
		imagesURL.add(Animal.class.getResource("animal2.jpg"));
		numberURL++;
		imagesURL.add(Animal.class.getResource("animal3.jpg"));
		numberURL++;
		imagesURL.add(Animal.class.getResource("animal4.jpg"));
		numberURL++;
	}

	@Override
	public List<URL> getPhotos() {
		return this.imagesURL;
	}

	@Override
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

	@Override
	public URL getRandomPhotoURL() {
		Random rand = new Random();
		int value = rand.nextInt(numberURL);
		return this.imagesURL.get(value);
	}

	@Override
	public boolean isPhotoCorrect(URL url) {
		if(this.imagesURL.contains(url)) {
			return true;
		}
		else {
			return false;
		}
	}


}
