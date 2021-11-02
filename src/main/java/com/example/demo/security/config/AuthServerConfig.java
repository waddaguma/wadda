package com.example.demo.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired private PasswordEncoder passwordEncoder; 
	
	@Autowired private UserDetailsService userDetailsService;

	@Override public void configure(ClientDetailsServiceConfigurer clients) throws Exception { 
		clients.inMemory()
		       .withClient("iamclient")
		       .secret(passwordEncoder
		       .encode("iamsecret"))
		       .authorizedGrantTypes("authorization_code", "password", "refresh_token")
		       .scopes("read", "write")
		       .accessTokenValiditySeconds(60*60) 
		       .refreshTokenValiditySeconds(6*60*60) 
		       .autoApprove(true); 
		}

	@Override public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception { 
		endpoints.tokenStore(tokenStore)
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService); 
		}


		
//	@Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
//		  clients.jdbc(dataSource);
//				
//    }
}
