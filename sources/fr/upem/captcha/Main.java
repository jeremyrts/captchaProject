package fr.upem.captcha;

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

	/**
	 * The name of the current theme
	 */
	private static String theme;
	
	/**
	 * The theme of the images the user has to pick
	 * 
	 * @see Theme
	 */
	private static Theme themeObject;
	
	/**
	 * The global theme of the images
	 * 
	 * @see Theme
	 */
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
		isEnded = false;
		
		
		Main.init();
		
		while (!isEnded) {
			play(res);
		}
	}	
	
	
	/**
	 * Handle the application initialization by picking a random theme. 
	 */	
	private static void init() {
		theme = Theme.init();
		
		try {
			themeGlobal = (Theme)Class.forName(theme).newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		try {
			themeObject = (Theme)Class.forName(themeGlobal.getRandomSubTheme(themeGlobal.getClass())).newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handle the application progression by loading the levels as the user goes through difficulties. 
	 * When user fails, the method loads a harder level by going deeper through files architecture
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
			try {
				themeGlobal = (Theme)Class.forName(themeObject.getClass().getName()).newInstance();
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
			
			if (Theme.getSubPackages(themeGlobal.getClass()).size() == 0) {
				isEnded = true;
			}
			else {
				try {
					themeObject = (Theme)Class.forName(themeGlobal.getRandomSubTheme(themeGlobal.getClass())).newInstance();				
				} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}
				res = 2;
			}
			break;
			
		case 1:
			isEnded = true;
			
		default: 
			break;
		}
	}
	
	/**
	 * Randomly selects a number of 'good images' that the user has to pick to success
	 * @return a random number of images that has to be picked within all the images
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
	 * Load a new level displaying the correct images regarding the theme and the number of good answers.
	 * First, frame is cleaned then the ui is called to be displayed.
	 * 
	 */
	
	private static void loadLevel(){
		frame.getContentPane().removeAll();
		frame.repaint();
		int numberGoodImages = getNumberOfGoodImage();
		ui.run(themeObject, themeGlobal, frame, numberGoodImages);
	}
}

