package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderServiceImpl os;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto orderDto = os.getOrderById(id);

		OrderDetailsResponse response = new OrderDetailsResponse();
		response.setOrderId(orderDto.getOrderId());
		response.setUserId(orderDto.getUserId());
		response.setCost(orderDto.getCost());
		response.setItems(orderDto.getItems());
		response.setStatus(orderDto.isStatus());

		return response;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {

		OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(String.valueOf(UUID.randomUUID()));

        orderDto.setUserId(order.getUserId());
        orderDto.setStatus(true);
        orderDto.setItems(order.getItems());
        orderDto.setCost(order.getCost());

        OrderDto savedDto = os.createOrder(orderDto);

        OrderDetailsResponse response = new OrderDetailsResponse();
        response.setOrderId(savedDto.getOrderId());
        response.setStatus(savedDto.isStatus());
        response.setItems(savedDto.getItems());
        response.setCost(savedDto.getCost());
        response.setUserId(savedDto.getUserId());

        return response;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(id);
        orderDto.setCost(order.getCost());
        orderDto.setItems(order.getItems());
        orderDto.setUserId(order.getUserId());
        orderDto.setStatus(true);

        OrderDto savedDto = os.updateOrderDetails(id,orderDto);

        OrderDetailsResponse response = new OrderDetailsResponse();
        response.setOrderId(savedDto.getOrderId());
        response.setUserId(savedDto.getUserId());
        response.setCost(savedDto.getCost());
        response.setItems(savedDto.getItems());
        response.setStatus(savedDto.isStatus());

        return response;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		try{
			os.deleteOrder(id);
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
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> orderDtos = os.getOrders();
		List<OrderDetailsResponse> responses = new ArrayList<>();

		for(OrderDto x: orderDtos){
			OrderDetailsResponse temp = new OrderDetailsResponse();
			temp.setOrderId(x.getOrderId());
			temp.setUserId(x.getUserId());
			temp.setStatus(x.isStatus());
			temp.setCost(x.getCost());
			temp.setItems(x.getItems());

			responses.add(temp);
		}

		return responses;
	}
}
