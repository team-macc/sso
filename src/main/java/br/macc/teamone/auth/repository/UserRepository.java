package br.macc.teamone.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.macc.teamone.auth.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{
	public Optional<User> findByUsername(String username);
}
