package br.edu.infnet.documentation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	
	@Value("${microservice.monstro.application.name}")
	String appName;
	
		@Bean
	public OpenAPI OpenAPI() {

		appName = StringUtils.capitalize(appName);
		Server server = new Server();
		server.setUrl("https://"+StringUtils.upperCase(appName)+"/");
	    return new OpenAPI().servers(List.of(server))
	    		.info(new Info()
	    				.title(StringUtils.capitalize(appName))
	    				.description("Documentação do micro serviço de " + appName)
	    				.version("v1.0.0")
	    				.contact(new Contact().name("Bruno, Eliton, Fábio e Rogéria").email("befr@infnet.com"))
	    				)
	    		.externalDocs(new ExternalDocumentation()
	            		.description("GitHub " + appName)
	            		.url("https://github.com/FabioNazario/monstro-api"));
  }

}
