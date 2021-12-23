package com.jotapcinfo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jotapcinfo.entities.User;
import com.jotapcinfo.services.UserService;
import com.jotapcinfo.services.exceptions.ServiceException;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		try {
			obj = userService.insert(obj);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") // Retornar o caminho do objeto inserido
					.buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).body(obj);
		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
}
