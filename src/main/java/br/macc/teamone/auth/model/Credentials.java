package br.macc.teamone.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Credentials {
	private String password;
	private String username;
}
