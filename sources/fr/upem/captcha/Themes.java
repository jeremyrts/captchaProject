package fr.upem.captcha;


public enum Themes { 
	Animal("Chien", "Shiba", "Akita"),
	Panneau("PanneauRouge", "PanneauRond", "SensInterdit");
	
	private String level1;
	private String level2;
	private String level3;
	
	private Themes(String level1, String level2, String level3) {
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
	}
	

	public String getLevel1() {
		return level1;
	}
	
	public String getLevel2() {
		return level2;
	}
	
	public String getLevel3() {
		return level3;
	}

}
