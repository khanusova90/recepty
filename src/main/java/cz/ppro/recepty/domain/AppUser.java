package cz.ppro.recepty.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Uzivatel aplikace.
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
@Table(name = "APP_USER")
public class AppUser implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_APP_USER")
	private Long idAppUser;

	@Size(min = 5, max = 20)
	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Size(min = 5, max = 20)
	@Column(name = "NAME")
	private String name;

	@Size(min = 5, max = 20)
	@Column(name = "SURNAME")
	private String surname;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "USER_RATING")
	private float rating;

	@ElementCollection
	@CollectionTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ID_APP_USER") })
	@Column(name = "ROLE")
	private Set<String> userRoles;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "author")
	private List<Recipe> recipes;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "FAVORITE_RECIPES", joinColumns = @JoinColumn(name = "ID_APP_USER") )
	private List<Recipe> favoriteRecipes;

	public AppUser() {
		this.userRoles = new HashSet<String>();
		this.rating = 0;
	}

	public Long getIdAppUser() {
		return idAppUser;
	}

	public void setIdAppUser(Long idAppUser) {
		this.idAppUser = idAppUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		String roles = null;
		for (String s : userRoles) {
			roles = roles + "," + s;
		}
		roles = roles.substring(1);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
