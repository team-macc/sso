package br.macc.teamone.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "roles")
@NoArgsConstructor
@Getter
public class Role {
	@Id
	private String id;
	private ERole name;

	public Role(final ERole name) {
		this.name = name;
	}
}
