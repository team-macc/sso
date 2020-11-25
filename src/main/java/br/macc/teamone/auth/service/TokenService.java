package br.macc.teamone.auth.service;

import org.springframework.stereotype.Service;

import br.macc.teamone.auth.model.User;

@Service
public class TokenService implements ITokenService{

	@Override
	public String getToken(final User user) {
		return "token-12345";
	}

}
