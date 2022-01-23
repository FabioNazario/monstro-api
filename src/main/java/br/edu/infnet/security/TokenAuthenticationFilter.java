package br.edu.infnet.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.infnet.client.SegurancaClient;
import br.edu.infnet.model.UserDTO;

public class TokenAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	SegurancaClient segurancaClient;

	public TokenAuthenticationFilter(ApplicationContext ctx) {
		
		this.segurancaClient = ctx.getBean(SegurancaClient.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String token = getToken(request);
		
		UserDTO user = null;
		try {
			user = segurancaClient.getWhoami(token);
		}catch (Exception e){
			e.printStackTrace();
		}
		 
		if(user != null) {
			
			System.out.println("Usuario recuperado: " + user.getUsername());

			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(user, null, user.getPerfis());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			
		}
		
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return StringUtils.removeStart(token, "Bearer").trim();
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		String path = request.getRequestURI();
		return "/auth".equals(path);
	}


}
