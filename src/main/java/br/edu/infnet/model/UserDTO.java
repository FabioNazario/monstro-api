package br.edu.infnet.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {

	private Long id;
	private String username;
	private List<PerfilDTO> perfis;

}
