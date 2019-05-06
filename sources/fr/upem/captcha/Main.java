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
	
	private static String theme = "Voiture";
	
	public static void main(String[] args) throws IOException{
		// Affichage de l'UI
		MainUI ui = new MainUI();
		ui.run(theme);
		
		// Vérification : est ce que le captcha est bon ? 
			// OUI
			// Message de validation, fin du programme
		
			/* NON 
				On update la fenêtre avec de nouvelles images 
			*/
		
		// Redefinition du thème 
		// theme = getTheme()
		
		// ui.update(theme)
		
		
	}
	
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
}
