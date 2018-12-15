package org.balu.learn.config;

import javax.sql.DataSource;

import org.balu.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserService userService;
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients)throws Exception {
        clients.jdbc(dataSource);
    }
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)throws Exception {
        endpoints.tokenStore(tokenStore())
          		.authenticationManager(authenticationManager).userDetailsService(userService);
    }
	
	@Bean
    public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
    }

	@Bean
	public DefaultTokenServices defaultTokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
	    return defaultTokenServices;
	}
	
	@Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) 
      throws Exception {
        oauthServer
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
}
