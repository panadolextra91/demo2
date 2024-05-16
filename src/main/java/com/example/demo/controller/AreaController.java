package com.example.demo.controller;

import com.example.demo.model.Area;
import com.example.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        return ResponseEntity.ok(areaService.findAllAreas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable Long id) {
        Area area = areaService.findAreaById(id);
        if (area != null) {
            return ResponseEntity.ok(area);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Area> createArea(@RequestBody Area area) {
        return ResponseEntity.ok(areaService.saveArea(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable Long id, @RequestBody Area area) {
        Area existingArea = areaService.findAreaById(id);
        if (existingArea != null) {
            area.setAreaId(id);
            return ResponseEntity.ok(areaService.saveArea(area));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        areaService.deleteArea(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteAreas(@RequestBody List<Long> areaIds) {
        areaService.bulkDeleteAreas(areaIds);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Area>> searchAreasByName(@RequestParam String name) {
        return ResponseEntity.ok(areaService.findAreasByName(name));
    }

    @GetMapping("/filterByManager")
    public ResponseEntity<List<Area>> filterAreasByManager(@RequestParam Long managerId) {
        return ResponseEntity.ok(areaService.findAreasByInventoryManagerId(managerId));
    }
}
