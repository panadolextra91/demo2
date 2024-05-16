package com.example.demo.service;

import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Transactional
    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public OrderItem findOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        // Fetch product price
        Product product = productService.findProductById(orderItem.getProduct().getProductId());
        if (product != null) {
            orderItem.setPrice(product.getProductPrice() * orderItem.getQuantity());
        }
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        // Recalculate total price
        orderService.updateOrderTotalPrice(orderItem.getOrder().getOrderId());
        return savedOrderItem;
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = findOrderItemById(id);
        if (orderItem != null) {
            Order order = orderItem.getOrder();
            orderItemRepository.delete(orderItem);
            // Remove the order item from the order's list of items
            order.getOrderItems().remove(orderItem);
            // Recalculate total price
            orderService.updateOrderTotalPrice(order.getOrderId());
        }
    }

    @Transactional
    public void deleteAllOrderItems() {
        orderItemRepository.deleteAll();
        // Note: This might need further handling to update prices for all orders.
    }

    @Transactional
    public List<OrderItem> findOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderOrderId(orderId);
    }

    @Transactional
    public List<OrderItem> findOrderItemsByProductId(Long productId) {
        return orderItemRepository.findByProductProductId(productId);
    }

    @Transactional
    public void bulkDeleteOrderItems(List<Long> orderItemIds) {
        List<OrderItem> orderItemsToDelete = orderItemRepository.findAllById(orderItemIds);
        for (OrderItem orderItem : orderItemsToDelete) {
            Order order = orderItem.getOrder();
            orderItemRepository.delete(orderItem);
            // Remove the order item from the order's list of items
            order.getOrderItems().remove(orderItem);
            // Recalculate total price
            orderService.updateOrderTotalPrice(order.getOrderId());
        }
    }

    @Transactional
    public void updateOrderItemQuantity(Long orderItemId, Integer quantity) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if (orderItem != null) {
            // Update quantity
            orderItem.setQuantity(quantity);
            // Fetch product price
            Product product = productService.findProductById(orderItem.getProduct().getProductId());
            if (product != null) {
                // Update price based on product price and new quantity
                orderItem.setPrice(product.getProductPrice() * quantity);
            }
            orderItemRepository.save(orderItem);
            // Recalculate total price
            orderService.updateOrderTotalPrice(orderItem.getOrder().getOrderId());
        }
    }
}
