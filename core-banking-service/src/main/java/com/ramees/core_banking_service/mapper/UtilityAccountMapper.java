package com.ramees.core_banking_service.mapper;

import com.ramees.core_banking_service.dto.UtilityAccount;
import com.ramees.core_banking_service.entity.UtilityAccountEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilityAccountMapper extends BaseMapper<UtilityAccountEntity, UtilityAccount>{

    @Override
    public UtilityAccount convertToDto(UtilityAccountEntity entity, Object... args) {
        UtilityAccount dto=new UtilityAccount();
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto);
        }
        return dto;
    }

    @Override
    public UtilityAccountEntity convertToEntity(UtilityAccount dto, Object... args) {
        UtilityAccountEntity entity=new UtilityAccountEntity();
        if(dto!=null){
            BeanUtils.copyProperties(dto,entity);
        }

        return entity;
    }
}
