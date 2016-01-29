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

}