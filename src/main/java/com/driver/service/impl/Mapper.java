package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;

public class Mapper {

    public static FoodEntity foodDtoToEntity(FoodDto foodDto){
        FoodEntity foodEntity = new FoodEntity();

        foodEntity.setFoodId(foodDto.getFoodId());
        //foodEntity.setId(foodDto.getId());
        foodEntity.setFoodName(foodDto.getFoodName());
        foodEntity.setFoodCategory(foodDto.getFoodCategory());
        foodEntity.setFoodPrice(foodDto.getFoodPrice());

        return foodEntity;
    }

    public static UserEntity userDtoToEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();

        //userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setUserId(userDto.getUserId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());


        return userEntity;
    }

    public static OrderEntity orderDtoToEntity(OrderDto orderDto){
        OrderEntity orderEntity = new OrderEntity();

        //orderEntity.setId(orderDto.getId());
        orderEntity.setOrderId(orderDto.getOrderId());
        orderEntity.setUserId(orderDto.getUserId());
        orderEntity.setCost(orderDto.getCost());
        orderEntity.setItems(orderDto.getItems());
        orderEntity.setStatus(orderDto.isStatus());

        return orderEntity;
    }

    public static FoodDto foodEntityToDto(FoodEntity foodEntity){
        FoodDto foodDto = new FoodDto();

        foodDto.setId(foodEntity.getId());
        foodDto.setFoodId(foodEntity.getFoodId());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());

        return foodDto;
    }

    public static UserDto userEntityToDto(UserEntity userEntity){
        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setUserId(userEntity.getUserId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());

        return userDto;
    }

    public static OrderDto orderEntityToDto(OrderEntity orderEntity){
        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderEntity.getId());
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setStatus(orderEntity.isStatus());
        orderDto.setUserId(orderEntity.getUserId());

        return orderDto;
    }

}
