package fr.upem.captcha;

import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import fr.upem.captcha.images.Theme;
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
	 * 
	 * @see JFrame
	 */
	private static MainUI ui;
	/**
	 * One frame is affected to the application. It is linked to the UI.
	 * 
	 * @see MainUI
	 */
	private static JFrame frame;

	private static String theme;
	private static Theme themeObject;
	private static Theme themeGlobal;
	
	
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
		
		loadLevel();
		
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
	
	
	/**
	 * Get information whether the captcha was completed or not.
	 * 
	 * @return 0 if it's failed, 1 if it's won, 2 either way.
	 * 
	 */
	
	private static int checkState () {
		return ui.checkResult((JTextArea)frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 2));
	}
	
	private static void loadLevel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
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
		theme = Theme.init();
		
		themeGlobal = (Theme)Class.forName(theme).newInstance();
		themeObject = (Theme)Class.forName(themeGlobal.getRandomSubTheme(themeGlobal.getClass())).newInstance();
		System.out.println("theme global" + themeGlobal.getClass().getSimpleName());
		numberGoodImages = getNumberOfGoodImage();
		ui.run(themeObject, themeGlobal, frame, numberGoodImages);
	}
}

