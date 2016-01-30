package cz.ppro.recepty.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Propojujici tabulka pro recepty a ingredience nesouci informace o mnozstvi
 * pouzitych ingredienci
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
@Table(name = "RECIPE_INGREDIENT")
public class RecipeIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RECIPE_INGREDIENT")
	private Long idRecipeIngredient;

	@ManyToOne
	@JoinColumn(name = "ID_RECIPE")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "ID_INGREDIENT")
	private Ingredient ingredient;

	@Column(name = "AMOUNT")
	private Float amount;

	@Column(name = "UNIT")
	private String unit;

	public RecipeIngredient() {
		this.amount = 0f;
		this.unit = "kg";
	}

	public RecipeIngredient(Recipe recipe) {
		this.recipe = recipe;
		this.amount = 0f;
		this.unit = "kg";
	}

	public Long getIdRecipeIngredient() {
		return idRecipeIngredient;
	}

	public void setIdRecipeIngredient(Long idRecipeIngredient) {
		this.idRecipeIngredient = idRecipeIngredient;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * @return Mnozstvi pouzite ingredience
	 */
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	/**
	 * @return Jednotka, ve ktere je uvedeno mnozstvi ingredienci (g, kg, ml
	 *         apod.)
	 */
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
