package cz.ppro.recepty.service;

import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;

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

	/**
	 * Vytvori novou ingredienci
	 * 
	 * @param ingredientName
	 *            Nazev ingredience
	 * @return Nova instance tridy {@link Ingredient}
	 */
	public Ingredient createIngredient(String ingredientName);

	/**
	 * Prida novou ingredienci k receptu
	 * 
	 * @param recipeIngredient
	 *            Instance {@link RecipeIngredient} pripravena k ulozeni do DB.
	 *            Nese informace o mnozstvi ingredience, ktera ma byt prirazena
	 *            k receptu
	 */
	public void addIngredientToRecipe(RecipeIngredient recipeIngredient);

	/**
	 * Zobrazi recept podle kategorie
	 *
	 * @param category
	 *            Vrati recepty podle kategorie
	 */
	public String showRecipesByCategory(String category);

	/**
	 * Zobrazi recept podle ingredienci
	 *
	 * @param ingredients
	 *            Vrati recepty podle ingredience
	 */
	public String showRecipesByIngredients(String[] ingredients);

	/**
	 * Vytvori recept
	 *
	 * @param recipe
	 *            {@link Recipe} s vyplnenymi hodnotami. Musi mit vyplnen postup
	 *            a ingredience
	 */
	public void createRecipe(Recipe recipe);

	/**
	 * Smaze vybrany recept z databaze
	 * 
	 * @param recipe
	 */
	public void deleteRecipe(Recipe recipe);

}