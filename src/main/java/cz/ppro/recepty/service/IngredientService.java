package cz.ppro.recepty.service;

import java.util.List;

import cz.ppro.recepty.domain.Ingredient;

public interface IngredientService {

	/**
	 * Najde ingredience podle nazvu.
	 * 
	 * @param name
	 *            Nazev ingredience. Nemusi byt zadany cely, staci cast
	 * @return Seznam {@link Ingredient} odpovidajici zadanemu nazvu
	 */
	public List<Ingredient> findIngredientByName(String name);

	/**
	 * @return Vsechny {@link Ingredient} ulozene v databazi
	 */
	public List<Ingredient> getAll();

	/**
	 * Ze zadanych nazvu ingredienci vytvori seznam {@link Ingredient}
	 * 
	 * @param ingredientsStr
	 *            Seznam nazvu ingredienci zadany jako String, kde jednotlive
	 *            ingredience jsou oddeleny carkou
	 * @return Seznam ingredienci. Pokud nic nenajde, vraci <code>null</code>
	 */
	public List<Ingredient> splitIngredients(String ingredientsStr);

}