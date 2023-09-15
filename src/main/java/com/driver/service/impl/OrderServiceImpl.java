package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository or;

    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity oe = Mapper.orderDtoToEntity(order);
        OrderEntity savedOrder = or.save(oe);
        return Mapper.orderEntityToDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = or.findByOrderId(orderId);
        return Mapper.orderEntityToDto(orderEntity);
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity oldOrder = or.findByOrderId(orderId);

        oldOrder.setStatus(order.isStatus());
        oldOrder.setItems(order.getItems());
        oldOrder.setCost(order.getCost());
        oldOrder.setUserId(order.getUserId());

        OrderEntity updatedOrder = or.save(oldOrder);

        return Mapper.orderEntityToDto(updatedOrder);
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity order = or.findByOrderId(orderId);
        or.delete(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        Iterable<OrderEntity> orderEntities = or.findAll();
        List<OrderDto> ans = new ArrayList<>();

        for(OrderEntity x : orderEntities){
            ans.add(Mapper.orderEntityToDto(x));
        }

        return ans;
    }

    public OrderDetailsResponse createOrder(OrderDetailsRequestModel order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(String.valueOf(UUID.randomUUID()));

        orderDto.setUserId(order.getUserId());
        orderDto.setStatus(true);
        orderDto.setItems(order.getItems());
        orderDto.setCost(order.getCost());

        OrderDto savedDto = createOrder(orderDto);

        OrderDetailsResponse response = new OrderDetailsResponse();
        response.setOrderId(savedDto.getOrderId());
        response.setStatus(savedDto.isStatus());
        response.setItems(savedDto.getItems());
        response.setCost(savedDto.getCost());
        response.setUserId(savedDto.getUserId());

        return response;
    }

    public OrderDetailsResponse updateOrder(String id, OrderDetailsRequestModel order) throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(id);
        orderDto.setCost(order.getCost());
        orderDto.setItems(order.getItems());
        orderDto.setUserId(order.getUserId());
        orderDto.setStatus(true);

        OrderDto savedDto = updateOrderDetails(id,orderDto);

        OrderDetailsResponse response = new OrderDetailsResponse();
        response.setOrderId(savedDto.getOrderId());
        response.setUserId(savedDto.getUserId());
        response.setCost(savedDto.getCost());
        response.setItems(savedDto.getItems());
        response.setStatus(savedDto.isStatus());

        return response;
    }
}