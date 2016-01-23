package cz.ppro.recepty.service.impl;

import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.service.RecipeService;

public class RecipeServiceImpl implements RecipeService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cz.ppro.recepty.service.RecipeService#setRating(cz.ppro.recepty.
	 * domain.Recipe, int)
	 */
	@Override
	public void setRating(Recipe recipe, int rating) {
		int rateCount = recipe.getRateCount();
		if (rateCount == 0) {
			recipe.setRating(rating);
		} else {
			float totalRating = recipe.getRating() * rateCount;
			float newRating = (totalRating + rating) / (rateCount + 1);
			recipe.setRating(newRating);
		}

		rateCount++;
		recipe.setRateCount(rateCount);

		// TODO: dao.save(recipe);

	}

}
