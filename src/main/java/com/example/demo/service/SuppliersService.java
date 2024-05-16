package com.example.demo.service;

import com.example.demo.model.Suppliers;
import com.example.demo.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuppliersService {

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Transactional
    public List<Suppliers> findAllSuppliers() {
        return suppliersRepository.findAll();
    }

    @Transactional
    public Suppliers findSupplierById(Long id) {
        return suppliersRepository.findById(id).orElse(null);
    }

    @Transactional
    public Suppliers saveSupplier(Suppliers supplier) {
        return suppliersRepository.save(supplier);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        suppliersRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllSuppliers() {
        suppliersRepository.deleteAll();
    }

    @Transactional
    public List<Suppliers> findSuppliersByName(String suppliersName) {
        return suppliersRepository.findBySuppliersNameContaining(suppliersName);
    }

    @Transactional
    public void bulkDeleteSuppliers(List<Long> supplierIds) {
        suppliersRepository.deleteAllById(supplierIds);
    }

    @Transactional
    public void updateSupplierAddress(Long supplierId, String address) {
        Suppliers supplier = suppliersRepository.findById(supplierId).orElse(null);
        if (supplier != null) {
            supplier.setAddress(address);
            suppliersRepository.save(supplier);
        }
    }

    @Transactional
    public void updateSupplierEmail(Long supplierId, String email) {
        Suppliers supplier = suppliersRepository.findById(supplierId).orElse(null);
        if (supplier != null) {
            supplier.setEmail(email);
            suppliersRepository.save(supplier);
        }
    }

    @Transactional
    public void updateSupplierPhone(Long supplierId, String phone) {
        Suppliers supplier = suppliersRepository.findById(supplierId).orElse(null);
        if (supplier != null) {
            supplier.setPhone(phone);
            suppliersRepository.save(supplier);
        }
    }
}
