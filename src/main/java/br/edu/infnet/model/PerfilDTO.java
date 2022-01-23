package br.edu.infnet.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO implements GrantedAuthority{

	private Long id;
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}
	
}