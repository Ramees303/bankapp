package com.ramees.internet_banking_user_service.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakProperties {

    @Value("${app.config.keycloak.server-url}")
    private String serverUrl;

    @Value("${app.config.keycloak.client-id}")
    private String clientId;

    @Value("${app.config.keycloak.client-secret}")
    private String clientSecret;

    @Value("${app.config.keycloak.realm}")
    private String realm;

    private  static Keycloak keyCloakInstance=null;

    public Keycloak getInstance(){
        if(keyCloakInstance==null){
            keyCloakInstance=KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .realm(realm)
                    .grantType("client_credentials")
                    .build();
        }
        return keyCloakInstance;
    }

    public String getRealm(){
        return realm;
    }


}
