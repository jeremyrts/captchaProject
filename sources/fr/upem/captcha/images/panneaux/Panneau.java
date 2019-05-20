package fr.upem.captcha.images.panneaux;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upem.captcha.Main;
import fr.upem.captcha.images.Images;

public class Panneau extends Images {
	
	
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
		imagesURL.add(Panneau.class.getResource("panneau5.jpg"));
		numberURL++;
		imagesURL.add(Panneau.class.getResource("panneau6.jpg"));
		numberURL++;
	}


}
