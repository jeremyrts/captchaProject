package fr.upem.captcha.ui;

import fr.upem.captcha.images.Images;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


/**
 * This is the class handling the UI and everything related to it. It has also multiple static field to keep a coherence
 * between the methods.
 * 
 * @author Jeremy Ratsimandresy
 * @author Julian Bruxelle
 */

public class MainUI {
	
	/**
	 * Keeps tracks of the images selected by the user as potential answers.
	 */
	private static ArrayList<URL> selectedImages = new ArrayList<URL>();
	/**
	 * Define the number of images to use in the application. The current layout is well made for 9 images, but less is possible, assuming the number of good
	 * images is modified as well.
	 */
	private static int numberOfImages = 9;
	/**
	 * Object representing the theme elements to chose as good answers.
	 */
	private static Images themeObject;
	/**
	 * Object representing the theme elements that are not good answers
	 */
	private static Images themeGlobal;
	
	/**
	 * Display a new frame related to the state of the captcha where in : level, themes, etc.
	 * This is the main UI method as it's called everytime we start the application or we update its state.
	 * 
	 * @param currentThemeName	string representing the class type of the global theme (Animal or Panneau on level 1 for instance).
	 * @param currentThemeDir	string representing the package of the class type of the global theme.
	 * @param nextThemeName	string representing the class type of the subtheme, corresponding to the good answers (Shiba or PanneauRouge on level 1 for instance).
	 * @param nextThemeDir	string representing the package of the class type of the subtheme.
	 * @param frame	the current frame used in the application.
	 * @param numberGoodImages	number of images from the subtheme to display, corresponding to the number of good answers.
	 */
	
