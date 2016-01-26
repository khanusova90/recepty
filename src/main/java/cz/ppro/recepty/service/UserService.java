package cz.ppro.recepty.service;
import cz.ppro.recepty.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kristyna Kamenicka
 */
public interface UserService {
    /**
     * Vypocita nove hodnoceni receptu
     *
     * @param user
     *             {@link User})
     */
    public void createUser(User user);

    /**
     * Vypocita nove hodnoceni receptu
     *
     * @param id
     *             id uzivatele
     */
    public void deleteUser(int id);

    /**
     * Vypocita nove hodnoceni receptu
     *
     * @param user
     *             {@link User})
     */
    public void editUserInfo(User user);

    /**
     * Vypocita nove hodnoceni receptu
     *
     * @param id
     *              id uzivatele
     */
    public User getUserById(int id);

    /**
     * Seznam uzivatelu
     *
     */
       public List<User> getUserList();
}
