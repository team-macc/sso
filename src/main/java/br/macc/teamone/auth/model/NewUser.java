package br.macc.teamone.auth.model;

import lombok.Data;

@Data
public class NewUser {
	private String username;
	private String password;
	private String email;
}
