package cz.ppro.recepty.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Uzivatel aplikace. Ma uzivatelske jmeno, heslo a seznam uzivatelskych roli
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
public class AppUser implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_APP_USER")
	private Long idAppUser;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@ElementCollection
	@CollectionTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ID_USER") })
	@Column(name = "ROLE")
	private Set<String> userRoles;

	public AppUser() {
		this.userRoles = new HashSet<String>();
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

	public Set<String> getUserRoles() {
		return userRoles;
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
