package cz.ppro.recepty.dao;

import cz.ppro.recepty.domain.User;

import java.util.List;

/**
 * Created by tynak_000 on 26/01/2016.
 */
public interface UserDao {
    public void addUser(User user);

    public void updateUserInfo(User user);

    /**
     * Returns all users
     * @return
     */
    public List<User> listUser();

    /**
     * Get user by his id
     * @param username
     * @return
     */
    public User getUserById(String username);

    public void removeUser(int id);


    /**
     * Get User proxy
     * @param username
     * @return
     */
    public User loadUser(String username);
}
