package org.balu.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RevokeTokenEndpoint { 
	@Autowired
	private DefaultTokenServices defaultTokenServices;
 
    @PostMapping("/logout")
    public ResponseEntity<Void> create(@RequestHeader("Authorization") String value) throws InvalidClientException {
		defaultTokenServices.revokeToken(value);
		return ResponseEntity.ok().build();	
	}
}