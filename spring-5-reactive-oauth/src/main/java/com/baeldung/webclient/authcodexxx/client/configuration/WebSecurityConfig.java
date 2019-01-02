package com.baeldung.webclient.authcodexxx.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

//import com.baeldung.webclient.authcode.client.configuration.filters.OAuth2ClientOnlyAuthorizationRequestRedirectWebFilter;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository clientRegistrationRepository) {
        http.authorizeExchange()
            .anyExchange()
            .permitAll();
//            .and()
//            .oauth2Client();
//            .clientRegistrationRepository(clientRegistrationRepository);
//            .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//            .addFilterAt(new OAuth2ClientOnlyAuthorizationRequestRedirectWebFilter(), SecurityWebFiltersOrder.FORM_LOGIN);
        return http.build();
    }

}
