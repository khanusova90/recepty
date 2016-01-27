package cz.ppro.recepty.domain;

/**
 * Uzivatelske role
 * 
 * <p>
 * <b>ROLE_USER</b> - Bezna uzivatelska role. Umoznuje pridavat recepty,
 * znamkovat je a komentovat. <br/>
 * <b>ROLE_ADMIN</b> - Administratorska role. Umozuje spravovat aplikaci
 * 
 * @author Katerina Hanusova
 *
 */
public enum Role {

	ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

	private String role;

	private Role(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}

}
