package br.macc.teamone.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.macc.teamone.auth.model.DefaultResponse;
import br.macc.teamone.auth.model.NewUser;
import br.macc.teamone.auth.service.ISignUpService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class SignUpCtrl {
	private final ISignUpService signupService;
	
	@Autowired
	public SignUpCtrl(final ISignUpService signupService) {
		this.signupService = signupService;
	}
	
	@PostMapping("signup")
	public ResponseEntity<DefaultResponse> signUp(@RequestBody final NewUser newUser){
		signupService.createNewUser(newUser);
		return ResponseEntity.ok(new DefaultResponse(true, "new user created"));
	}
}
