package br.macc.teamone.auth.service;

import br.macc.teamone.auth.model.User;

public interface ITokenService {
	public String getToken(User user);
}
