package cz.ppro.recepty.service;

import java.util.List;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Category;
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Photo;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;

/**
 * Interface pro praci s recepty
 * 
 * @author Katerina Hanusova
 *
 */
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
	 * Vyhleda recepty podle zadane kategorie
	 * 
	 * @param category
	 *            String hodnota {@link Category}
	 * @return List of {@link Recipe}
	 */
	public List<Recipe> showRecipesByCategory(String category);

	/**
	 * Najde recepty podle zadanych ingredienci
	 * 
	 * @param ingredients
	 *            Seznam ingredienci, ktere chceme mit v receptu
	 * @return Seznam receptu, ktere obsahuji vsechny zadane ingredience. Pokud
	 *         nenajde zadny recept, vraci prazdny seznam
	 */
	public List<Recipe> findRecipesByAllIngredients(List<Ingredient> ingredients);

	/**
	 * Najde recepty dle zadanych ingredienci
	 * 
	 * @param ingredients
	 *            Seznam ingredienci, ktere chceme mit v receptu
	 * @return Seznam receptu, ktere lze uvarit pouze ze zadanych ingredienci
	 *         (vsechny ingredience receptu se nachazi v seznamu). Pokud zadny
	 *         recept nenajde, vraci prazdny seznam
	 */
	public List<Recipe> findRecipesByIngredients(List<Ingredient> ingredients);

	/**
	 * Vytvori recept
	 *
	 * @param recipe
	 *            {@link Recipe} s vyplnenymi hodnotami. Musi mit vyplnen postup
	 * @param ingredients
	 *            Seznam ingredienci k receptu
	 * @param user
	 *            Autor receptu
	 */
	public void createRecipe(Recipe recipe, AppUser user);

	/**
	 * Vrati list receptu podle jejich nejlepsiho hodnoceni
	 *
	 */
	public List<Recipe> getRecipesSortedByRating();

	/**
	 * @return Vsechny instance {@link Recipe} ulozene v DB
	 */
	public List<Recipe> getAllRecipes();

	/**
	 * Najde vsechny ingredience zadaneho receptu
	 * 
	 * @param recipe
	 * @return Seznam {@link RecipeIngredient}
	 */
	public List<RecipeIngredient> getAllIngredients(Recipe recipe);

	public void deleteRecipe(Recipe recipe);

	/**
	 * Najde recepty podle autora
	 * 
	 * @param user
	 *            {@link AppUser}, ktery recept ulozil
	 * @return
	 */
	public List<Recipe> getAllRecipesByUser(AppUser user);

	/**
	 * Najde vsechny fotky k receptu
	 * 
	 * @param recipe
	 * @return
	 */
	public List<Photo> getPhotosByRecipe(Recipe recipe);

	/**
	 * Najde recept podle nazvu
	 * 
	 * @param name
	 *            Nazev receptu
	 * @return Seznam receptu, jejichz nazev obsahuje pozadovany nazev
	 */
	public List<Recipe> findRecipesByName(String name);

	public void deleteRecipeIngredient(Long id);

	public Recipe findById(Long id);

}