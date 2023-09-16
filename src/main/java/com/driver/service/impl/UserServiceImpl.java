package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository ur;


    @Override
    public UserDto createUser(UserDto userDto) throws Exception {

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userDto.getEmail());
        userEntity.setUserId(userDto.getUserId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity savedUser = ur.save(userEntity);

        UserDto response = new UserDto();

        response.setId(savedUser.getId());
        response.setUserId(savedUser.getUserId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());

        return response;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = ur.findByEmail(email);

        UserDto response = new UserDto();

        response.setId(userEntity.getId());
        response.setUserId(userEntity.getUserId());
        response.setEmail(userEntity.getEmail());
        response.setFirstName(userEntity.getFirstName());
        response.setLastName(userEntity.getLastName());

        return response;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = ur.findByUserId(userId);

        if(userEntity==null){
            return new UserDto();
        }

        UserDto response = new UserDto();

        response.setId(userEntity.getId());
        response.setUserId(userEntity.getUserId());
        response.setEmail(userEntity.getEmail());
        response.setFirstName(userEntity.getFirstName());
        response.setLastName(userEntity.getLastName());

        return response;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = ur.findByUserId(userId);

        userEntity.setLastName(user.getLastName());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setEmail(user.getEmail());

        UserEntity updatedUser = ur.save(userEntity);

        UserDto response = new UserDto();

        response.setId(updatedUser.getId());
        response.setUserId(updatedUser.getUserId());
        response.setEmail(updatedUser.getEmail());
        response.setFirstName(updatedUser.getFirstName());
        response.setLastName(updatedUser.getLastName());

        return response;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity user = ur.findByUserId(userId);
        ur.delete(user);
    }

    @Override
    public List<UserDto> getUsers() {
        Iterable<UserEntity> users = ur.findAll();

        List<UserDto> ans = new ArrayList<>();

        for(UserEntity userEntity: users){
            UserDto response = new UserDto();

            response.setId(userEntity.getId());
            response.setUserId(userEntity.getUserId());
            response.setEmail(userEntity.getEmail());
            response.setFirstName(userEntity.getFirstName());
            response.setLastName(userEntity.getLastName());

            ans.add(response);
        }
        return ans;
    }
}