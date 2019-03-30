package com.baeldung.restassured.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    
    private static final String SECURED_RESOURCE = "secured-resource";
    
    @GetMapping("/secured")
    public String retrieveSecuredResourceWithBasicAuth() {
        return SECURED_RESOURCE; 
    }

}
