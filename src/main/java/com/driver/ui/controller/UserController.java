package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	//@Autowired
	UserServiceImpl us = new UserServiceImpl();

	@GetMapping("/get/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto = us.getUserByUserId(id);

		UserResponse response = new UserResponse();

		response.setEmail(userDto.getEmail());
		response.setUserId(userDto.getUserId());
		response.setFirstName(userDto.getFirstName());
		response.setLastName(userDto.getLastName());

		return response;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserResponse response = us.createUser(userDetails);
		return response;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{

		UserResponse response = us.updateUser(id,userDetails);
		return response;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{

		try{

			us.deleteUser(id);
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
			return operationStatusModel;

		}catch (Exception e){

			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
			return operationStatusModel;
		}
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){

		List<UserDto> userDtos = us.getUsers();
		List<UserResponse> ans = new ArrayList<>();

		for(UserDto x : userDtos){
			UserResponse temp = new UserResponse();

			temp.setUserId(x.getUserId());
			temp.setEmail(x.getEmail());
			temp.setFirstName(x.getFirstName());
			temp.setLastName(x.getLastName());

			ans.add(temp);
		}


		return ans;
	}
	
}
