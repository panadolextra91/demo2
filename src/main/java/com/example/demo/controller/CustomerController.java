package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.findCustomerById(id);
        if (existingCustomer != null) {
            customer.setCustomerId(id);
            return ResponseEntity.ok(customerService.saveCustomer(customer));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomersByName(@RequestParam String userName) {
        return ResponseEntity.ok(customerService.findCustomersByName(userName));
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<List<Customer>> searchCustomersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(customerService.findCustomersByEmail(email));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteCustomers(@RequestBody List<Integer> customerIds) {
        customerService.bulkDeleteCustomers(customerIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateContact")
    public ResponseEntity<Void> updateCustomerContact(@PathVariable int id, @RequestParam String phone, @RequestParam String address) {
        customerService.updateCustomerContact(id, phone, address);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updatePassword")
    public ResponseEntity<Void> updateCustomerPassword(@PathVariable int id, @RequestParam String newPassword) {
        customerService.updateCustomerPassword(id, newPassword);
        return ResponseEntity.noContent().build();
    }
}
