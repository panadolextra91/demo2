package com.example.demo.controller;

import com.example.demo.model.OrderItem;
import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.findAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.findOrderItemById(id);
        if (orderItem != null) {
            return ResponseEntity.ok(orderItem);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderItemService.saveOrderItem(orderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        OrderItem existingOrderItem = orderItemService.findOrderItemById(id);
        if (existingOrderItem != null) {
            orderItem.setOrderItemId(id);
            return ResponseEntity.ok(orderItemService.saveOrderItem(orderItem));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByOrder")
    public ResponseEntity<List<OrderItem>> searchOrderItemsByOrder(@RequestParam Long orderId) {
        List<OrderItem> orderItems = orderItemService.findOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/searchByProduct")
    public ResponseEntity<List<OrderItem>> searchOrderItemsByProduct(@RequestParam Long productId) {
        return ResponseEntity.ok(orderItemService.findOrderItemsByProductId(productId));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteOrderItems(@RequestBody List<Long> orderItemIds) {
        orderItemService.bulkDeleteOrderItems(orderItemIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateQuantity")
    public ResponseEntity<Void> updateOrderItemQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        orderItemService.updateOrderItemQuantity(id, quantity);
        return ResponseEntity.noContent().build();
    }
}
