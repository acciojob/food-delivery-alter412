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
    public UserDto createUser(UserDto user) throws Exception {
        user.setUserId(String.valueOf(UUID.randomUUID()));

        UserEntity ue = Mapper.userDtoToEntity(user);

        UserEntity savedUser = ur.save(ue);

        return Mapper.userEntityToDto(savedUser);
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = ur.findByEmail(email);
        return Mapper.userEntityToDto(userEntity);
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = ur.findByUserId(userId);
        return Mapper.userEntityToDto(userEntity);
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = ur.findByUserId(userId);

        userEntity.setLastName(user.getLastName());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setEmail(user.getEmail());

        UserEntity updatedUser = ur.save(userEntity);

        return Mapper.userEntityToDto(userEntity);
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
        for(UserEntity x: users){
            ans.add(Mapper.userEntityToDto(x));
        }
        return ans;
    }

//    public UserResponse createUser(UserDetailsRequestModel userDetails) throws Exception{
//        UserDto userDto = new UserDto();
//        userDto.setUserId(String.valueOf(UUID.randomUUID()));
//
//        userDto.setFirstName(userDetails.getFirstName());
//        userDto.setLastName(userDetails.getLastName());
//        userDto.setEmail(userDetails.getEmail());
//
//        UserDto savedDto = createUser(userDto);
//
//        UserResponse response = new UserResponse();
//        response.setLastName(savedDto.getLastName());
//        response.setFirstName(savedDto.getFirstName());
//        response.setEmail(savedDto.getEmail());
//        response.setUserId(savedDto.getUserId());
//
//        return response;
//    }
//
//    public UserResponse updateUser(String id, UserDetailsRequestModel userDetails) throws Exception {
//        UserDto userDto = new UserDto();
//
//        userDto.setUserId(id);
//        userDto.setEmail(userDetails.getEmail());
//        userDto.setFirstName(userDetails.getFirstName());
//        userDto.setLastName(userDetails.getLastName());
//
//        UserDto updatedDto = updateUser(id,userDto);
//
//        UserResponse response = new UserResponse();
//        response.setUserId(updatedDto.getUserId());
//        response.setEmail(updatedDto.getEmail());
//        response.setFirstName(updatedDto.getFirstName());
//        response.setLastName(updatedDto.getLastName());
//
//        return response;
//    }
}