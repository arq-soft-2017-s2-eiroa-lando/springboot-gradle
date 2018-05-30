package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import application.Application;
import application.AuthenticationException;
import application.model.User;
import application.persistence.UserRepository;
import application.service.AuthenticationService;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
public class AuthenticationServiceTest {

	@Autowired AuthenticationService service;
	
	@MockBean UserRepository userRepository;
	
	@Test
	public void goodCredentialsTest() throws Exception {
	    User user = new User();
	    user.setPassword("$2a$10$9qwBauxOTVWsVuFZbCgPz.V64BJu/CetakLyDx1RTfPJ4DRlD1CDS");
	    when(userRepository.findByUsername("user")).thenReturn(user);
	    
		String token = service.authenticate("user", "pass");
		
		assertNotNull(token);
		verify(userRepository).findByUsername("user");
	}
	
	@Test(expected = AuthenticationException.class)
	public void badCredentialsTest() throws Exception {
		service.authenticate("a user", "invalid pass");
	}
	
}
