package br.macc.teamone.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.macc.teamone.auth.model.NewUser;
import br.macc.teamone.auth.model.User;
import br.macc.teamone.auth.repository.UserRepository;

@Service
public class SignUpService implements ISignUpService {
	private final UserRepository userRepository;

	@Autowired
	public SignUpService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void createNewUser(NewUser newUser) {
		userRepository.save(new User(newUser.getUsername(), newUser.getPassword(), newUser.getEmail()));
	}

}
