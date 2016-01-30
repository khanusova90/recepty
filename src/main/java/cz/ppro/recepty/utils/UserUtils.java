package cz.ppro.recepty.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import cz.ppro.recepty.domain.AppUser;

public class UserUtils {

	private UserUtils() {
	}

	/**
	 * @return Uzivatelske jmeno aktualne prihlaseneho uzivatele
	 */
	public static String getActualUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	/**
	 * @return Aktualne prihlaseny uzivatel
	 */
	public static AppUser getActualUser() {
		AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

}
