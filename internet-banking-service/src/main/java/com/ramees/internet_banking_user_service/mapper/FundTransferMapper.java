package com.ramees.internet_banking_user_service.mapper;

import com.ramees.internet_banking_user_service.dto.FundTransfer;
import com.ramees.internet_banking_user_service.entity.FundTransferEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FundTransferMapper extends BaseMapper<FundTransferEntity, FundTransfer>{
    @Override
    public FundTransfer convertToDto(FundTransferEntity entity, Object... args) {
        FundTransfer dto=new FundTransfer();
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto);
        }

        return dto;
    }

    @Override
    public FundTransferEntity convertToEntity(FundTransfer dto, Object... args) {
        FundTransferEntity entity=new FundTransferEntity();
        if(dto!=null){
            BeanUtils.copyProperties(dto,entity);
        }
        return entity;
    }
}
