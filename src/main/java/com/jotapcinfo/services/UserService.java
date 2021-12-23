package com.jotapcinfo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jotapcinfo.entities.User;
import com.jotapcinfo.repositories.UserRepository;
import com.jotapcinfo.services.exceptions.ServiceException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User insert(User obj) {
		User user = userRepository.findByEmail(obj.getEmail());
		if (user != null) {
			throw new ServiceException("Email já existe");
		}

		obj.setPassword(passwordEncoder.encode(obj.getPassword()));
		return userRepository.save(obj);
	}

}
