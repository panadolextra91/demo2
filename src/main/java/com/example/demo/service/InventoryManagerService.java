package com.example.demo.service;

import com.example.demo.model.InventoryManager;
import com.example.demo.repository.InventoryManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryManagerService {

    @Autowired
    private InventoryManagerRepository inventoryManagerRepository;

    @Transactional
    public List<InventoryManager> findAllInventoryManagers() {
        return inventoryManagerRepository.findAll();
    }

    @Transactional
    public InventoryManager findInventoryManagerById(Long id) {
        return inventoryManagerRepository.findById(id).orElse(null);
    }

    @Transactional
    public InventoryManager saveInventoryManager(InventoryManager inventoryManager) {
        return inventoryManagerRepository.save(inventoryManager);
    }

    @Transactional
    public void deleteInventoryManager(Long id) {
        inventoryManagerRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllInventoryManagers() {
        inventoryManagerRepository.deleteAll();
    }

    @Transactional
    public List<InventoryManager> findInventoryManagersByName(String userName) {
        return inventoryManagerRepository.findByUserNameContaining(userName);
    }

    @Transactional
    public List<InventoryManager> findInventoryManagersByEmail(String email) {
        return inventoryManagerRepository.findByEmailContaining(email);
    }

    @Transactional
    public void bulkDeleteInventoryManagers(List<Long> inventoryManagerIds) {
        inventoryManagerRepository.deleteAllById(inventoryManagerIds);
    }

    @Transactional
    public void updateInventoryManagerContact(Long id, String phone, String email) {
        InventoryManager inventoryManager = inventoryManagerRepository.findById(id).orElse(null);
        if (inventoryManager != null) {
            inventoryManager.setPhone(phone);
            inventoryManager.setEmail(email);
            inventoryManagerRepository.save(inventoryManager);
        }
    }

    @Transactional
    public void updateInventoryManagerPassword(Long id, String newPassword) {
        InventoryManager inventoryManager = inventoryManagerRepository.findById(id).orElse(null);
        if (inventoryManager != null) {
            inventoryManager.setPassword(newPassword);
            inventoryManagerRepository.save(inventoryManager);
        }
    }
}
