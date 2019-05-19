package fr.upem.captcha;

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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import fr.upem.captcha.images.Images;
import fr.upem.captcha.images.panneaux.Panneau;
import fr.upem.captcha.images.voitures.Voiture;
import fr.upem.captcha.ui.MainUI;

public class Main {
	
	private static ArrayList<URL> selectedImages = new ArrayList<URL>();
	private static int numberOfImages = 3;
	private static int numberGoodImages;
	private Object themeObject;
	
	public static void main(String[] args) throws IOException{
		// Affichage de l'UI
		MainUI ui = new MainUI();
		JFrame frame = new JFrame ("Captcha");
		
		// Premier niveau par défaut
		final String theme = getTheme(1, "none");
		int res = 2;
		try {
			ui.run(theme, frame);
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Vérification : est ce que le captcha est bon ? 
		
		while (res == 2){
			res = ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
		}
		
		// NON 
		// On update la fenêtre avec de nouvelles images 

		if(res == 0) {
			System.out.println("C'est faux, il faut passer à la difficulté supérieure");
			System.out.println(getTheme(2, theme));
		}
		
		// OUI
		// Message de validation, fin du programme
		
		else if(res == 1){
			System.out.println("C'est vrai, tu peux accéder au contenu");
			
		}
		
			
		// Redefinition du thème 
		// theme = getTheme()
		
		// ui.update(theme)
		
		
	}
	
	private static String getTheme(int level, String currentTheme) {
		Random rand = new Random();
		int value = rand.nextInt(2);
		String theme = new String();
		
		if(currentTheme.equals("none")) {
			switch(value) {
			case 0: 
				theme = "fr.upem.captcha.images.animaux.Animal";
				break;
			case 1:
				theme = "fr.upem.captcha.images.panneaux.Panneau";
				break;
			default:
				theme = "fr.upem.captcha.images.panneaux.Panneau";
			}
		}
		
		if(level == 2) {
			switch(currentTheme) {
			case "fr.upem.captcha.images.animaux.Animal":
				theme = "fr.upem.captcha.images.animaux.chien";
				break;
			
			case "fr.upem.captcha.images.panneaux.Panneau":
				theme = "fr.upem.captcha.images.panneaux.rouge";
				break;
			}
		}
		return theme;
		
	}
}
