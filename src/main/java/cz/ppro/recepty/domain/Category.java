package cz.ppro.recepty.domain;

/**
 * Druh jidla
 * 
 * <p>
 * <b>BREAKFAST</b> - snidane <br/>
 * <b>LUNCH</b> - obed <br/>
 * <b>DINNER</b> - vecere <br/>
 * <b>DESERT</b> - dezert
 * </p>
 * 
 * @author Katerina Hanusova
 *
 */

public enum Category {

	BREAKFAST("BREAKFAST"), LUNCH("LUNCH"), DINNER("DINNER"), DESERT("DESERT");

	private String meal;

	private Category(String meal) {
		this.meal = meal;
	}

	@Override
	public String toString() {
		return meal;
	}

}
