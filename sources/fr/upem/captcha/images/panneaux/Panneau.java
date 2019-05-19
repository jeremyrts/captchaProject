package fr.upem.captcha.images.panneaux;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upem.captcha.Main;
import fr.upem.captcha.images.Images;

public class Panneau implements Images {
	
	 protected List<URL> imagesURL;
	 protected int numberURL; 
	
	public Panneau(){
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package et on incrémente le nombre
		imagesURL.add(Panneau.class.getResource("panneau1.jpg"));
		numberURL++;
		imagesURL.add(Panneau.class.getResource("panneau2.jpg"));
		numberURL++;
		imagesURL.add(Panneau.class.getResource("panneau3.jpg"));
		numberURL++;
		imagesURL.add(Panneau.class.getResource("panneau4.jpg"));
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
