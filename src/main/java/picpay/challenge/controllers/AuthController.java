package picpay.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import picpay.challenge.dtos.LoginDTO;
import picpay.challenge.dtos.RegisterDTO;
import picpay.challenge.dtos.TokenResponseDTO;
import picpay.challenge.services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	public AuthenticationService authenticationService;
	
    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(
            @RequestBody RegisterDTO request
    ) {
		return ResponseEntity.ok(authenticationService.register(request));

    	
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(
            @RequestBody LoginDTO request
    ) {
		return ResponseEntity.ok(authenticationService.login(request));

    }
}
