package com.ramees.core_banking_service.controller;

import com.ramees.core_banking_service.dto.User;
import com.ramees.core_banking_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Slf4j
@RequestMapping(path="/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{identification}")
    public ResponseEntity<User> getUserByIdentification(@PathVariable("identification") String identificationNumber){
        return new ResponseEntity<>(userService.getUserByIdentification(identificationNumber), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUserByPagination(Pageable pageable){
       return new ResponseEntity<>(userService.getAllUserByPagination(pageable),HttpStatus.OK);
    }


}
