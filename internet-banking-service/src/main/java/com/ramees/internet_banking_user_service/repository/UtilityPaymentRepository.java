package com.ramees.internet_banking_user_service.repository;


import com.ramees.internet_banking_user_service.entity.UtilityPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityPaymentRepository extends JpaRepository<UtilityPaymentEntity,Long> {
}
