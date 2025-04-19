package com.ramees.internet_banking_user_service.service;

import com.ramees.internet_banking_user_service.config.KeycloakManager;
import com.ramees.internet_banking_user_service.exception.custom.UserNotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    private final KeycloakManager keycloakManager;

    public Integer createUser(UserRepresentation userRepresentation){
        Response response = keycloakManager.getKeycloakInstanceWithRealm().users().create(userRepresentation);
        return response.getStatus();
    }

    public void updateUser(UserRepresentation userRepresentation){
        keycloakManager.getKeycloakInstanceWithRealm().users().get(userRepresentation.getId()).update(userRepresentation);

    }

    public List<UserRepresentation> getListOfAllUsersByEmail(String email){
        return keycloakManager.getKeycloakInstanceWithRealm().users().search(email);
    }

    public UserRepresentation getUserUsingAuthId(String authId){
        try {
            UserResource userResource = keycloakManager.getKeycloakInstanceWithRealm().users().get(authId);
            return userResource.toRepresentation();
        }catch (Exception e){
            throw new UserNotFoundException("user not found under given Id");
        }
    }



}
