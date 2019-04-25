package fr.upem.captcha;

import java.awt.Color;
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
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import fr.upem.captcha.images.panneaux.Panneau;
import fr.upem.captcha.images.voitures.Voiture;

public class Main {
	
	private static ArrayList<URL> selectedImages = new ArrayList<URL>();
	
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Capcha"); // Création de la fenêtre principale
		
		GridLayout layout = createLayout();  // Création d'un layout de type Grille avec 4 lignes et 3 colonnes
		
		frame.setLayout(layout);  // affection du layout dans la fenêtre.
		frame.setSize(1024, 768); // définition de la taille
		frame.setResizable(false);  // On définit la fenêtre comme non redimensionnable
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'on ferme la fenêtre on quitte le programme.
		 
		
		JButton okButton = createOkButton();
		
		Panneau panneaux = new Panneau();
		Voiture voitures = new Voiture();
		
		//System.out.println(Panneau.class.getPackage().getName());
		
		//System.out.println(getTheme());
		
		final String theme = getTheme();
		
		frame.add(createLabelImage(panneaux.getPhotos().get(0)));
		frame.add(createLabelImage(voitures.getPhotos().get(0)));
		frame.add(createLabelImage(voitures.getPhotos().get(1)));

		

		
		//frame.add(createLabelImage("images/panneaux/panneau 70.jpg")); //ajouter des composants à la fenêtre
		//frame.add(createLabelImage("le havre.jpg"));
		//frame.add(createLabelImage("panneau 70.jpg"));
		//frame.add(createLabelImage("panneaubleu-carre.jpeg"));
		//frame.add(createLabelImage("parking.jpg"));
		//frame.add(createLabelImage("route panneau.jpg"));
		//frame.add(createLabelImage("tour eiffel.jpg"));
		//frame.add(createLabelImage("ville espace verts.jpg"));
		//frame.add(createLabelImage("voie pieton.jpg"));
		
		
		
		frame.add(new JTextArea("Cliquez n'importe où ... juste pour tester l'interface !"));
		
		
		frame.add(okButton);
		
		frame.setVisible(true);
		
		//Panneau panneau = new Panneau();
		
		//System.out.println(panneau.getPhotos());
		
		//URL urlToCheck = Main.class.getResource("images/Voitures/parking.jpg");
		//System.out.println("L'url à checker est : "+urlToCheck);
		
		//System.out.println(panneau.isPhotoCorrect(urlToCheck));
	}
	
	
	/* Définir le thème du captcha */
	
	private static String getTheme() {
		Random rand = new Random();
		int value = rand.nextInt(2);
		String theme = new String();
		
		switch(value) {
		case 0: 
			theme = "Voiture";
			break;
		case 1:
			theme = "Panneau";
			break;
		default:
			break;
		}
		return theme;
	}
	
	
	private static GridLayout createLayout(){
		return new GridLayout(4,3);
	}
	
	private static JButton createOkButton(){
		return new JButton(new AbstractAction("Vérifier") { //ajouter l'action du bouton
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements
					
					@Override
					public void run() { // c'est un runnable
						System.out.println("J'ai cliqué sur Ok");
					}
				});
			}
		});
	}
	
	private static JLabel createLabelImage(URL urlImage) throws IOException{
		
		//final URL url = Main.class.getResource(imageLocation); //Aller chercher les images !! IMPORTANT 
		
		System.out.println(urlImage); 
		
		BufferedImage img = ImageIO.read(urlImage); //lire l'image
		Image sImage = img.getScaledInstance(1024/3,768/4, Image.SCALE_SMOOTH); //redimentionner l'image
		
		final JLabel label = new JLabel(new ImageIcon(sImage)); // créer le composant pour ajouter l'image dans la fenêtre
		
		label.addMouseListener(new MouseListener() { //Ajouter le listener d'évenement de souris
			private boolean isSelected = false;
			
			
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
						if(!isSelected){
							label.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
							isSelected = true;
							selectedImages.add(urlImage);
						}
						else {
							label.setBorder(BorderFactory.createEmptyBorder());
							isSelected = false;
							selectedImages.remove(urlImage);
						}
						
					}
				});
				
			}
		});
		
		return label;
	}
}
