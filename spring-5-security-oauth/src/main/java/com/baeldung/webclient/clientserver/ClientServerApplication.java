package com.baeldung.webclient.clientserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//@EnableResourceServer
@PropertySource("webclient-client-application.properties")
@SpringBootApplication
public class ClientServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ClientServerApplication.class, args);
    }

}
