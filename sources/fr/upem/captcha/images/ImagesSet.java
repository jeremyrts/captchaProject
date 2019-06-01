package fr.upem.captcha.images;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Abstract class that represents an object containing all the images related to him.
 * 
 * @author Jeremy Ratsimandresy
 * @author Julian Bruxelle
 * 
 */

public abstract class ImagesSet implements Images {
	/**
	 * List containing the URL of the images related to the Images instance.
	 */
	protected List<URL> imagesURL; 
	/**
	 * Number of elements contained in the List
	 */
	protected int numberURL;
	
	/**
	 * Constructor of the instance. It collect all the images placed inside it's directory and add the URLs in its imagesURL field.
	 * It then increase the numberURL field to keep track of the number of URL.
	 */
	
	public ImagesSet() {
		this.imagesURL = this.getPhotosNames()
				.stream()
				.map(name -> this.getClass().getResource(name))
				.collect(Collectors.toList());
		this.numberURL = this.imagesURL.size();
	}
	
	/**
	 * Fetch and build the absolute path of an image location
	 * 
	 * @return the string representation of the path.
	 */
	
	public String getPhotosLocation() {
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(System.getProperty("user.dir"));
		stringbuilder.append(System.getProperty("file.separator"));
		stringbuilder.append("classes/"); // Cette ligne ne fonctionne pas dans un jar mais est nécessaire pour executer dans Eclipse
		stringbuilder.append(this.getClass().getPackage().getName().replace(".", System.getProperty("file.separator")));
		return stringbuilder.toString();
	}
	
	/**
	 * Collect all the images name inside the current directory.
	 * 
	 * @return a List containing all filenames of the images. 
	 */
	
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
	
	/**
	 * Getter on the imagesURL field, to access all the images URL.
	 * 
	 * @return the field imagesURL.
	 */
	
	public List<URL> getPhotos() {
		return this.imagesURL;
	}
	
	/**
	 * Get randomly multiple URLs of the images inside its directory, inside a List object.
	 * 
	 * @param photosNumber	number of random images we are looking for.
	 * 
	 * @return the List object with random URLs inside.
	 */

	public List<URL> getRandomPhotosURL(int photosNumber) {
		List<URL> randomLink = new ArrayList<URL>();

		// Quelles images vont s'afficher
		for (int i = 0; i < photosNumber; i++) {
			randomLink.add(this.getRandomPhotoURL());
		}
		
		return randomLink;
	}
	
	/**
	 * Get randomly one URL out of of the images inside its directory.
	 * 
	 * @return the URL randomly selected.
	 */

	public URL getRandomPhotoURL() {
		Random rand = new Random();
		int value = rand.nextInt(numberURL);
		while (value == 0) {
			value = rand.nextInt(numberURL);
		}
		return this.imagesURL.get(value);
	}
	
	/**
	 * Check if an URL belongs to the imagesURL field and so to the directory.
	 * 
	 * @param url	the url to check.
	 * 
	 * @return true if the images is in the directory, false otherwise.
	 */

	public boolean isPhotoCorrect(URL url) {
		return this.imagesURL.contains(url);
	}
	
	/**
	 * Getter on the current package name
	 * 
	 * @return the string representation of the package name.
	 */
	
	public String getPackageName() {
		return this.getClass().getPackage().getName();
	}

}
