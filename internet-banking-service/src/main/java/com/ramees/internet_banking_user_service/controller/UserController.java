package com.ramees.internet_banking_user_service.controller;

import com.ramees.internet_banking_user_service.dto.User;
import com.ramees.internet_banking_user_service.request.UserUpdateRequest;
import com.ramees.internet_banking_user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bank-user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<User> updateUserStatus(@PathVariable("id") Long userId,@RequestBody UserUpdateRequest userUpdateRequest){
        return  new ResponseEntity<>(userService.updateUserStatus(userUpdateRequest,userId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsersByPagination(pageable),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }




}
