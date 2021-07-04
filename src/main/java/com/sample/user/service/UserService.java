package com.sample.user.service;

import com.sample.user.core.ErrorStatus;
import com.sample.user.core.ObjectMapperHelper;
import com.sample.user.core.ServiceException;
import com.sample.user.dto.UserRequestParam;
import com.sample.user.dto.UserUpdateReq;
import com.sample.user.resonse.UserUpdateResponse;
import com.sample.user.entity.UserEntity;
import com.sample.user.dto.UserReq;
import com.sample.user.mapper.usermanagement.UserMapper;
import com.sample.user.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;

    /**
     *
     * @param eventId
     * @return  User entity list
     */
    /*public List<UserEntity> getUserListById(long eventId) {
        return mapper.getListByEnventId(eventId);
    }

    *//**
     *
     * @param param
     */
    @Transactional(rollbackFor = {Exception.class})
    public UserReq create(UserRequestParam param) {
        UserEntity entityExist = mapper.getById(param.getId());
        if (entityExist != null && !StringUtils.isEmpty(entityExist.getId())) {
            Map<String, Object> cause = new HashMap<>();
            cause.put("cause","Already same user_id exist");
            throw new ServiceException(cause, ErrorStatus.Err_EXIST);
        }

        if(!Utility.validateUser(param.getId())){
            Map<String, Object> cause = new HashMap<>();
            cause.put("cause","User is not valid. User must be half width character within 6 to 20 character length");
            throw new ServiceException(cause, ErrorStatus.Err_NO_UPDATE);
        }
        mapper.create(param.getId(), param.getPassword(), param.getName(), param.getComments());

        UserEntity entity = mapper.getById(param.getId());
        if(entity == null || StringUtils.isEmpty(entity.getId())){
            if (!ObjectUtils.isEmpty(entityExist)) {
                Map<String, Object> cause = new HashMap<>();
                cause.put("cause","User registration failed. Please try again later");
                throw new ServiceException(cause, ErrorStatus.Err_NO_EXIST);
            }
        }
        UserReq req = new UserReq();
        req.setUsers(entity);
        return req;
    }

    /**
     *
     * @param id
     * @return user entity
     */
    public UserReq singleUser(String id){
        UserEntity entity = mapper.getById(id);
        if(ObjectUtils.isEmpty(entity)){
            throw new ServiceException(ErrorStatus.Err_NO_EXIST);
        }
        if(entity.getName().isEmpty()){
            entity.setName(entity.getId());
        }
        UserReq req = new UserReq();
        req.setUsers(entity);
        return req;
    }

    public UserUpdateResponse updateUser(String id, UserUpdateReq param){
        UserEntity entity = mapper.getById(id);
        if(ObjectUtils.isEmpty(entity) || entity.getDeleteFlg().equalsIgnoreCase("true")){
            throw new ServiceException(ErrorStatus.Err_NO_EXIST);
        }

        if(StringUtils.isEmpty(param.getComments()) && StringUtils.isEmpty(param.getName())){
            throw new ServiceException("Required nickname or comment", ErrorStatus.Err_NO_UPDATE);
        }

        if(StringUtils.isEmpty(param.getComments())){
            param.setComments(entity.getComments());
        }

        if(StringUtils.isEmpty(param.getName())){
            param.setName(entity.getName());
        }
        mapper.updateUser(id, param.getName(), param.getComments());

        UserUpdateReq req = new UserUpdateReq();
        req.setName(param.getName());
        req.setComments(param.getComments());
        UserUpdateResponse res = new UserUpdateResponse();
        res.setRecipe(req);
        return res;
    }

    public void delete(String id){
        UserEntity entity = mapper.getById(id);
        if(ObjectUtils.isEmpty(entity)){
            throw new ServiceException(ErrorStatus.Err_NO_EXIST);
        }
        mapper.delete(id);
    }

    /**
     *
     * @param param
     * @return event Id and user email address
     * @throws Exception
     */
    /*@Transactional(rollbackFor = {Exception.class})
    public AddEventResponseDto addEvent(AddEventParam param) throws Exception {
        UserEventEntity userExist = mapper.getUserEventByEmail(param.getEmail(), param.getEventId());
        if (!ObjectUtils.isEmpty(userExist)) {
            throw new ServiceException(ErrorStatus.Err_EXIST);
        }
        int count = mapper.createUserEvent(param.getEventId(), param.getEmail());
        if (count < 1) {
            throw new ServiceException(ErrorStatus.Err_EVENTCREATE);
        }
        // Send Completion email
        EventEntity event = service.getById(param.getEventId());
        sendCompletionEmail(param.getEmail(), event.getName());
        //Prepare Response
        AddEventResponseDto response = new AddEventResponseDto();
        response.setEventId(param.getEventId());
        response.setEmail(param.getEmail());
        return response;
    }

    *//**
     *
     * @param email
     * @param eventId
     *//*
    @Transactional(rollbackFor = {Exception.class})
    public void deleteUserFromEvent(String email, long eventId) {
        int count = mapper.deleteUserEvent(email, eventId);
        if (count < 1) {
            throw new ServiceException(ErrorStatus.Err_EVENTDELETE);
        }
    }

    private void sendCompletionEmail(String email, String eventName) throws Exception {
        UserEntity entity = mapper.getById(email);

        String body = mailBody.replace(
                user, entity.getName())
                .replace(event, eventName);

        emailService.sendEMail(email, mailSubject, body);
    }*/
}
