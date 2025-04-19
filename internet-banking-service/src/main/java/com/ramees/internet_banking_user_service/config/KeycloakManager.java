package com.ramees.internet_banking_user_service.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakManager {

    private final KeycloakProperties keycloakProperties;

    public RealmResource getKeycloakInstanceWithRealm(){
        return keycloakProperties.getInstance().realm(keycloakProperties.getRealm());
    }

}
