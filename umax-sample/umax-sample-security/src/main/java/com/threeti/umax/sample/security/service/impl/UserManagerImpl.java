package com.threeti.umax.sample.security.service.impl;

import com.threeti.umax.sample.security.Constants;
import com.threeti.umax.sample.security.dao.UserDao;
import com.threeti.umax.sample.security.model.User;
import com.threeti.umax.sample.security.service.UserExistsException;
import com.threeti.umax.sample.security.service.UserManager;
import com.threeti.umax.sample.security.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;


/**
 * Implementation of UserManager interface.
 *
 * @author laurence.geng</a>
 */
@Service("userManager")
@WebService(serviceName = "UserService", 
			targetNamespace="http://service.security.sample.umax.threeti.com/",
			endpointInterface = "com.threeti.umax.sample.security.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager, UserService {
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }

    public int authenticate(String userName, String password){
    	String encodedPassword = passwordEncoder.encodePassword(password, null);
    	String storedPassword = userDao.getUserPassword(userName);
    	if(StringUtils.isNotBlank(storedPassword)&&storedPassword.endsWith(encodedPassword) ){
    		return Constants.AUTHENTICATION_PASS;
    	}
    	return Constants.AUTHENTICATION_FAILED;
    }
    
    /**
     * {@inheritDoc}
     */
    public User getUser(String userId) {
        return userDao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers() {
        return userDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                String currentPassword = userDao.getUserPassword(user.getUsername());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }

        try {
            return userDao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        } catch (JpaSystemException e) { // needed for JPA
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userId) {
        log.debug("removing user: " + userId);
        userDao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return (User) userDao.loadUserByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    public List<User> search(String searchTerm) {
        return super.search(searchTerm, User.class);
    }
}
