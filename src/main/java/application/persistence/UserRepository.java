package application.persistence;

import org.springframework.data.repository.CrudRepository;

import application.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String user);

	
}
