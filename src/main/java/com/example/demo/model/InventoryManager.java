package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Inventory_Manager")
public class InventoryManager {
    @Id
    @Column(name = "imId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public InventoryManager() {}

    // Getters and Setters
    public Long getImId() { return imId; }
    public void setImId(Long imId) { this.imId = imId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
