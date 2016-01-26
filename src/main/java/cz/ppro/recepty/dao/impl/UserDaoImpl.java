package cz.ppro.recepty.dao.impl;

import cz.ppro.recepty.dao.UserDao;
import cz.ppro.recepty.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUserInfo(User user) {

    }

    @Override
    public List<User> listUser() {
        return null;
    }

    @Override
    public User getUserById(String username) {
        return null;
    }

    @Override
    public void removeUser(int id) {

    }

    @Override
    public User loadUser(String username) {
        return null;
    }
  /*  @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void updateUserInfo(User user) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(user);
        }

    @Override
    public List<User> listUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        return userList;
    }

    @Override
    public User getUserById(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, username);
        return user;
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User a = (User) session.load(User.class, new Integer(id));
        if (a != null) {
            session.delete(a);
        }
    }

    @Override
    public User loadUser(String username) {
        return (User)sessionFactory.getCurrentSession().load(User.class, username);
    }*/
}
