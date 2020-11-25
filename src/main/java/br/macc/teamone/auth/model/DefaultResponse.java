package br.macc.teamone.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DefaultResponse {
	private Boolean success;
	private String message;
	private Object data;

	public DefaultResponse(final Boolean success, final String message) {
		this.success = success;
		this.message = message;
	}

}
