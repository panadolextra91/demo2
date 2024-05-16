package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order saveOrder(Order order) {
        order.recalculateTotalPrice();
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    @Transactional
    public List<Order> findOrdersByDateRange(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    @Transactional
    public List<Order> findOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerCustomerId(customerId);
    }

    @Transactional
    public void bulkDeleteOrders(List<Long> orderIds) {
        orderRepository.deleteAllById(orderIds);
    }

    @Transactional
    public void updateOrderTotalPrice(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            double totalPrice = order.getOrderItems().stream()
                    .mapToDouble(orderItem -> orderItem.getPrice())
                    .sum();
            order.setTotalPrice(totalPrice);
            orderRepository.save(order);
        }
    }
}
