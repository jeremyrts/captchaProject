package fr.upem.captcha.images;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Theme implements Images {

	private List<URL> imagesURL; 
	private int URLsNumber;	
	
	public Theme() {
		this.imagesURL = this.getPhotosNames()
				.stream()
				.map(name -> this.getClass().getResource(name))
				.collect(Collectors.toList());
		this.URLsNumber = this.imagesURL.size();
	}
	
	public static String init() {
		return Theme.getRandomSubTheme(Theme.class);
	}
	
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
	
	public static ArrayList<File> getPackageContent(Class className) {
		File packageFolder = new File(Theme.getPackageURL(className));
		return new ArrayList<File>(Arrays.asList(packageFolder.listFiles()));
	}
	
	public static ArrayList<File> getSubPackages(Class className) {
		ArrayList<File> files = Theme.getPackageContent(className);
		files.removeIf(file -> !file.isDirectory());
		return files;
	}
	
	public static String getSubPackageClassName(File subPackage) throws FileNotFoundException {
		
		ArrayList<File> content = new ArrayList<File>(Arrays.asList(subPackage.listFiles()));
		content.removeIf(file -> !file.getName().endsWith(".java"));
		
		if (content.isEmpty()) throw new FileNotFoundException("Can't find the corresponding java file for " + subPackage.getName());
		String filePath = content.get(0).getPath();
		return filePath.substring(filePath.lastIndexOf("fr"), filePath.lastIndexOf('.')).replace("/", ".");	
	}

	/*
	 * @return ArrayList<String> : a list of photos names in the concerned folder
	 *  
	 *  Creates an ArrayList from the String[] returned by the list method of the file created with the package URL.
	 *  FilenameFilter keeping only .jpg files
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
	
	public static String getRandomSubTheme(Class classname) {
		Random rand = new Random();
		System.out.println("subpack for " + classname.getSimpleName() + " " + Theme.getSubPackages(classname).size());
		int value = Math.abs(rand.nextInt()) % Theme.getSubPackages(classname).size();

		try {
			return Theme.getSubPackageClassName(Theme.getSubPackages(classname).get(value));
		} catch (FileNotFoundException e) {
			System.err.println(e.toString());
		}
		return null;
		
	}

	public boolean isPhotoCorrect(URL url) {
		return this.imagesURL.contains(url);
	}

}
