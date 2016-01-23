package cz.ppro.recepty.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Trida reprezentujici jednotlive druhy prisad.
 * 
 * @author Katerina Hanusova
 *
 */
@Entity(name = "INGREDIENT")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INGREDIENT")
	private Long idIngredient;

	@Column(name = "INGREDIENT_NAME")
	private String ingredientName;

	public Long getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(Long idIngredient) {
		this.idIngredient = idIngredient;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

}
