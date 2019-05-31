package fr.upem.captcha.images;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class ImagesSet implements Images {
	
	protected List<URL> imagesURL; 
	protected int numberURL;
	
	public ImagesSet() {
		this.imagesURL = this.getPhotosNames()
				.stream()
				.map(name -> this.getClass().getResource(name))
				.collect(Collectors.toList());
		this.numberURL = this.imagesURL.size();
	}
	
	public String getPhotosLocation() {
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(System.getProperty("user.dir"));
		stringbuilder.append(System.getProperty("file.separator"));
		//stringbuilder.append("classes/"); // Cette ligne ne fonctionne pas dans un jar mais est nécessaire pour executer dans Eclipse
		stringbuilder.append(this.getClass().getPackage().getName().replace(".", System.getProperty("file.separator")));
		return stringbuilder.toString();
	}
	
	public List<String> getPhotosNames() {
		File folder = new File(this.getPhotosLocation());
		ArrayList<String> filesNames = new ArrayList<String>(Arrays.asList(folder.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jpg");
			}
		})));
		return filesNames;
	}
	
	public List<URL> getPhotos() {
		return this.imagesURL;
	}

	public List<URL> getRandomPhotosURL(int photosNumber) {
		List<URL> randomLink = new ArrayList<URL>();

		// Quelles images vont s'afficher
		for (int i = 0; i < photosNumber; i++) {
			randomLink.add(this.getRandomPhotoURL());
		}
		
		return randomLink;
	}

	public URL getRandomPhotoURL() {
		Random rand = new Random();
		int value = rand.nextInt(numberURL);
		while (value == 0) {
			value = rand.nextInt(numberURL);
		}
		return this.imagesURL.get(value);
	}

	public boolean isPhotoCorrect(URL url) {
		return this.imagesURL.contains(url);
	}
	
	public String getPackageName() {
		return this.getClass().getPackage().getName();
	}

}
