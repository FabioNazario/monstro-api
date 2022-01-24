package br.edu.infnet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.infnet.client.SegurancaClient;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	SegurancaClient segurancaClient;

	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

		UserDetails user = (UserDetails) segurancaClient.getWhoami(token);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		
		return user;

	}

}