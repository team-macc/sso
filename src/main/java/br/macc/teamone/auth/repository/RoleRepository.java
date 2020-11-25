package br.macc.teamone.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.macc.teamone.auth.model.Role;

public interface RoleRepository extends MongoRepository<Role, Integer> {
	public Optional<Role> findByName(String name);
}
