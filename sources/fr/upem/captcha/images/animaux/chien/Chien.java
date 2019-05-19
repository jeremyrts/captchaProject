package fr.upem.captcha.images.animaux.chien;

import java.util.ArrayList;

import fr.upem.captcha.images.animaux.Animal;

public class Chien extends Animal {

	public Chien() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package et on incrémente le nombre
		imagesURL.add(Chien.class.getResource("chien1.jpg"));
		numberURL++;
		imagesURL.add(Chien.class.getResource("chien2.jpg"));
		numberURL++;
		imagesURL.add(Chien.class.getResource("chien3.jpg"));
		numberURL++;
		imagesURL.add(Chien.class.getResource("chien4.jpg"));
		numberURL++;
	}

}
