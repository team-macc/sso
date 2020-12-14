package br.macc.teamone.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.macc.teamone.auth.model.DefaultResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/verify/token")
public class VerifyCtrl {
	@GetMapping
	public ResponseEntity<DefaultResponse> verify() {
		return ResponseEntity.ok(new DefaultResponse(true, "token válido"));
	}
}
