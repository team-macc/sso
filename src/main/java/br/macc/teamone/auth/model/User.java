package br.macc.teamone.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Document(collection = "users")
public class User {
	@Id
	private String id;
	private String username;
	private String password;
	private String email;

	public User(final String username, final String password, final String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
