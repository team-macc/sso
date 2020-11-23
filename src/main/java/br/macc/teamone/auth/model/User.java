package br.macc.teamone.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
	private Integer id;
	private String username;
	private String password;
}
