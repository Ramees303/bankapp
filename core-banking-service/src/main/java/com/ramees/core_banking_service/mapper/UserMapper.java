package com.ramees.core_banking_service.mapper;

import com.ramees.core_banking_service.dto.User;
import com.ramees.core_banking_service.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserEntity, User>{

    BankAccountMapper bankAccountMapper=new BankAccountMapper();

    @Override
    public User convertToDto(UserEntity entity, Object... args) {
        User dto=new User();
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto,"bankAccount");
            dto.setBankAccount(bankAccountMapper.convertToDtoList(entity.getBankAccount()));
        }
        return dto;
    }

    @Override
    public UserEntity convertToEntity(User dto, Object... args) {
        UserEntity entity=new UserEntity();
        if(dto!=null){
            BeanUtils.copyProperties(dto,entity,"bankAccount");
            entity.setBankAccount(bankAccountMapper.convertToEntityList(dto.getBankAccount()));
        }

        return entity;
    }
}
