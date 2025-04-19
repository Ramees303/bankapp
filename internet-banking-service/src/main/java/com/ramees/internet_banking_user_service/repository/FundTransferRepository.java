package com.ramees.internet_banking_user_service.repository;

import com.ramees.internet_banking_user_service.entity.FundTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundTransferRepository extends JpaRepository<FundTransferEntity,Long> {
}
