package cz.ppro.recepty.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.repository.IngredientRepository;
import cz.ppro.recepty.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public List<Ingredient> findIngredientByName(String name) {
		return ingredientRepository.findByIngredientNameLike(name);
	}

	@Override
	public List<Ingredient> getAll() {
		return ingredientRepository.findAll();
	}
}