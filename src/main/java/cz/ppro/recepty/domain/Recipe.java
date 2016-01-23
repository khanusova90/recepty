package cz.ppro.recepty.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

/**
 * Trida reprezentujici recept
 * 
 * @author Katerina Hanusova
 *
 */
@Entity(name = "RECIPE")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RECIPE")
	private Long idRecipe;

	@ElementCollection
	@CollectionTable(name = "RECIPE_MEAL", joinColumns = { @JoinColumn(name = "ID_RECIPE") })
	@Column(name = "MEAL")
	private List<String> meals;

	@Column(name = "RATING")
	private float rating;

	@Column(name = "RATE_COUNT")
	private Integer rateCount;

	@Lob
	@Column(name = "PREPARATION")
	private String preparationProcess;

	// private Date time;
	// private List<Ingredient> ingredients;
	// private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	public Recipe() {
		this.rateCount = 0;
	}

	public Long getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(Long idRecipe) {
		this.idRecipe = idRecipe;
	}

	// public List<Ingredient> getIngrediences() {
	// return ingredients;
	// }
	//
	// public void setIngrediences(List<Ingredient> ingredients) {
	// this.ingredients = ingredients;
	// }

	public List<String> getMeals() {
		return meals;
	}

	public void setMeals(List<String> meals) {
		this.meals = meals;
	}

	public String getPreparationProcess() {
		return preparationProcess;
	}

	public void setPreparationProcess(String preparationProcess) {
		this.preparationProcess = preparationProcess;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	// public Date getTime() {
	// return time;
	// }
	//
	// public void setTime(Date time) {
	// this.time = time;
	// }

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

}
