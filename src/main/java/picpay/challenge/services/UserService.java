package picpay.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import picpay.challenge.model.User;
import picpay.challenge.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAllUsers() {
		
		return repository.findAll();
	}
	
	public User findById(Long id) {
	    return repository.findById(id).orElseThrow(() -> new 
	    		UsernameNotFoundException("User not found with id: " + id));
	}
	
	public void updateUser(Long id, User user) {
		
		User newUser = findById(id);
		
		newUser.setName(user.getName());
		newUser.setPassword(user.getPassword());
		
		repository.save(newUser);
	}
}
