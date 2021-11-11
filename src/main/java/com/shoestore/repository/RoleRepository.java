package com.shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoestore.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByName(String name);
}
