package fr.upem.captcha.ui;


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

import fr.upem.captcha.images.Images;
import fr.upem.captcha.images.animaux.Animal;
import fr.upem.captcha.images.panneaux.Panneau;
import fr.upem.captcha.images.voitures.Voiture;

public class MainUI {
	
	private static ArrayList<URL> selectedImages = new ArrayList<URL>();
	private static int numberOfImages = 4;
	private static int numberGoodImages;
	private Object themeObject;
	
	public void run(String theme, JFrame frame) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
				
		GridLayout layout = createLayout();  // Création d'un layout de type Grille avec 4 lignes et 3 colonnes
		
		frame.setLayout(layout);  // affection du layout dans la fenêtre.
		frame.setSize(1024, 768); // définition de la taille
		frame.setResizable(false);  // On définit la fenêtre comme non redimensionnable
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'on ferme la fenêtre on quitte le programme.
		
		JButton okButton = createOkButton(frame, theme);
		
		// Classe de toutes les images (peu importe le thème)
		
		Panneau panneaux = new Panneau();
		Animal animaux = new Animal();
		Voiture voitures = new Voiture();
		
		// Classe particulière concernant le thème
		Object themeObject = Class.forName(theme).newInstance();
		
		
				
		System.out.println("Layout principal crée");
		
		/* Etape 1 : récupérer aléatoirement les images nécessaires */
		
		List<URL> imagesToDisplay = new ArrayList();
		String themeLitteral;
		 
		// Variable pour définir le nombre d'image correcte, à utiliser pour la vérification (1 et 4)
		// Pour l'instant on choisit deux images du thème
		
		numberGoodImages = 2;
		
		System.out.println("Le thème est : "+themeObject.getClass().getPackage().getName());
		
		
		if((panneaux.getClass().getPackage().getName() == themeObject.getClass().getPackage().getName())) {
			themeLitteral = "Panneau";
			for(int i=0; i<numberGoodImages; i++) {
				URL tempURL = panneaux.getRandomPhotoURL();
				while (imagesToDisplay.contains(tempURL)) {
					tempURL = panneaux.getRandomPhotoURL();
				}
				imagesToDisplay.add(tempURL);
			}
			for(int i=0; i<numberOfImages-numberGoodImages; i++) {
				
				URL tempURL = animaux.getRandomPhotoURL();
				while (imagesToDisplay.contains(tempURL)) {
					tempURL = animaux.getRandomPhotoURL();
				}
				imagesToDisplay.add(tempURL);

			}
			
		}
		else {
			themeLitteral = "Animal";
			for(int i=0; i<numberGoodImages; i++) {
				
				URL tempURL = animaux.getRandomPhotoURL();
				while (imagesToDisplay.contains(tempURL)) {
					tempURL = animaux.getRandomPhotoURL();
				}
				imagesToDisplay.add(tempURL);
			}
			for(int i=0; i<numberOfImages-numberGoodImages; i++) {
				URL tempURL = panneaux.getRandomPhotoURL();
				while (imagesToDisplay.contains(tempURL)) {
					tempURL = panneaux.getRandomPhotoURL();
				}
				imagesToDisplay.add(tempURL);
			}
		}
		
		
		// Boucle pour ajouter les images au viewer
		
		for (URL url : imagesToDisplay) {
			frame.add(createLabelImage(url, frame));
		}
		
		frame.add(new JTextArea("Cliquez sur les images du thème : "+themeLitteral));
		frame.add(new JTextArea("Résultat : "));
		frame.add(okButton);
		frame.setVisible(true);	
		
	}
	
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
	
	
	public void update() {
		
	}
	
	/* Défini le thème du captcha */
	
	
	
	
	private static boolean checkImage(String theme) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Object classTheme = Class.forName(theme).newInstance();
		boolean verif = true;
		
		/* On vérifie que le nombre d'image selectionné est bon */
		if(selectedImages.size() != numberGoodImages) {
			return false;
		}
		else {
			Iterator<URL> iter = selectedImages.iterator();
			if(!iter.hasNext()) {
				verif = false;
			}
			while(verif && iter.hasNext()) {
				if(!((Images) classTheme).isPhotoCorrect((URL)iter.next())) {
					verif = false; // L'image n'est pas dans le package du thème
				}
			}
			return verif;
		}
	}
	
	private static GridLayout createLayout(){
		return new GridLayout(4,3);
	}
	
	private static JButton createOkButton(JFrame frame, String theme){
		return new JButton(new AbstractAction("Vérifier") { //ajouter l'action du bouton
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements
					
					int layoutElement = frame.getContentPane().getComponentCount(); // Nombre d'élément contenu dans le layout
					
					@Override
					public void run() { // c'est un runnable
						System.out.println("J'ai cliqué sur Ok");
						try {
							if(!checkImage(theme)) {
								System.out.println("FAUX");
								((JTextArea) frame.getContentPane().getComponent(layoutElement - 2)).append("FAUX !");
							}
							else {
								System.out.println("VALIDE");
								((JTextArea) frame.getContentPane().getComponent(layoutElement - 2)).append("VRAI !");
							}
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						
						// Récupérer les index des images selectionnées et les déselectionner
						
						for (int i = 0; i<layoutElement - 3; i++) {
							((JLabel)frame.getContentPane().getComponent(i)).setBorder(null);
							
						}	
						selectedImages.clear();
					}
					
				});
			}
		});
	}
	
	private static JLabel createLabelImage(URL urlImage, JFrame frame) throws IOException{
		
		//final URL url = Main.class.getResource(imageLocation); //Aller chercher les images !! IMPORTANT 
		int layoutElement = frame.getContentPane().getComponentCount(); // Nombre d'élément contenu dans le layout
		System.out.println(urlImage); 
		
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
							System.out.println("Clic sur l'image : on l'ajoute ");
						}
						else {
							label.setBorder(BorderFactory.createEmptyBorder());
							selectedImages.remove(urlImage);
							System.out.println("Clic sur l'image : on l'enlève");
						}
						((JTextArea) frame.getContentPane().getComponent(numberOfImages + 1)).setText("Résultat : ");
					}
					
				});				
			}
		});
		return label;
	}
}