	public void run( String currentThemeName, String currentThemeDir, String nextThemeName, String nextThemeDir, JFrame frame, int numberGoodImages)   {
				
		GridLayout layout = createLayout();  // Création d'un layout de type Grille avec 4 lignes et 3 colonnes
		
		frame.setLayout(layout);  // affection du layout dans la fenêtre.
		frame.setSize(1024, 768); // définition de la taille
		frame.setResizable(false);  // On définit la fenêtre comme non redimensionnable
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'on ferme la fenêtre on quitte le programme.
		JButton okButton = createOkButton(frame, nextThemeDir.concat("."+nextThemeName), numberGoodImages);
	
		try {
			themeGlobal = (Images) Class.forName(currentThemeDir.concat("."+currentThemeName)).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			themeObject = (Images) Class.forName(nextThemeDir.concat("."+nextThemeName)).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
		System.out.println("Layout principal crée");	
		ArrayList<URL> imagesToDisplay = new ArrayList();
		imagesToDisplay = getImagesToDisplay(themeGlobal, themeObject, imagesToDisplay, numberGoodImages);
		Collections.shuffle(imagesToDisplay);
		for (URL url : imagesToDisplay) {
			try {
				frame.add(createLabelImage(url, frame));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		frame.add(new JTextArea("Cliquez sur : "+nextThemeName));
		frame.add(new JTextArea("Résultat : "));
		frame.add(okButton);
		frame.setVisible(true);	
	}
	
	/**
	 * Check the value of the panel with the result information and return an integer to give the answer.
	 * 
	 * @param textResult	the panel JTextArea where the result is displayed.
	 * 
	 * @return 0 if the result is false ("Résultat : FAUX !"), 1 if the result is correct ("Résultat : VRAI !"), 2 if no result yet ("Résultat : ")
	 */
	
	public int checkResult(JTextArea textResult) {
			if(textResult.getText().equals("Résultat : FAUX !")) {
			return 0;
		}
		else if(textResult.getText().equals("Résultat : VRAI !")) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	/**
	 * Add images to the frame that we want to display.
	 * 
	 * @param imagesToDisplay	the ArrayList containing all the URL of the images we want to display.
	 * @param frame the frame we want to display the images on.
	 */
	
	public void addImages(ArrayList<URL> imagesToDisplay, JFrame frame) {
		for (URL url : imagesToDisplay) {
			try {
				frame.add(createLabelImage(url, frame));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Get dynamically the URLs of the images we want to display for the application. It first collect images from the good answer class then from the  global theme.
	 * 
	 * @param themeGlobal	Images instance representing the global theme class. 
	 * @param themeObject	Images instance representing the specific theme class.
	 * @param imagesToDisplay	the ArrayList to fill up.
	 * @param numberGoodImages	number of images from the subtheme to display, corresponding to the number of good answers.
	 * 
	 * @return the ArrayList filled up with the URLs of the images to display.
	 */
	
	private static ArrayList<URL> getImagesToDisplay(Images themeGlobal, Images themeObject, ArrayList<URL> imagesToDisplay, int numberGoodImages){
		for(int i=0; i<numberGoodImages; i++) {
			URL tempURL = themeObject.getRandomPhotoURL();
			while (imagesToDisplay.contains(tempURL) || tempURL == null) {
				tempURL = themeObject.getRandomPhotoURL();
			}
			imagesToDisplay.add(tempURL);
		}
		
		for(int i=0; i<numberOfImages-numberGoodImages; i++) {
			URL tempURL = themeGlobal.getRandomPhotoURL();
			while (imagesToDisplay.contains(tempURL) || tempURL == null) {
				tempURL = themeGlobal.getRandomPhotoURL();
			}
			imagesToDisplay.add(tempURL);
		}
		return imagesToDisplay;
	}
	
	/**
	 * Verify if the images selected are the good answers. It verify if the number of images selected matches the number of good images displayed
	 * and then compare whether the URL of each image belonged to the URLs of the class subtheme (good answers).
	 * 
	 * @param theme	full class name of the good answer theme (subtheme). 
	 * @param numberGoodImages	number of good images that was supposed to be selected.
	 * 
	 * @return true if both condition (number correct and URLs matching) are filled up, false otherwise.
	 */
	
	private static boolean checkImage(String theme, int numberGoodImages)  {
		Images classTheme;
		boolean verif = true;
		try {
			classTheme = (Images) Class.forName(theme).newInstance();
			
			if(selectedImages.size() != numberGoodImages) { // On vérifie que le nombre d'image selectionné est bon
				return false;
			}
			else {
				Iterator<URL> iter = selectedImages.iterator();
				if(!iter.hasNext()) {
					verif = false;
				}
				while(verif && iter.hasNext()) {
					if(!((Images) classTheme).isPhotoCorrect((URL)iter.next())) {
						verif = false;
					}
				}
				return verif;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return verif;
		
	}
	
	/**
	 * Create the layout used to display the images. By default it's a 4x3 grid.
	 * 
	 * @return the GridLayout layout used to display the images.
	 */
	
	private static GridLayout createLayout(){
		return new GridLayout(4,3);
	}
	
	/**
	 * Create the button to validate our choice.
	 * 
	 * @param frame	the current frame used, to give context information.
	 * @param theme	the subtheme name to give another context information.
	 * @param numberGoodImages	number of good images supposed to be selected.
	 * 
	 * @return the button created
	 */
	
	private static JButton createOkButton(JFrame frame, String theme, int numberGoodImages){
		return new JButton(new AbstractAction("Vérifier") { //ajouter l'action du bouton
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements
					
					int layoutElement = frame.getContentPane().getComponentCount(); // Nombre d'élément contenu dans le layout
					
					@Override
					public void run() { // c'est un runnable
						if(!checkImage(theme, numberGoodImages)) {
							((JTextArea) frame.getContentPane().getComponent(layoutElement - 2)).append("FAUX !");
						}
						else {
							((JTextArea) frame.getContentPane().getComponent(layoutElement - 2)).append("VRAI !");
						}
						
						// Récupérer les index des images selectionnées et les déselectionner
						selectedImages.clear();
					}
					
				});
			}
		});
	}
	
	/**
	 * Create a new label for an image to be displayed on and give a listener to the mouse events, so that it can interact with it.
	 * On click, the image will get black border if it's not already selected, and lose its black border if it's already selected.
	 * 
	 * @param urlImage	the image location to get the images.
	 * @param frame	the current frame used, to give context information.
	 * 
	 * @throws IOException if the url is invalid.
	 * 
	 * @return 	 the new label created to display on the application.
	 */
	
	private static JLabel createLabelImage(URL urlImage, JFrame frame) throws IOException{
		
		int layoutElement = frame.getContentPane().getComponentCount(); // Nombre d'élément contenu dans le layout
		BufferedImage img = ImageIO.read(urlImage); //lire l'image
		Image sImage = img.getScaledInstance(1024/3,768/4, Image.SCALE_SMOOTH); //redimentionner l'image
		
		final JLabel label = new JLabel(new ImageIcon(sImage)); // créer le composant pour ajouter l'image dans la fenêtre
		
		label.addMouseListener(new MouseListener() { //Ajouter le listener d'évenement de souris
			
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
		
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) { //ce qui nous intéresse c'est lorsqu'on clique sur une image, il y a donc des choses à faire ici
				EventQueue.invokeLater(new Runnable() { 
					
					@Override
					public void run() {
						if(!selectedImages.contains(urlImage)){
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
							selectedImages.add(urlImage);
						}
						else {
							label.setBorder(BorderFactory.createEmptyBorder());
							selectedImages.remove(urlImage);
						}
					}
					
				});				
			}
		});
		return label;
	}
	

}

