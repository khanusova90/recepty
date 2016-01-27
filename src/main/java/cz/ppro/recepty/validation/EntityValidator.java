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
			throw new IllegalArgumentException("Nen� vypln�n recept, ke kter�mu se m� ingredience ulo�it!");
		}
		if (ri.getIngredient() == null) {
			throw new IllegalArgumentException("Nen� vypln�na ingredience, kter� se m� receptu p�i�adit!");
		}
		if (ri.getAmount() == null || ri.getUnit() == null) {
			throw new IllegalArgumentException("Nen� vypln�no mno�stv� ingredience, kter� se m� p�i�adit k receptu!");
		}
	}

	public static void checkRecipe(Recipe r) {
		if (r.getRecipeIngredients() == null || r.getRecipeIngredients().isEmpty()) {
			throw new IllegalArgumentException("K receptu nebyly p�id�ny ��dn� ingredience");
		}
		if (r.getPreparationProcess() == null) {
			throw new IllegalArgumentException("Nen� vypln�n postup");
		}
	}

}
