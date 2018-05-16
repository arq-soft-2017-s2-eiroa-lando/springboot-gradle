package application.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.AuthenticationException;
import application.model.User;
import application.persistence.UserRepository;

@Service
public class AuthenticationService {

	@Autowired UserRepository userRepository;
	
	public String authenticate(String user, String pass) throws Exception {
	    Iterable<User> all = userRepository.findAll();
	    
	    
		User aUser = userRepository.findByUsername(user);
		if(aUser == null) throw new AuthenticationException();
		
		if (BCrypt.checkpw(pass, aUser.getPassword())) return generateToken();
		
		throw new AuthenticationException();
	}

	private String generateToken() throws Exception {
		return String.valueOf(System.currentTimeMillis());
	}

}
