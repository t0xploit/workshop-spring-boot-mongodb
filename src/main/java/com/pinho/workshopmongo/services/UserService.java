package com.pinho.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinho.workshopmongo.domain.User;
import com.pinho.workshopmongo.dto.UserDTO;
import com.pinho.workshopmongo.repository.UserRepository;
import com.pinho.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
 
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) { 
	    Optional<User> optionalUser = repository.findById(obj.getId());
	    
	    if (optionalUser.isPresent()) {
	        User newObj = optionalUser.get();
	        updateData(newObj, obj);
	        return repository.save(newObj);
	    } else { 
	        throw new ObjectNotFoundException("User not found with id: " + obj.getId());
	    }
	}

	 
	private void updateData(User newObj, User obj) {
		 newObj.setName(obj.getName());
		 newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getEmail(), objDto.getName());
	}
	
}
