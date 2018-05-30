package application.service;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.AuthenticationException;
import application.model.User;
import application.persistence.UserRepository;

@Service
public class AuthenticationService {

	@Autowired UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);  
	
	public String authenticate(String user, String pass) throws Exception {
	    log.info("Authenticating user " + user);
	    
		User aUser = userRepository.findByUsername(user);
		if(aUser == null) {
		    log.info("User " + user + " not found in database.");
		    throw new AuthenticationException();
		}
		
		if (BCrypt.checkpw(pass, aUser.getPassword())) {
		    log.info("Login successful, generating auth-token for user " + user);
		    return generateToken();
		}
		
		log.info("Password verification failed for user " + user);
		throw new AuthenticationException();
	}

	private String generateToken() throws Exception {
		return String.valueOf(System.currentTimeMillis());
	}

}
