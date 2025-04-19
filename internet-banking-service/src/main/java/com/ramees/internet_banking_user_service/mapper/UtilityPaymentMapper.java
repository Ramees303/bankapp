package com.ramees.internet_banking_user_service.mapper;

import com.ramees.internet_banking_user_service.dto.UtilityPayment;
import com.ramees.internet_banking_user_service.entity.UtilityPaymentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilityPaymentMapper extends BaseMapper<UtilityPaymentEntity, UtilityPayment>{
    @Override
    public UtilityPayment convertToDto(UtilityPaymentEntity entity, Object... args) {
        UtilityPayment dto=new UtilityPayment();
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto);
        }
        return  dto;
    }

    @Override
    public UtilityPaymentEntity convertToEntity(UtilityPayment dto, Object... args) {

        UtilityPaymentEntity entity=new UtilityPaymentEntity();
        if(dto!=null){
            BeanUtils.copyProperties(dto,entity);
        }

        return entity;
    }
}
