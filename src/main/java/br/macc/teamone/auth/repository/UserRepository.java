package br.macc.teamone.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.macc.teamone.auth.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{
	public User findByUsername(String username);
}
