package com.teammacc.auth.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.teammacc.auth.entity.Permission;
import com.teammacc.auth.entity.User;
import com.teammacc.auth.jwt.JwtTokenProvider;
import com.teammacc.auth.repository.PermissionRepository;
import com.teammacc.auth.repository.UserRepository;
import com.teammacc.auth.vo.UserVO;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/", produces = { "application/json", "application/xml",
		"application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final PermissionRepository permissionRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserRepository userRepository,PermissionRepository permissionRepository, BCryptPasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
		this.permissionRepository = permissionRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping(value ="/teste-security")
	public String teste() {
		return "testado";
	}

	@PostMapping(value ="/login")
	public ResponseEntity<?> login(@RequestBody UserVO userVO) {
		try {
			var username = userVO.getUserName();
			var password = userVO.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = userRepository.findByUserName(username);

			var token = "";

			if (user != null) {
				token = jwtTokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("User name not found");
			}

			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password");
		}
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> createUser(@RequestBody UserVO userVO){
		Permission permission = null;
		Permission findPermission = permissionRepository.findByDescription("User");
		if (findPermission == null) {
			permission = new Permission();
			permission.setDescription("User");
			permission = permissionRepository.save(permission);
		} else {
			permission = findPermission;
		}
		
		User user = new User();
		
		user.setUserName(userVO.getUserName());
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(userVO.getPassword()));
		user.setPermissions(Arrays.asList(permission));
		
		userRepository.save(user);
				
		Map<Object, Object> model = new HashMap<>();
		model.put("status_code", 200);
		return ok(model);
	}
	
}
