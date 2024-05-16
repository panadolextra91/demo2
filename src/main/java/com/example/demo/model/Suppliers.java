package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table (name = "Suppliers")
public class Suppliers {
    @Id
    @Column(name = "supplierId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    @Column(name = "supplierName")
    private String suppliersName;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    public Suppliers() {}

    // Getters and Setters
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public String getSuppliersName() { return suppliersName; }
    public void setSuppliersName(String suppliersName) { this.suppliersName = suppliersName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
