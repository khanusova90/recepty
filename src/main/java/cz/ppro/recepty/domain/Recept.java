package cz.ppro.recepty.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Recept {
	
	private List<Ingredience> ingredience;
	private List<Meal> meals;
	private Date time;
	private float ranking;
	private String preparationProcess;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

}
