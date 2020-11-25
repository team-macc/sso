package br.macc.teamone.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.macc.teamone.auth.model.NewUser;
import br.macc.teamone.auth.model.User;
import br.macc.teamone.auth.repository.UserRepository;

@Service
public class SignUpService implements ISignUpService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SignUpService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void createNewUser(NewUser newUser) {
		final String encPwd = passwordEncoder.encode(newUser.getPassword());
		final User user = new User(newUser.getUsername(), encPwd, newUser.getEmail());
		System.out.println(user.toString());
		userRepository.save(user);
	}

}
