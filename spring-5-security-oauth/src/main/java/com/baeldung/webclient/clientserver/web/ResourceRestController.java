package com.baeldung.webclient.clientserver.web;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceRestController {

    @GetMapping("/retrieve-resource")
    public String retrieveResource(@RegisteredOAuth2AuthorizedClient("bael") OAuth2AuthorizedClient authorizedClient) {
        return "TGERGERGER";
    }

}
