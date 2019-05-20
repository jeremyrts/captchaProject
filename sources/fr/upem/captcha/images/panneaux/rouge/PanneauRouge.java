package fr.upem.captcha.images.panneaux.rouge;

import java.util.ArrayList;

import fr.upem.captcha.images.panneaux.Panneau;

public class PanneauRouge extends Panneau {

	public PanneauRouge() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package et on incrémente le nombre
		imagesURL.add(PanneauRouge.class.getResource("panneauRouge1.jpg"));
		numberURL++;
		imagesURL.add(PanneauRouge.class.getResource("panneauRouge2.jpg"));
		numberURL++;
		imagesURL.add(PanneauRouge.class.getResource("panneauRouge3.jpg"));
		numberURL++;
		imagesURL.add(PanneauRouge.class.getResource("panneauRouge4.jpg"));
		numberURL++;
		imagesURL.add(PanneauRouge.class.getResource("panneauRouge5.jpg"));
		numberURL++;
		imagesURL.add(PanneauRouge.class.getResource("panneauRouge6.jpg"));
		numberURL++;
	}

}
