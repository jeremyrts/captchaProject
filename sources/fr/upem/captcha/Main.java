package fr.upem.captcha;

import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import fr.upem.captcha.images.Theme;
import fr.upem.captcha.ui.MainUI;

public class Main {
	
	private static int numberGoodImages;
	
	private static int level = 1;
	private static boolean isEnded = false;
	private static MainUI ui;
	private static JFrame frame;

	private static String theme;
	private static Theme themeObject;
	private static Theme themeGlobal;
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		// Affichage de l'UI
		int res = 2;
		ui = new MainUI();
		frame = new JFrame ("Captcha");
		
		loadLevel();
		
		while (!isEnded && level < 4) {
			play(res,ui);
		}
	}	
	
	private static void play(int res, MainUI ui) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		System.out.println("etners play");
		while (res == 2) {
			res = checkState();
		}
		switch(res) {
		case 0:
			themeGlobal = (Theme)Class.forName(themeObject.getClass().getName()).newInstance();
			themeObject = (Theme)Class.forName(themeGlobal.getRandomSubTheme(themeGlobal.getClass())).newInstance();
			System.out.println("theme global" + themeGlobal.getClass().getSimpleName());
			System.out.println("theme object" + themeObject.getClass().getSimpleName());
			res = 2;
			break;
			
		case 1:
			isEnded = true;
			
		default: 
			break;
		}
	}
	
	private static int getNumberOfGoodImage() {
		Random rand = new Random();
		int value = rand.nextInt(4);
		while(value==0) {
			value = rand.nextInt(4);
		}
		return value;
	}
	
	private static int checkState () {
		return ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
	}
	
	private static void loadLevel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		frame.getContentPane().removeAll();
		frame.repaint();
		theme = Theme.init();
		
		themeGlobal = (Theme)Class.forName(theme).newInstance();
		themeObject = (Theme)Class.forName(themeGlobal.getRandomSubTheme(themeGlobal.getClass())).newInstance();
		System.out.println("theme global" + themeGlobal.getClass().getSimpleName());
		numberGoodImages = getNumberOfGoodImage();
		ui.run(themeObject, themeGlobal, frame, numberGoodImages);
	}
}

