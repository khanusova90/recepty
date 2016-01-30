package cz.ppro.recepty.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.repository.IngredientRepository;
import cz.ppro.recepty.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> findIngredientByName(String name) {
		return ingredientRepository.findByIngredientNameLike(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> getAll() {
		return ingredientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> splitIngredients(String ingredientsStr) {
		List<Ingredient> ingredients = new ArrayList<>();

		String[] ingredientNames = ingredientsStr.split(",");
		for (String s : ingredientNames) {
			ingredientRepository.findByIngredientNameLike(s);
		}

		return ingredients;
	}
}
