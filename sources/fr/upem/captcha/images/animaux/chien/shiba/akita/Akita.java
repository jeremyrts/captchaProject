package fr.upem.captcha.images.animaux.chien.shiba.akita;

import java.util.ArrayList;

import fr.upem.captcha.images.animaux.chien.shiba.Shiba;

public class Akita extends Shiba {

	public Akita() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package et on incrémente le nombre
		imagesURL.add(Akita.class.getResource("akita1.jpg"));
		numberURL++;
		imagesURL.add(Akita.class.getResource("akita2.jpg"));
		numberURL++;
		imagesURL.add(Akita.class.getResource("akita3.jpg"));
		numberURL++;
		imagesURL.add(Akita.class.getResource("akita4.jpg"));
		numberURL++;
		imagesURL.add(Akita.class.getResource("akita5.jpg"));
		numberURL++;
		imagesURL.add(Akita.class.getResource("akita6.jpg"));
		numberURL++;
	}

}
