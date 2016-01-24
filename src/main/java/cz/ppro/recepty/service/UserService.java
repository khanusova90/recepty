package cz.ppro.recepty.service;

import cz.ppro.recepty.domain.AppUser;

public interface UserService {

	/**
	 * Ulozi do DB noveho uzivatele a zakoduje mu heslo
	 * 
	 * @param user
	 *            Instance tridy {@link AppUser} s vyplnenym uzivatelskym jmenem
	 *            a heslem
	 * @return <code>false</code>, pokud uzivatel se zadanym jmenem uz v
	 *         databazi existuje, jinak vraci <code>true</code>
	 */
	public Boolean saveUser(AppUser user);

}