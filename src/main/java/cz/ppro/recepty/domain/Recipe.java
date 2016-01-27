package cz.ppro.recepty.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "recipe", fetch = FetchType.EAGER)
	private List<RecipeIngredient> recipeIngredients;

	@ManyToOne
	private AppUser author;

	public Recipe() {
		this.rateCount = 0;
		this.rating = 0;
	}

	public Long getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(Long idRecipe) {
		this.idRecipe = idRecipe;
	}

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

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public List<RecipeIngredient> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}

	public AppUser getAuthor() {
		return author;
	}

	public void setAuthor(AppUser author) {
		this.author = author;
	}

}
