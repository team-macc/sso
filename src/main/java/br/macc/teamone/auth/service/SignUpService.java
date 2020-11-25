package br.macc.teamone.auth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.macc.teamone.auth.model.ERole;
import br.macc.teamone.auth.model.NewUser;
import br.macc.teamone.auth.model.Role;
import br.macc.teamone.auth.model.User;
import br.macc.teamone.auth.repository.RoleRepository;
import br.macc.teamone.auth.repository.UserRepository;

@Service
public class SignUpService implements ISignUpService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SignUpService(final UserRepository userRepository, final RoleRepository roleRepository,
			final PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public void createNewUser(final NewUser newUser) {
		final String encPwd = passwordEncoder.encode(newUser.getPassword());
		final User user = new User(newUser.getUsername(), encPwd, newUser.getEmail());

		final Set<Role> roles = new HashSet<>();
		final String nRole = newUser.getRole();
		
		if (nRole == null || newUser.getRole().equals("consumer")) {
			final Role userRole = roleRepository.findByName(ERole.ROLE_CONSUMER.name()).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			switch (nRole) {
			case "admin":
				final Role admRole = roleRepository.findByName(ERole.ROLE_ADMIN.name()).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(admRole);
				break;
			case "vendor":
				final Role vendorRole = roleRepository.findByName(ERole.ROLE_VENDOR.name()).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(vendorRole);
				break;
			default:
				final Role userRole = roleRepository.findByName(ERole.ROLE_CONSUMER.name()).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
				break;
			}
		}
		
		user.setRoles(roles);
		userRepository.save(user);
	}

}
