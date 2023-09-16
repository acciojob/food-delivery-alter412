package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	@Autowired
	UserServiceImpl us;

	@GetMapping("/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto;
		try {
			 userDto = us.getUserByUserId(id);

			UserResponse response = new UserResponse();

			response.setEmail(userDto.getEmail());
			response.setUserId(userDto.getUserId());
			response.setFirstName(userDto.getFirstName());
			response.setLastName(userDto.getLastName());

			return response;
		}catch (Exception e){
			return new UserResponse();
		}
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = new UserDto();

        userDto.setUserId(String.valueOf(UUID.randomUUID()));
        userDto.setFirstName(userDetails.getFirstName());
        userDto.setLastName(userDetails.getLastName());
        userDto.setEmail(userDetails.getEmail());

		UserDto savedDto = us.createUser(userDto);
		UserResponse response = new UserResponse();
        response.setLastName(savedDto.getLastName());
        response.setFirstName(savedDto.getFirstName());
        response.setEmail(savedDto.getEmail());
        response.setUserId(savedDto.getUserId());

        return response;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{

		UserDto userDto = new UserDto();

        userDto.setUserId(id);
        userDto.setEmail(userDetails.getEmail());
        userDto.setFirstName(userDetails.getFirstName());
        userDto.setLastName(userDetails.getLastName());

        UserDto updatedDto = us.updateUser(id,userDto);

        UserResponse response = new UserResponse();
        response.setUserId(updatedDto.getUserId());
        response.setEmail(updatedDto.getEmail());
        response.setFirstName(updatedDto.getFirstName());
        response.setLastName(updatedDto.getLastName());

        return response;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{

		try{

			us.deleteUser(id);
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
			return operationStatusModel;

		}catch (Exception e){

			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.toString());
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
