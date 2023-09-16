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
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository or;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(orderDto.getOrderId());
        orderEntity.setUserId(orderDto.getUserId());
        orderEntity.setCost(orderDto.getCost());
        orderEntity.setItems(orderDto.getItems());
        orderEntity.setStatus(true);

        OrderEntity savedOrder = or.save(orderEntity);

        OrderDto response = new OrderDto();

        response.setId(savedOrder.getId());
        response.setOrderId(savedOrder.getOrderId());
        response.setCost(savedOrder.getCost());
        response.setItems(savedOrder.getItems());
        response.setStatus(savedOrder.isStatus());
        response.setUserId(savedOrder.getUserId());

        return response;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = or.findByOrderId(orderId);

        if(orderEntity==null){
            return new OrderDto();
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderEntity.getId());
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setStatus(orderEntity.isStatus());
        orderDto.setUserId(orderEntity.getUserId());

        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity oldOrder = or.findByOrderId(orderId);
        if(oldOrder==null){
            return new OrderDto();
        }

        oldOrder.setStatus(order.isStatus());
        oldOrder.setItems(order.getItems());
        oldOrder.setCost(order.getCost());
        oldOrder.setUserId(order.getUserId());

        OrderEntity updatedOrder = or.save(oldOrder);

        OrderDto response = new OrderDto();

        response.setId(updatedOrder.getId());
        response.setOrderId(updatedOrder.getOrderId());
        response.setCost(updatedOrder.getCost());
        response.setItems(updatedOrder.getItems());
        response.setStatus(updatedOrder.isStatus());
        response.setUserId(updatedOrder.getUserId());

        return response;
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

        for (OrderEntity orderEntity : orderEntities) {
            OrderDto orderDto = new OrderDto();

            orderDto.setId(orderEntity.getId());
            orderDto.setOrderId(orderEntity.getOrderId());
            orderDto.setCost(orderEntity.getCost());
            orderDto.setItems(orderEntity.getItems());
            orderDto.setStatus(orderEntity.isStatus());
            orderDto.setUserId(orderEntity.getUserId());

            ans.add(orderDto);

        }

        return ans;
    }
}