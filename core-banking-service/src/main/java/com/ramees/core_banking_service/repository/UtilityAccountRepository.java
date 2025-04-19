package com.ramees.core_banking_service.repository;

import com.ramees.core_banking_service.entity.UtilityAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilityAccountRepository extends JpaRepository<UtilityAccountEntity,Long> {

    Optional<UtilityAccountEntity> findByProviderName(String ProviderName);

    Optional<UtilityAccountEntity> findById(Long Id);
}
