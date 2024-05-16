package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Area")
public class Area {
    @Id
    @Column(name = "areaId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areaId;
    @Column(name = "areaName")
    private String areaName;

    @ManyToOne
    @JoinColumn(name = "im_id", nullable = false)
    private InventoryManager inventoryManager;

    public Area() {}

    // Getters and Setters
    public Long getAreaId() { return areaId; }
    public void setAreaId(Long areaId) { this.areaId = areaId; }
    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }
    public InventoryManager getInventoryManager() { return inventoryManager; }
    public void setInventoryManager(InventoryManager inventoryManager) { this.inventoryManager = inventoryManager; }
}
