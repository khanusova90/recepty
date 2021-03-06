package cz.ppro.recepty.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Role;
import cz.ppro.recepty.repository.UserRepository;
import cz.ppro.recepty.service.UserService;

/**
 * Created by tynak_000 on 26/01/2016.
 */
@Service
public class UserServiceImpl implements UserService {

	private static Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	private PasswordEncoder encoder;

	public UserServiceImpl() {
		this.encoder = new BCryptPasswordEncoder();
	}

	@Override
	@Transactional(readOnly = true)
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	@Override
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
