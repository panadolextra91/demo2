package com.example.demo.service;

import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.service.OrderService;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;

    @Transactional
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Transactional
    public List<Product> findProductsByName(String productName) {
        return productRepository.findByProductNameContaining(productName);
    }

    @Transactional
    public List<Product> findProductsByAreaId(Long areaId) {
        return productRepository.findByAreaAreaId(areaId);
    }

    @Transactional
    public List<Product> findProductsBySupplierId(Long supplierId) {
        return productRepository.findBySupplierSupplierId(supplierId);
    }

    @Transactional
    public void bulkDeleteProducts(List<Long> productIds) {
        productRepository.deleteAllById(productIds);
    }

    @Transactional
    public void updateProductStock(Long productId, Integer stock) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setStock(stock);
            productRepository.save(product);
        }
    }

    @Transactional
    public void updateProductPrice(Long productId, Double price) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setProductPrice(price);
            productRepository.save(product);
            updateOrdersWithProduct(product);
        }
    }

    private void updateOrdersWithProduct(Product product) {
        List<OrderItem> orderItems = product.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setPrice(product.getProductPrice() * orderItem.getQuantity());
            orderItem.getOrder().recalculateTotalPrice();
            orderService.saveOrder(orderItem.getOrder());
        }
    }
}
