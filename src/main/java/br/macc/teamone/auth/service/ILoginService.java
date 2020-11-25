package br.macc.teamone.auth.service;

import br.macc.teamone.auth.model.Credentials;
import br.macc.teamone.auth.model.JwtResponse;

public interface ILoginService {
	public JwtResponse login(Credentials credentials);
}
