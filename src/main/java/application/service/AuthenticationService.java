package application.service;

import org.springframework.stereotype.Service;

import application.AuthenticationException;

@Service
public class AuthenticationService {

	public String authenticate(String user, String pass) throws Exception {
		if(user.equals("user") && pass.equals("pass")) { //TODO Validar credenciales con una BD
			return generateToken();
		}else {
			throw new AuthenticationException();
		}
	}

	private String generateToken() throws Exception {
		//TODO encrypt token
		return String.valueOf(System.currentTimeMillis());
	}

	public boolean isValid(String authToken) throws Exception {
		//TODO decrypt token and then validate timestamp
		long now = System.currentTimeMillis();
		long token = Long.parseLong(authToken);
		long elapsed = now - token;
		return elapsed < 3600000; //1h
	}

}
