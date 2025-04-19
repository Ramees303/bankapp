package com.ramees.core_banking_service.repository;

import com.ramees.core_banking_service.entity.BankAccountEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository  extends JpaRepository<BankAccountEntity,Long> {
    Optional<BankAccountEntity> findByNumber(String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select b from BankAccountEntity b where b.number = :accountNumber")
    Optional<BankAccountEntity> findByNumberForUpdate(@Param("accountNumber")String  accountNumber);
}
