package com.ramees.internet_banking_user_service.repository;

import com.ramees.internet_banking_user_service.dto.User;
import com.ramees.internet_banking_user_service.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
