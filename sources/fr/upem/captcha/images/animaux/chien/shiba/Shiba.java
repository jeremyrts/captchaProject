package fr.upem.captcha.images.animaux.chien.shiba;

import java.util.ArrayList;

import fr.upem.captcha.images.animaux.chien.Chien;

public class Shiba extends Chien {

	public Shiba() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package et on incrémente le nombre
		imagesURL.add(Shiba.class.getResource("shiba1.jpg"));
		numberURL++;
		imagesURL.add(Shiba.class.getResource("shiba2.jpg"));
		numberURL++;
		imagesURL.add(Shiba.class.getResource("shiba3.jpg"));
		numberURL++;
		imagesURL.add(Shiba.class.getResource("shiba4.jpg"));
		numberURL++;
		imagesURL.add(Shiba.class.getResource("shiba5.jpg"));
		numberURL++;
		imagesURL.add(Shiba.class.getResource("shiba6.jpg"));
		numberURL++;
	}

}
