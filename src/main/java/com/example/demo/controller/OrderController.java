package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.findOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order existingOrder = orderService.findOrderById(id);
        if (existingOrder != null) {
            order.setOrderId(id);
            return ResponseEntity.ok(orderService.saveOrder(order));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<Order>> searchOrdersByDate(@RequestParam Date startDate, @RequestParam Date endDate) {
        return ResponseEntity.ok(orderService.findOrdersByDateRange(startDate, endDate));
    }

    @GetMapping("/filterByCustomer")
    public ResponseEntity<List<Order>> filterOrdersByCustomer(@RequestParam Long customerId) {
        return ResponseEntity.ok(orderService.findOrdersByCustomerId(customerId));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteOrders(@RequestBody List<Long> orderIds) {
        orderService.bulkDeleteOrders(orderIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateTotalPrice")
    public ResponseEntity<Void> updateOrderTotalPrice(@PathVariable Long id) {
        orderService.updateOrderTotalPrice(id);
        return ResponseEntity.noContent().build();
    }
}
