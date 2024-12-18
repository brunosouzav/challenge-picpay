package picpay.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import picpay.challenge.dtos.LoginDTO;
import picpay.challenge.dtos.RegisterDTO;
import picpay.challenge.dtos.TokenResponseDTO;
import picpay.challenge.exceptions.DocumentAlreadyRegisteredException;
import picpay.challenge.exceptions.EmailAlreadyRegisteredException;
import picpay.challenge.exceptions.EmailInvalidException;
import picpay.challenge.model.User;
import picpay.challenge.repositories.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public TokenResponseDTO register(RegisterDTO request) {

		repository.findByEmail(request.email()).ifPresent(user -> {
			throw new EmailAlreadyRegisteredException("O email " + request.email() + " já está registrado");
		});

		repository.findByDocument(request.document()).ifPresent(user -> {
			throw new DocumentAlreadyRegisteredException("O documento " + request.document() + " já está registrado");
		});
		
		User user = User.builder()
				.name(request.name())
				.email(request.email())
				.password(passwordEncoder.encode(request.password()))
				.document(request.document())
				.role(request.role())
				.build();
		
		repository.save(user);

		String jwtToken = jwtService.generateToken(user);
		return TokenResponseDTO.builder().token(jwtToken).build();
	}

	public TokenResponseDTO login(LoginDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

		User user = repository.findByEmail(request.email())
				.orElseThrow(() -> new EmailInvalidException("O email " + request.email() + "não foi encontrado"));

		String jwtToken = jwtService.generateToken(user);
		return TokenResponseDTO.builder().token(jwtToken).build();
	}
}
