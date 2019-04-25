package fr.upem.captcha.images.panneaux;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.Main;
import fr.upem.captcha.images.Images;

public class Panneau implements Images {
	
	private List<URL> imagesURL;
	
	public Panneau(){
		imagesURL = new ArrayList<>();
		// A l'initialisation, on ajoute toutes les URL d'images du package
		imagesURL.add(Panneau.class.getResource("panneau 70.jpg"));
	}

	@Override
	public List<URL> getPhotos() {
		return this.imagesURL;
	}

	@Override
	public List<URL> getRandomPhotosURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getRandomPhotoURL() {
		// TODO Auto-generated method stub
		return null;
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
