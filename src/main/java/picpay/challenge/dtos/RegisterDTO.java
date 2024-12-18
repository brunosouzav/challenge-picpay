package picpay.challenge.dtos;

import picpay.challenge.enus.UserRole;

public record RegisterDTO (
		
		String name,
		String email,
		String password,
		String document,
		UserRole role
	  
		)
	{

}
