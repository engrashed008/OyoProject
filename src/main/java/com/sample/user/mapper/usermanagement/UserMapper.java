package com.sample.user.mapper.usermanagement;

import com.sample.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

   /* List<UserEntity> getListByEnventId(long eventId);

    Integer create(UserEntity entity);*/

    UserEntity getById(String Id);

    int updateUser(String id, String name, String comments);

    int delete(String id);

    int create(String id, String password, String name, String comments);

    /*Integer createUserEvent(long eventId, String email);

    UserEventEntity getUserEventByEmail(String email, long eventId);

    Integer deleteUserEvent(String email, long eventId);*/
}
