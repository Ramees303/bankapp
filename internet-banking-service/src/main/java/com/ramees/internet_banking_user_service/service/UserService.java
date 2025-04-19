package com.ramees.internet_banking_user_service.service;

import com.ramees.internet_banking_user_service.dto.User;
import com.ramees.internet_banking_user_service.entity.UserEntity;
import com.ramees.internet_banking_user_service.enums.UserStatus;
import com.ramees.internet_banking_user_service.exception.custom.EmailAlreadyExist;
import com.ramees.internet_banking_user_service.exception.custom.EmailNotFound;
import com.ramees.internet_banking_user_service.exception.custom.KeycloakUserCreationException;
import com.ramees.internet_banking_user_service.exception.custom.UserNotFoundException;
import com.ramees.internet_banking_user_service.internal.controller.BankingCoreRestClient;
import com.ramees.internet_banking_user_service.internal.response.UserResponse;
import com.ramees.internet_banking_user_service.mapper.UserMapper;
import com.ramees.internet_banking_user_service.repository.UserRepository;
import com.ramees.internet_banking_user_service.request.UserUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final KeycloakUserService keycloakUserService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BankingCoreRestClient bankingCoreRestClient;


    @Transactional
    public User createUser(User user) {

        List<UserRepresentation> userRepresentationList=keycloakUserService.getListOfAllUsersByEmail(user.getEmail());
        if(userRepresentationList.size()>0){
            throw new EmailAlreadyExist("email already exist");
        }

        UserResponse userResponse =bankingCoreRestClient.getUserByIdentification(user.getIdentification());

        if(userResponse.getEmail()!=null) {
            if (!userResponse.getEmail().equals(user.getEmail())) {
                throw new EmailNotFound("wrong email for this identificationNumber" + user.getIdentification());
            }
        }

        UserRepresentation userRepresentation=new UserRepresentation();
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getEmail());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setEnabled(false);

        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentation.setTemporary(false);

        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        Integer userCreationStatusCode= keycloakUserService.createUser(userRepresentation);
        if(userCreationStatusCode!=201){
           throw new KeycloakUserCreationException("failed to create user in keycloak");
        }
        List<UserRepresentation> userRepresentationUsingEmail=keycloakUserService.getListOfAllUsersByEmail(user.getEmail());
        user.setAuthId(userRepresentationUsingEmail.get(0).getId());
        user.setStatus(UserStatus.PENDING);
        user.setIdentification(UUID.randomUUID().toString());

        UserEntity userEntity =userRepository.save(userMapper.convertToEntity(user));

        return userMapper.convertToDto(userEntity);

    }

    public User updateUserStatus(UserUpdateRequest userUpdateRequest,Long userId) {

        UserEntity userEntity=userRepository.findById(userId)
                .orElseThrow( ()->new UserNotFoundException("user not found with this id "+ userId));

        if(userUpdateRequest.getUserStatus()== UserStatus.APPROVED){
            UserRepresentation userRepresentation=keycloakUserService.getUserUsingAuthId(userEntity.getAuthId());
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(true);
            keycloakUserService.updateUser(userRepresentation);
        }
        userEntity.setStatus(userUpdateRequest.getUserStatus());
        userRepository.save(userEntity);
        return userMapper.convertToDto(userEntity);

    }

    public List<User> getAllUsersByPagination(Pageable pageable) {
        List<UserEntity> users=userRepository.findAll(pageable).getContent();
        return userMapper.convertToDtoList(users);
    }


    public User getUserById(Long id) {
        UserEntity user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found with this id "+id));
        return userMapper.convertToDto(user);
    }
}

