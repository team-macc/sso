package br.macc.teamone.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.macc.teamone.auth.model.Credentials;
import br.macc.teamone.auth.model.DefaultResponse;
import br.macc.teamone.auth.service.LoginService;

@RestController
public class LoginCtrl {
	private final LoginService loginService;

	@Autowired
	public LoginCtrl(final LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("login")
	public ResponseEntity<DefaultResponse> login(@RequestBody final Credentials credentials) {
		return ResponseEntity.ok(loginService.login(credentials));
	}
}
