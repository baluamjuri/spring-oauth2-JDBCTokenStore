package org.balu.learn;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ResourceServerApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public TokenStore tokenStore() {
	    return new JdbcTokenStore(dataSource);
	}
	
	
	//For checking token with auth-server
	/*@Primary
	@Bean
	public RemoteTokenServices tokenService() {
	    RemoteTokenServices tokenService = new RemoteTokenServices();
	    tokenService.setCheckTokenEndpointUrl(
	      "http://localhost:8881/auth/oauth/check_token");
	    tokenService.setClientId("springdeveloper123");
	    tokenService.setClientSecret("test");
	    return tokenService;
	}*/
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String hasRoleAdmin() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();						
		return "Hi "+name+", you have Admin Role";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String message() {
		return "You have User Role" ;
	}
	
	@GetMapping("/")
	public String welcome() {
		return "This is Resource server...";
	}
}
