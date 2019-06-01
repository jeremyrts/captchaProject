package fr.upem.captcha;

import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import fr.upem.captcha.ui.MainUI;


/**
 * This is the main class of the application. All the process and logic are handled here.
 * The captcha keeps running as long as the boolean field isEnded stays false and that there are still
 * some level to go through.
 * 
 * @author Jeremy Ratsimandresy
 * @author Julian Bruxelle
 */

public class Main {
	/**
	 * This field allows the application to know at anytime in which level it is at any moment.
	 * This way, we can monitor how the user is doing and moving onto the next level if needed.
	 */
	private static int level;
	/**
	 * This field is what make sure the captcha keeps going as long as there is still some contents to display
	 */
	private static boolean isEnded;
	/**
	 * One UI is affected to the application. It is linked to a frame.
	 * @see JFrame
	 */
	private static MainUI ui;
	/**
	 * One frame is affected to the application. It is linked to the UI.
	 * @see MainUI
	 */
	private static JFrame frame;
	
	
	/**
	 * This is the Enumeration that contains the themes vocabulary and wording.
	 */
	private static Themes themes;
	/**
	 * This is the name of the class used as the global theme.
	 */
	private static String currentThemeName;
	/**
	 * This is the package name of the class used as the global theme.
	 */
	private static String currentThemeDir;
	/**
	 * This is the name of the class used as elements that are looked for.
	 */
	private static String nextThemeDir;
	/**
	 * This is the package name of the class used as elements that are looked for..
	 */
	private static String nextThemeName;
	
	
	/**
	 * This is the entry point of the application
	 * 
	 * @param args	any argument, here it is not mandatory
 	 * 
	 */
	
	public static void main(String[] args) {
		int res = 2;
		ui = new MainUI();
		frame = new JFrame ("Captcha");
		level = 1; // First launch
		currentThemeDir ="fr.upem.captcha.images";
		isEnded = false;
		
		while (!isEnded && level < 4) {
			play(res);
		}
	}	
	
	/**
	 * Handle the application progression by loading the levels as the user goes through difficulties. 
	 * 
	 * @param res	this integer let the application know at what state the user is, 0 if he failed, 1 if he won, 2 either way.
	 */
	
	private static void play(int res) {
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
	
	/**
	 * Select and define the theme the application is going to run with.
	 * On the first level, the theme is chosen randomly, while for the other level, the themes are related to the initial
	 * package the first theme was chosen. 
	 * 
	 * @param level	gives the information on what level we currently are and therefore what should be the next theme.
	 * 
	 */
	
	private static void setTheme(int level){
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
	
	/**
	 * Define how many good answers the application will display, between 1 and 4.
	 * 
	 * @return value	number of good answers needed to be clicked.
	 * 
	 */
	
	private static int getNumberOfGoodImage() {
		Random rand = new Random();
		int value = rand.nextInt(4);
		while(value==0) {
			value = rand.nextInt(4);
		}
		return value;
	}
	
	
	/**
	 * Get information whether the captcha was completed or not.
	 * 
	 * @return 0 if it's failed, 1 if it's won, 2 either way.
	 * 
	 */
	
	private static int checkState () {
			return ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
	}
	
	/**
	 * Dynamically construct the package where the class of the theme is located.
	 * 
	 * @param themeElement	single name of a class without its package context.
	 * 
	 * @return the full package name containing the global class used as theme.	
	 * 
	 */
	
	private static String getFullThemePackageName(String themeElement) {
		return currentThemeDir.concat("."+themeElement.toLowerCase());
	}
	
	/**
	 * Load a new level displaying the correct images regarding the theme and the number of good answers.
	 * First, frame is cleaned then the ui is called to be displayed.
	 * 
	 */
	
	private static void loadLevel(){
		frame.getContentPane().removeAll();
		frame.repaint();
		setTheme(level);
		int numberGoodImages = getNumberOfGoodImage();
		ui.run(currentThemeName, currentThemeDir, nextThemeName, nextThemeDir, frame, numberGoodImages);
	}
}

