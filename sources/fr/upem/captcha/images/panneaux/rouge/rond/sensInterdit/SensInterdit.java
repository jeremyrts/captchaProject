package fr.upem.captcha.images.panneaux.rouge.rond.sensInterdit;

import java.util.ArrayList;

import fr.upem.captcha.images.panneaux.rouge.rond.PanneauRond;

public class SensInterdit extends PanneauRond {

	public SensInterdit() {
		imagesURL = new ArrayList<>();
		numberURL = 0;
		// A l'initialisation, on ajoute toutes les URL d'images du package et on incrémente le nombre
		imagesURL.add(SensInterdit.class.getResource("sensInterdit1.jpg"));
		numberURL++;
		imagesURL.add(SensInterdit.class.getResource("sensInterdit2.jpg"));
		numberURL++;
		imagesURL.add(SensInterdit.class.getResource("sensInterdit3.jpg"));
		numberURL++;
		imagesURL.add(SensInterdit.class.getResource("sensInterdit4.jpg"));
		numberURL++;
		imagesURL.add(SensInterdit.class.getResource("sensInterdit5.jpg"));
		numberURL++;
		imagesURL.add(SensInterdit.class.getResource("sensInterdit6.jpg"));
		numberURL++;
	}

}
