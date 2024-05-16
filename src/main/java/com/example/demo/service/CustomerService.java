package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer findCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    @Transactional
    public List<Customer> findCustomersByName(String userName) {
        return customerRepository.findByUserNameContaining(userName);
    }

    @Transactional
    public List<Customer> findCustomersByEmail(String email) {
        return customerRepository.findByEmailContaining(email);
    }

    @Transactional
    public void bulkDeleteCustomers(List<Integer> customerIds) {
        customerRepository.deleteAllById(customerIds);
    }

    @Transactional
    public void updateCustomerContact(Integer id, String phone, String address) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setPhone(phone);
            customer.setAddress(address);
            customerRepository.save(customer);
        }
    }

    @Transactional
    public void updateCustomerPassword(Integer id, String newPassword) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setPassword(newPassword);
            customerRepository.save(customer);
        }
    }
}
