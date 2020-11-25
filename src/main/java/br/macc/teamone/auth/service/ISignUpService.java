package br.macc.teamone.auth.service;

import br.macc.teamone.auth.model.NewUser;

public interface ISignUpService {
	public void createNewUser(NewUser newUser);
}
