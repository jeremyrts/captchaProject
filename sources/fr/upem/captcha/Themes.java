package fr.upem.captcha;

/**
 * This enumeration used to have litteral reference to the themes. This is the only files to modify if we wish to
 * changer the themes order, types, or number of level.
 * The elements all have a main title and between "()" refered to the different level.
 * 
 * @author Jeremy Ratsimandresy
 * @author Julian Bruxelle
 */

public enum Themes { 
	Animal("Chien", "Shiba", "Akita"),
	Panneau("PanneauRouge", "PanneauRond", "SensInterdit");
	
	/**
	 * Field representing the first level of a theme
	 */
	private String level1;
	/**
	 * Field representing the second level of a theme
	 */
	private String level2;
	/**
	 * Field representing the third level of a theme
	 */
	private String level3;
	
	private Themes(String level1, String level2, String level3) {
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
	}
	
	/**
	 * Getter on the level1 field
	 * 
	 * @return level1 the string corresponding to the first level
	 */

	public String getLevel1() {
		return level1;
	}
	
	/**
	 * Getter on the level2 field
	 * 
	 * @return level2 the string corresponding to the second level
	 */
	
	public String getLevel2() {
		return level2;
	}
	
	/**
	 * Getter on the level3 field
	 * @return level3 the string corresponding to the third level
	 */
	
	public String getLevel3() {
		return level3;
	}

}
