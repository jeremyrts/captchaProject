package fr.upem.captcha;

import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

import javax.swing.JTextArea;

import fr.upem.captcha.images.Theme;

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
		System.out.println("checkstate");
			return ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
	}
	
	private static void loadLevel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		frame.getContentPane().removeAll();
		frame.repaint();
		theme = Theme.init();
		numberGoodImages = getNumberOfGoodImage();
		ui.run(level, theme, frame, numberGoodImages);
		System.out.println("after ui run");
	}
}

