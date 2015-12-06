package cz.ppro.recepty.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Recipe {
	
	private List<Ingredience> ingrediences;
	private List<Meal> meals;
	private Date time;
	
	/*
	 * Hodnoceni receptu
	 */
	private float rating;
	/*
	 * Pocet lidi, kteri recept hodnotili
	 */
	private int rateCount;
	private String preparationProcess;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	public Recipe() {
		this.rateCount = 0;
	}
	
	public List<Ingredience> getIngrediences() {
		return ingrediences;
	}
	
	public void setIngrediences(List<Ingredience> ingrediences) {
		this.ingrediences = ingrediences;
	}
	
	public List<Meal> getMeals() {
		return meals;
	}
	
	public void setMeals(List<Meal> meals) {
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
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public int getRateCount() {
		return rateCount;
	}
	
	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

}
