package cz.ppro.recepty.validation;

import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;

/**
 * Trida slouzici ke kontrole validity ukladanych informaci
 * 
 * @author Katerina Hanusova
 *
 */
public final class EntityValidator {

	/**
	 * Zkontroluje, jestli se ingredience k receptu spravne uklada
	 *
	 * @param ri
	 */
	public static void checkRecipeIngredient(RecipeIngredient ri) {
		if (ri.getRecipe() == null) {
			throw new IllegalArgumentException("Není vyplnìn recept, ke kterému se má ingredience uložit!");
		}
		if (ri.getIngredient() == null) {
			throw new IllegalArgumentException("Není vyplnìna ingredience, která se má receptu pøiøadit!");
		}
		if (ri.getAmount() == null || ri.getUnit() == null) {
			throw new IllegalArgumentException("Není vyplnìno množství ingredience, která se má pøiøadit k receptu!");
		}
	}

	public static void checkRecipe(Recipe r) {
		if (r.getRecipeIngredients() == null || r.getRecipeIngredients().isEmpty()) {
			throw new IllegalArgumentException("K receptu nebyly pøidány žádné ingredience");
		}
		if (r.getPreparationProcess() == null) {
			throw new IllegalArgumentException("Není vyplnìn postup");
		}
	}

}
