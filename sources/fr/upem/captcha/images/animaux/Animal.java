package fr.upem.captcha.images.animaux;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upem.captcha.images.Images;


public class Animal extends Images {
	
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



}
