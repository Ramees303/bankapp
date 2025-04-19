package com.ramees.core_banking_service.mapper;

import com.ramees.core_banking_service.dto.BankAccount;
import com.ramees.core_banking_service.entity.BankAccountEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper extends BaseMapper<BankAccountEntity, BankAccount>{
    @Override
    public BankAccount convertToDto(BankAccountEntity entity, Object... args) {
        BankAccount dto=new BankAccount();
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto,"user","transaction");
        }
        return dto;
    }

    @Override
    public BankAccountEntity convertToEntity(BankAccount dto, Object... args) {
        BankAccountEntity entity=new BankAccountEntity();
        if(dto!=null){
            BeanUtils.copyProperties(dto,entity,"user","transaction");
        }
        return entity;
    }
}
