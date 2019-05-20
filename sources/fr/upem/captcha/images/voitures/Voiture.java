package fr.upem.captcha.images.voitures;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upem.captcha.images.Images;

public class Voiture extends Images {
	
	
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
}
