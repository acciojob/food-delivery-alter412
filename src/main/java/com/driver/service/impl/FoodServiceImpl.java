package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository fr;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = Mapper.foodDtoToEntity(food);
       FoodEntity savedFoodEntity = fr.save(foodEntity);
       FoodDto response = Mapper.foodEntityToDto(savedFoodEntity);
        return response;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = fr.findByFoodId(foodId);
        FoodDto response = Mapper.foodEntityToDto(foodEntity);
        return response;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {

        FoodEntity foodEntity = fr.findByFoodId(foodId);

        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());

        FoodEntity updatedFood = fr.save(foodEntity);

        FoodDto response = Mapper.foodEntityToDto(updatedFood);

        return response;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity response = fr.findByFoodId(id);
        fr.delete(response);
    }

    @Override
    public List<FoodDto> getFoods() {
       Iterable<FoodEntity> responses = fr.findAll();
       List<FoodDto> foodDtos = new ArrayList<>();

       for(FoodEntity x : responses){
           foodDtos.add(Mapper.foodEntityToDto(x));
       }

       return foodDtos;

    }

    public FoodDetailsResponse createFood(FoodDetailsRequestModel foodDetails) {
        FoodDto foodDto = new FoodDto();

        foodDto.setFoodId(String.valueOf(UUID.randomUUID()));
        foodDto.setFoodCategory(foodDetails.getFoodCategory());
        foodDto.setFoodPrice(foodDetails.getFoodPrice());
        foodDto.setFoodName(foodDetails.getFoodName());

        FoodDto responseDto = createFood(foodDto);

        FoodDetailsResponse response = new FoodDetailsResponse();

        response.setFoodId(responseDto.getFoodId());
        response.setFoodName(responseDto.getFoodName());
        response.setFoodCategory(responseDto.getFoodCategory());
        response.setFoodPrice(responseDto.getFoodPrice());

        return response;
    }

    public FoodDetailsResponse getFood(String id) throws Exception {
        FoodDto response = getFoodById(id);

        FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();

        foodDetailsResponse.setFoodPrice(response.getFoodPrice());
        foodDetailsResponse.setFoodCategory(response.getFoodCategory());
        foodDetailsResponse.setFoodName(response.getFoodName());
        foodDetailsResponse.setFoodId(response.getFoodId());

        return foodDetailsResponse;

    }

    public FoodDetailsResponse updateFood(String id, FoodDetailsRequestModel foodDetails) throws Exception {

        FoodDto foodDto = new FoodDto();

        foodDto.setFoodId(id);
        foodDto.setFoodCategory(foodDetails.getFoodCategory());
        foodDto.setFoodPrice(foodDetails.getFoodPrice());
        foodDto.setFoodName(foodDetails.getFoodName());

        FoodDto response = updateFoodDetails(id,foodDto);

        FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();

        foodDetailsResponse.setFoodId(response.getFoodId());
        foodDetailsResponse.setFoodName(response.getFoodName());
        foodDetailsResponse.setFoodCategory(response.getFoodCategory());
        foodDetailsResponse.setFoodPrice(response.getFoodPrice());

        return foodDetailsResponse;
    }
}