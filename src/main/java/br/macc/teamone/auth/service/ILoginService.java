package br.macc.teamone.auth.service;

import br.macc.teamone.auth.model.Credentials;
import br.macc.teamone.auth.model.DefaultResponse;

public interface ILoginService {
	public DefaultResponse login(Credentials credentials);
}
