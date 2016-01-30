package cz.ppro.recepty.utils;

/**
 * Trida pro prekladani ciselniku
 * 
 * @author Katerina Hanusova
 *
 */
public class EnumTranslator {

	public static String getMessageKey(Enum<?> e) {
		return e.getClass().getSimpleName() + "." + e.name();
	}

}
