package com.jotapcinfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotapcinfo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email); //Buscar usuário por email

}
