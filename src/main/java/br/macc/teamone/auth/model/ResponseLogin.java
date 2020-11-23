package br.macc.teamone.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseLogin {
	private Boolean success;
	private String message;
	private Object data;

	public ResponseLogin(final Boolean success, final String message) {
		this.success = success;
		this.message = message;
	}

}
