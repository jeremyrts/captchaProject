package fr.upem.captcha;

import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

import javax.swing.JTextArea;

import fr.upem.captcha.images.animaux.chien.Chien;
import fr.upem.captcha.images.animaux.chien.shiba.Shiba;
import fr.upem.captcha.images.animaux.chien.shiba.akita.Akita;
import fr.upem.captcha.images.panneaux.Panneau;
import fr.upem.captcha.images.panneaux.rouge.PanneauRouge;
import fr.upem.captcha.images.panneaux.rouge.rond.PanneauRond;
import fr.upem.captcha.images.panneaux.rouge.rond.sensInterdit.SensInterdit;
import fr.upem.captcha.images.voitures.Voiture;
import fr.upem.captcha.ui.MainUI;

public class Main {
	
	private static int numberGoodImages;
	private static String theme = "none";
	private static int level = 1;
	private static boolean isEnded = false;
	private static MainUI ui;
	private static JFrame frame;
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		// Affichage de l'UI
		int res = 2;
		ui = new MainUI();
		frame = new JFrame ("Captcha");
		
		while (!isEnded && level < 4) {
			loadLevel();
			while (res == 2) {
				res = checkState();
			}
			
			switch(res) {
			case 0:
				System.out.println("C'est faux, il faut passer à la difficulté supérieure");
				level++;
				System.out.println("Niveau "+level);
				res = 2;
				break;
				
			case 1:
				System.out.println("C'est vrai, tu peux accéder au contenu");
				isEnded = true;
				
			default: 
				break;
			}
		}
				
	}
	
	private static String getTheme(int level, String currentTheme) {
		Random rand = new Random();
		int value = rand.nextInt(2);
		String theme = new String();
		switch(level) {
		case 1:
			System.out.println("Niveau 1");
			switch(value) {
			case 0: 
				System.out.println("La value aléatoire chien : "+value);
				theme = Chien.class.getName();
				break;
			case 1:
				System.out.println("La value aléatoire panneauRouge : "+value);
				theme = PanneauRouge.class.getName();
				break;
			default:
				break;
			}
			break;
	
		case 2:
			System.out.println("Niveau 2");
			if(currentTheme.equals(Chien.class.getName())) {
				theme = Shiba.class.getName();
			}
			else {
				theme = PanneauRond.class.getName();
			}
			break;
			
		case 3:
			System.out.println("Niveau 3");
			if(currentTheme.equals(Shiba.class.getName())) {
				theme = Akita.class.getName();
			}
			else {
				theme = SensInterdit.class.getName();
			}
			break;
		default:
			break;
			
		}
		return theme;
		
	}
	
	private static int getNumberOfGoodImage() {
		Random rand = new Random();
		int value = rand.nextInt(2);
		System.out.println("Number good image : "+value);
		while(value==0) {
			value = rand.nextInt(2);
		}
		System.out.println("Number good image : "+value);
		return value;
	}
	
	private static int checkState () {
			return ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
	}
	
	private static void loadLevel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		frame.getContentPane().removeAll();
		frame.repaint();
		theme = getTheme(level, theme);
		numberGoodImages = getNumberOfGoodImage();
		ui.run(level, theme, frame, numberGoodImages);
	}
}

