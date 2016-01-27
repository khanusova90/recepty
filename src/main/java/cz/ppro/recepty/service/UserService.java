package cz.ppro.recepty.service;

import cz.ppro.recepty.domain.AppUser;

///**
// * Created by Kristyna Kamenicka
// */
//public interface UserService {
//    /**
//     * Vypocita nove hodnoceni receptu
//     *
//     * @param user
//     *             {@link User})
//     */
//    public void createUser(User user);
//
//    /**
//     * Vypocita nove hodnoceni receptu
//     *
//     * @param id
//     *             id uzivatele
//     */
//    public void deleteUser(int id);
//
//    /**
//     * Vypocita nove hodnoceni receptu
//     *
//     * @param user
//     *             {@link User})
//     */
//    public void editUserInfo(User user);
//
//    /**
//     * Vypocita nove hodnoceni receptu
//     *
//     * @param id
//     *              id uzivatele
//     */
//    public User getUserById(int id);
//
//    /**
//     * Seznam uzivatelu
//     *
//     */
//       public List<User> getUserList();
//}
//
//	/**
//	 * Ulozi do DB noveho uzivatele a zakoduje mu heslo
//	 * 
//	 * @param user
//	 *            Instance tridy {@link AppUser} s vyplnenym uzivatelskym jmenem
//	 *            a heslem
//	 * @return <code>false</code>, pokud uzivatel se zadanym jmenem uz v
//	 *         databazi existuje, jinak vraci <code>true</code>
//	 */
//	public Boolean saveUser(AppUser user);
//
//}

public interface UserService {
	/**
	 * + * Ulozi do DB noveho uzivatele a zakoduje mu heslo + * + * @param user
	 * + * Instance tridy {@link AppUser} s vyplnenym uzivatelskym jmenem + * a
	 * heslem + * @return <code>false</code>, pokud uzivatel se zadanym jmenem
	 * uz v + * databazi existuje, jinak vraci <code>true</code> +
	 */

	public Boolean saveUser(AppUser user);
}