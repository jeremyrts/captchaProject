package fr.upem.captcha.images;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This is the class that handles themes dynamic locating
 * 
 * @author Jeremy Ratsimandresy
 * @author Julian Bruxelle
 */

public abstract class Theme implements Images {

	/**
	 * The list of the images URL used to display theme images
	 */
	private List<URL> imagesURL; 
	
	/**
	 * The number of URL
	 */
	private int URLsNumber;	

	public Theme() {
		this.imagesURL = this.getPhotosNames()
				.stream()
				.map(name -> this.getClass().getResource(name))
				.collect(Collectors.toList());
		this.URLsNumber = this.imagesURL.size();
	}
	
	/**
	 * Initializes the captcha theme by picking a random theme in fr.umlv.captcha.images subpackages
	 * 
	 * @return String the name of the picked theme
	 */
	public static String init() {
		return Theme.getRandomSubTheme(Theme.class);
	}

	/**
	 * Gets the URL of the package in which a class is located
	 * 
	 * @param className the name of the class to locate
	 * @return String the URL of the package in which the parameter class is located
	 */
	public static String getPackageURL(Class className) {

		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(System.getProperty("user.dir"));
		stringbuilder.append("/");

		// If launched using eclipse (not added in .jar)
		if (new File (stringbuilder.toString() + "/sources").exists()) {
			stringbuilder.append("sources");
			stringbuilder.append("/");
		}
		stringbuilder.append(className.getPackage().getName().replace(".", "/"));
		return stringbuilder.toString();
	}

	/**
	 * Gets the content of the package in which a class is located
	 * 
	 * Uses the getPackageURL method of Theme
	 * 
	 * @param className the name of the class of which you want to get the content
	 * @return The content (ArrayList<File>) of the package in which the parameter class is located
	 */
	public static ArrayList<File> getPackageContent(Class className) {
		File packageFolder = new File(Theme.getPackageURL(className));
		return new ArrayList<File>(Arrays.asList(packageFolder.listFiles()));
	}

	/**
	 * Gets the subPackages (here subThemes) of the current package (ie. class package)
	 * @param className the name of the class of which you want to get subpackages
	 * @return The subpackages (ArrayList<File>) of the package in which the parameter class is located
	 */
	public static ArrayList<File> getSubPackages(Class className) {
		ArrayList<File> files = Theme.getPackageContent(className);
		files.removeIf(file -> !file.isDirectory());
		return files;
	}

	/**
	 * Gets the name of the class which represents the image theme of the package 
	 * 
	 * @param subPackage the File corresponding to the subpackage of which you want to get the  first class name (String format)
	 * @return the class name of the subPackage first class (which is supposed to be the only one)
	 * @throws FileNotFoundException if no class is found
	 */
	public static String getSubPackageClassName(File subPackage) throws FileNotFoundException {

		ArrayList<File> content = new ArrayList<File>(Arrays.asList(subPackage.listFiles()));
		System.out.println(subPackage.getAbsolutePath());

		// If using IDE like Eclipse, else (.jar)
		if (subPackage.getAbsolutePath().contains("sources"))
			content.removeIf(file -> !file.getName().endsWith(".java"));
		else 
			content.removeIf(file -> !file.getName().endsWith(".class"));

		if (content.isEmpty()) throw new FileNotFoundException("Can't find the corresponding java file for " + subPackage.getName());
		String filePath = content.get(0).getPath();
		return filePath.substring(filePath.lastIndexOf("fr"), filePath.lastIndexOf('.')).replace("/", ".");	
	}

	/** 
	 *  Creates an ArrayList from the String[] returned by the list method of the file created with the package URL.
	 *  Removes all non .jpg files
	 * 
	 *  @return ArrayList<String> : a list of photos names for the current theme
	 */
	public ArrayList<String> getPhotosNames() {
		ArrayList<File> files = new ArrayList<File>(Theme.getPackageContent(this.getClass()));
		ArrayList<String> names = new ArrayList<String>(files.stream().map(file -> file.getName()).collect(Collectors.toList()));
		names.removeIf(name -> !name.endsWith(".jpg"));
		return names;
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
		int value = rand.nextInt(URLsNumber);
		while (value == 0) {
			value = rand.nextInt(URLsNumber);
		}
		return this.imagesURL.get(value);
	}

	/**
	 * Gets randomly a subTheme for the current theme within current theme class package subpackages
	 * @param classname the name of the current Theme class
	 * @return the name (String) of the selected subTheme
	 */
	public static String getRandomSubTheme(Class classname) {
		Random rand = new Random();
		int value = 0;	

		try {
			value = Math.abs(rand.nextInt()) % Theme.getSubPackages(classname).size();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}

		try {
			return Theme.getSubPackageClassName(Theme.getSubPackages(classname).get(value));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean isPhotoCorrect(URL url) {
		return this.imagesURL.contains(url);
	}

}
