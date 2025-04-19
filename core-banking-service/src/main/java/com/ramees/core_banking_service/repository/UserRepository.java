package com.ramees.core_banking_service.repository;

import com.ramees.core_banking_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByIdentificationNumber(String identificationNumber);
}
