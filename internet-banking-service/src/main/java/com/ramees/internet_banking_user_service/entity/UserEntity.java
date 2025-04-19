package com.ramees.internet_banking_user_service.entity;

import com.ramees.internet_banking_user_service.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth_id")
    private String authId;

    private String identification;

    private  String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
