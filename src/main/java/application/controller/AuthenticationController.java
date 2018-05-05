package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.AuthenticationException;
import application.persistence.UserRepository;
import application.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

	@Autowired AuthenticationService service;
	@Autowired UserRepository userRepository;
	
	@PostMapping(consumes="application/json")
	@ApiOperation(value = "Authenticates a user and returns a token to use for calling protected endpoints.")
	public @ResponseBody ResponseEntity<String> authenticate(@RequestBody Auth credentials) {
		try {
			return new ResponseEntity<String>(service.authenticate(credentials.user, credentials.pass), HttpStatus.OK);
		}catch( AuthenticationException e) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}catch( Exception e ) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

class Auth {
	String user;
	String pass;
	
	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
