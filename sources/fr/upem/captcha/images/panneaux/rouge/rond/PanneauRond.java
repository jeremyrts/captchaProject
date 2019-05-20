package fr.upem.captcha.images.panneaux.rouge.rond;

import java.util.ArrayList;

import fr.upem.captcha.images.panneaux.rouge.PanneauRouge;

public class PanneauRond extends PanneauRouge {

	public PanneauRond() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package et on incrémente le nombre
		imagesURL.add(PanneauRond.class.getResource("panneauRond1.jpg"));
		numberURL++;
		imagesURL.add(PanneauRond.class.getResource("panneauRond2.jpg"));
		numberURL++;
		imagesURL.add(PanneauRond.class.getResource("panneauRond3.jpg"));
		numberURL++;
		imagesURL.add(PanneauRond.class.getResource("panneauRond4.jpg"));
		numberURL++;
		imagesURL.add(PanneauRond.class.getResource("panneauRond5.jpg"));
		numberURL++;
		imagesURL.add(PanneauRond.class.getResource("panneauRond6.jpg"));
		numberURL++;
	}

}
