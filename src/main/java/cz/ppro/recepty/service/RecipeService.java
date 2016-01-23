package cz.ppro.recepty.service;

import cz.ppro.recepty.domain.Recipe;

public interface RecipeService {

	/**
	 * Vypocita nove hodnoceni receptu
	 * 
	 * @param recipe
	 *            Recept, ktery uzivatel hodnoti (instance tridy {@link Recipe})
	 * @param rating
	 *            Hodnoceni zadane uzivatelem
	 */
	public void setRating(Recipe recipe, int rating);

}