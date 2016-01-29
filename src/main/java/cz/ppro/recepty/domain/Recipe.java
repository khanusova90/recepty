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

	private String name;

	@Column(name = "RATING")
	private float rating;

	@Column(name = "RATE_COUNT")
	private Integer rateCount;

	private String description;

	@Lob
	@Column(name = "PREPARATION")
	private String preparationProcess;

	@ElementCollection
	@CollectionTable(name = "RECIPE_MEAL", joinColumns = { @JoinColumn(name = "ID_RECIPE") })
	@Column(name = "MEAL")
	private List<String> categories;

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

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public void addCategory(String category) {
		this.categories.add(category);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
