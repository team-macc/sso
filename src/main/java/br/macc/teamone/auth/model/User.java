package br.macc.teamone.auth.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Document(collection = "users")
@ToString
@NoArgsConstructor
public class User {
	@Id
	private String id;
	private String username;
	private String password;
	private String email;
	@DBRef
	@Setter
	private Set<Role> roles = new HashSet<>();

	public User(final String username, final String password, final String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
