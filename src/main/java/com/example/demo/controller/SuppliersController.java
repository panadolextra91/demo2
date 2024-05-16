package com.example.demo.controller;

import com.example.demo.model.Suppliers;
import com.example.demo.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SuppliersController {

    @Autowired
    private SuppliersService suppliersService;

    @GetMapping
    public ResponseEntity<List<Suppliers>> getAllSuppliers() {
        return ResponseEntity.ok(suppliersService.findAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suppliers> getSupplierById(@PathVariable Long id) {
        Suppliers supplier = suppliersService.findSupplierById(id);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Suppliers> createSupplier(@RequestBody Suppliers supplier) {
        return ResponseEntity.ok(suppliersService.saveSupplier(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suppliers> updateSupplier(@PathVariable Long id, @RequestBody Suppliers supplier) {
        Suppliers existingSupplier = suppliersService.findSupplierById(id);
        if (existingSupplier != null) {
            supplier.setSupplierId(id);
            return ResponseEntity.ok(suppliersService.saveSupplier(supplier));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        suppliersService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Suppliers>> searchSuppliersByName(@RequestParam String name) {
        return ResponseEntity.ok(suppliersService.findSuppliersByName(name));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteSuppliers(@RequestBody List<Long> supplierIds) {
        suppliersService.bulkDeleteSuppliers(supplierIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateAddress")
    public ResponseEntity<Void> updateSupplierAddress(@PathVariable Long id, @RequestParam String address) {
        suppliersService.updateSupplierAddress(id, address);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateEmail")
    public ResponseEntity<Void> updateSupplierEmail(@PathVariable Long id, @RequestParam String email) {
        suppliersService.updateSupplierEmail(id, email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updatePhone")
    public ResponseEntity<Void> updateSupplierPhone(@PathVariable Long id, @RequestParam String phone) {
        suppliersService.updateSupplierPhone(id, phone);
        return ResponseEntity.noContent().build();
    }
}
