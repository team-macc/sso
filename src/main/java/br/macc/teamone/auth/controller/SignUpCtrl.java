package br.macc.teamone.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.macc.teamone.auth.model.DefaultResponse;
import br.macc.teamone.auth.model.NewUser;


@RestController
public class SignUpCtrl {
	
	@PostMapping("signup")
	public ResponseEntity<DefaultResponse> signUp(@RequestBody final NewUser newUser){
		return ResponseEntity.ok(new DefaultResponse(false, "error"));
	}
}
