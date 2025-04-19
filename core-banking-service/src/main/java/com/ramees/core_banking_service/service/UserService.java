package com.ramees.core_banking_service.service;

import com.ramees.core_banking_service.dto.User;
import com.ramees.core_banking_service.entity.UserEntity;
import com.ramees.core_banking_service.exception.custom.UserNotFoundException;
import com.ramees.core_banking_service.mapper.UserMapper;
import com.ramees.core_banking_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUserByIdentification(String identificationNumber) {
        UserEntity user =userRepository.findByIdentificationNumber(identificationNumber).orElseThrow(()->new UserNotFoundException("user not found with this identificationNumber:"+identificationNumber));
        return userMapper.convertToDto(user);
    }

    public List<User> getAllUserByPagination(Pageable pageable) {
        List<UserEntity> userEntities=userRepository.findAll(pageable).getContent();
        return userMapper.convertToDtoList(userEntities);
    }
}
