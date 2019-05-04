package fr.upem.captcha.images.voitures;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upem.captcha.images.Images;

public class Voiture implements Images {
	
	private List<URL> imagesURL; 
	private int numberURL;
	
	public Voiture() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package
		imagesURL.add(Voiture.class.getResource("parking.jpg"));
		numberURL++;
		imagesURL.add(Voiture.class.getResource("voitures2.jpg"));
		numberURL++;
		imagesURL.add(Voiture.class.getResource("voitures3.jpg"));
		numberURL++;
		imagesURL.add(Voiture.class.getResource("voitures4.jpg"));
		numberURL++;
	}

	@Override
	public List<URL> getPhotos() {
		return this.imagesURL;
	}

	@Override
	public List<URL> getRandomPhotosURL() {
		List<URL> randomLink = new ArrayList();
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
