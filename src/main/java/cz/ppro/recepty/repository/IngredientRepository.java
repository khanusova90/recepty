package cz.ppro.recepty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.ppro.recepty.domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	public List<Ingredient> findByIngredientNameLike(String ingredientName);

}
