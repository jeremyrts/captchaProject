package fr.upem.captcha;

import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import fr.upem.captcha.ui.MainUI;
//import fr.upem.captcha.Themes;



public class Main {
	
	private static int numberGoodImages;
	
	private static int level = 1;
	private static boolean isEnded = false;
	private static MainUI ui;
	private static JFrame frame;
	
	

	private static Themes themes; // On peut le toString
	private static String currentThemeName;
	private static String currentThemeDir; // Le directory dans lequel on se trouve en ce moment.
	private static String nextThemeDir; // Le prochain directory
	private static String nextThemeName;
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		// Affichage de l'UI
		int res = 2;
		ui = new MainUI();
		frame = new JFrame ("Captcha");
		currentThemeDir ="fr.upem.captcha.images";
		
		while (!isEnded && level < 4) {
			play(res,ui);
		}
	}	
	
	private static void play(int res, MainUI ui) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		loadLevel();
		while (res == 2) {
			res = checkState();
		}
		switch(res) {
		case 0:
			level++;
			res = 2;
			break;
			
		case 1:
			isEnded = true;
			
		default: 
			break;
		}
	}
	
	private static void setTheme(int level) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		switch(level) {
		case 1:
			System.out.println("Niveau 1");
			Random rand = new Random();
			int value = rand.nextInt(2);
			switch(value) {
			case 0: 
				themes = Themes.Animal;
				break;
			case 1:
				themes = Themes.Panneau;
				break;
			default:
				break;
			}
			currentThemeName = themes.toString();
			System.out.println(currentThemeName);
			currentThemeDir = getFullThemePackageName(themes.toString());
			nextThemeDir = getFullThemePackageName(themes.getLevel1());
			nextThemeName = themes.getLevel1();
			break;
	
		case 2:
			System.out.println("Niveau 2");
			currentThemeName = nextThemeName;
			currentThemeDir = getFullThemePackageName(nextThemeName.toString());
			nextThemeDir = getFullThemePackageName(themes.getLevel2());
			nextThemeName = themes.getLevel2();
			break;
			
		case 3:
			System.out.println("Niveau 3");
			currentThemeName = nextThemeName;
			currentThemeDir = getFullThemePackageName(nextThemeName.toString());
			nextThemeDir = getFullThemePackageName(themes.getLevel3());
			nextThemeName = themes.getLevel3();
			break;
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
	
	private static String getFullThemePackageName(String themeElement) {
		return currentThemeDir.concat("."+themeElement.toLowerCase());
	}
	
	private static void loadLevel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		frame.getContentPane().removeAll();
		frame.repaint();
		setTheme(level);
		numberGoodImages = getNumberOfGoodImage();
		ui.run(currentThemeName, currentThemeDir, nextThemeName, nextThemeDir, frame, numberGoodImages);
	}
}

