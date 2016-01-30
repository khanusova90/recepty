package cz.ppro.recepty.domain;

import cz.ppro.recepty.utils.EnumTranslator;

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

	private String name;

	private Category(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return EnumTranslator.getMessageKey(this);
	}

}
