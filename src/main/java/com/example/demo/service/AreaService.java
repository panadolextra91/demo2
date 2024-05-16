package com.example.demo.service;

import com.example.demo.model.Area;
import com.example.demo.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Transactional
    public List<Area> findAllAreas() {
        return areaRepository.findAll();
    }
    @Transactional
    public void bulkDeleteAreas(List<Long> areaIds) {
        areaRepository.deleteAllById(areaIds);
    }
    @Transactional
    public Area findAreaById(Long id) {
        return areaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Area saveArea(Area area) {
        return areaRepository.save(area);
    }

    @Transactional
    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllAreas() {
        areaRepository.deleteAll();
    }

    @Transactional
    public List<Area> findAreasByName(String areaName) {
        return areaRepository.findByAreaNameContaining(areaName);
    }

    @Transactional
    public List<Area> findAreasByInventoryManagerId(Long imId) {
        return areaRepository.findByInventoryManagerImId(imId);
    }
}
