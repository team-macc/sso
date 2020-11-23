package br.macc.teamone.auth.service;

import br.macc.teamone.auth.model.Credentials;
import br.macc.teamone.auth.model.ResponseLogin;

public interface ILoginService {
	public ResponseLogin login(Credentials credentials);
}
