package com.example.demo.repository;

import com.example.demo.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByAreaNameContaining(String areaName);
    List<Area> findByInventoryManagerImId(Long imId);
}
