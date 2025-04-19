package com.ramees.internet_banking_user_service.mapper;

import com.ramees.internet_banking_user_service.dto.User;
import com.ramees.internet_banking_user_service.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserEntity, User>{



    @Override
    public User convertToDto(UserEntity entity, Object... args) {
        User dto=new User();
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto);
        }
        return dto;
    }

    @Override
    public UserEntity convertToEntity(User dto, Object... args) {
        UserEntity entity=new UserEntity();
        if(dto!=null){
            BeanUtils.copyProperties(dto,entity);
        }

        return entity;
    }
}
