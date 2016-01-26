<<<<<<< HEAD
package cz.ppro.recepty.service.impl;
import cz.ppro.recepty.dao.UserDao;
import cz.ppro.recepty.domain.User;
import cz.ppro.recepty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tynak_000 on 26/01/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {

    }

    @Override
    public void editUserInfo(User user) {

    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserList() {
        return null;
    }


	private static Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	private PasswordEncoder encoder;

	public UserServiceImpl() {
		this.encoder = new BCryptPasswordEncoder();
	}

	@Transactional
	public Boolean saveUser(AppUser user) {
		AppUser existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser == null) {
			logger.info("Saving new user: " + user.getUsername());
			String encPass = encoder.encode(user.getPassword());
			user.setPassword(encPass);
			user.getUserRoles().add(Role.ROLE_USER.toString());
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}

}
