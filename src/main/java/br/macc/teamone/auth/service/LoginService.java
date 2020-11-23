package br.macc.teamone.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.macc.teamone.auth.model.Credentials;
import br.macc.teamone.auth.model.ResponseLogin;
import br.macc.teamone.auth.model.User;
import br.macc.teamone.auth.repository.UserRepository;

@Service
public class LoginService implements ILoginService {
	private static final String EMPTY_MESSAGE = "";
	private final UserRepository userRepository;
	private final ITokenService tokenService;

	@Autowired
	public LoginService(final UserRepository userRepository, final ITokenService tokenService) {
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}
	
	@Override
	public ResponseLogin login(final Credentials credentials) {
		final User user = userRepository.findByUsername(credentials.getUsername());
		ResponseLogin res;
		
		if(user.getPassword().equals(credentials.getPassword())) {
			final String toekn = tokenService.getToken(user);
			res = new ResponseLogin(true, EMPTY_MESSAGE, toekn);
		} else {
			res = new ResponseLogin(false, "Credencials incorretas");
		}
		
		return res;
	}
}
