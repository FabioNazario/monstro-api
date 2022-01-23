package br.edu.infnet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.infnet.model.UserDTO;


@FeignClient(name="SEGURANCA")
public interface SegurancaClient {

	@RequestMapping("/whoami")
	UserDTO getWhoami(@RequestHeader("Authorization") String token);

}
