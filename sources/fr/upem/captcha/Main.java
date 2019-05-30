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
	private static String theme;
	private static int level = 1;
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		// Affichage de l'UI
		int res = 2;
		MainUI ui = new MainUI();
		JFrame frame = new JFrame ("Captcha");
		
		// Premier niveau par défaut
		System.out.println("Niveau : "+level);
		theme = getTheme(level, "none");
		numberGoodImages = getNumberOfGoodImage();
		String currentTheme = theme;
		try {
			ui.run(level, theme, frame, numberGoodImages);
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

		while (res == 2){
			//System.out.println(frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
			res = ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
		}
		if(res == 0) {
			System.out.println("C'est faux, il faut passer à la difficulté supérieure");
			level++;
			System.out.println("Niveau "+level);
			
			theme = getTheme(level, currentTheme);
			currentTheme = theme;
			System.out.println(theme);
			frame.getContentPane().removeAll();
			frame.repaint();
			res = 2;
			ui.run(level, theme, frame, getNumberOfGoodImage());
			while (res == 2){
				res = ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
			}
			if(res == 0) {
				System.out.println("C'est faux, il faut passer à la difficulté supérieure");
				level++;
				System.out.println("Niveau "+level);
				theme = getTheme(level, currentTheme);
				currentTheme = theme;
				System.out.println(theme);
				frame.getContentPane().removeAll();
				frame.repaint();
				res = 2;
				ui.run(level, theme, frame, getNumberOfGoodImage());
				while (res == 2){
					res = ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
				}
				if(res == 0) {
					frame.add(new JTextArea("Acces refusé"));
				}
				else if(res == 1){
					System.out.println("C'est vrai, tu peux accéder au contenu");
				}
			}
			else if(res == 1){
				System.out.println("C'est vrai, tu peux accéder au contenu");
				
			}
		}
		
		else if(res == 1){
			System.out.println("C'est vrai, tu peux accéder au contenu");
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
}
