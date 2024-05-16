package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.findProductById(id);
        if (existingProduct != null) {
            product.setProductId(id);
            return ResponseEntity.ok(productService.saveProduct(product));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

    @GetMapping("/filterByArea")
    public ResponseEntity<List<Product>> filterProductsByArea(@RequestParam Long areaId) {
        return ResponseEntity.ok(productService.findProductsByAreaId(areaId));
    }

    @GetMapping("/filterBySupplier")
    public ResponseEntity<List<Product>> filterProductsBySupplier(@RequestParam Long supplierId) {
        return ResponseEntity.ok(productService.findProductsBySupplierId(supplierId));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteProducts(@RequestBody List<Long> productIds) {
        productService.bulkDeleteProducts(productIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateStock")
    public ResponseEntity<Void> updateProductStock(@PathVariable Long id, @RequestParam Integer stock) {
        productService.updateProductStock(id, stock);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updatePrice")
    public ResponseEntity<Void> updateProductPrice(@PathVariable Long id, @RequestParam Double price) {
        productService.updateProductPrice(id, price);
        return ResponseEntity.noContent().build();
    }
}
