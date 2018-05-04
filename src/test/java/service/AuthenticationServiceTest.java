package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import application.Application;
import application.AuthenticationException;
import application.service.AuthenticationService;

@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
public class AuthenticationServiceTest {

	@Autowired AuthenticationService service;
	
	@Test
	public void goodCredentialsTest() throws Exception {
		String token = service.authenticate("user", "pass");
		assertNotNull(token);
	}
	
	@Test(expected = AuthenticationException.class)
	public void badCredentialsTest() throws Exception {
		service.authenticate("a user", "invalid pass");
	}
	
}
