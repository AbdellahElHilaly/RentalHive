package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getAllEquipments() {

        return ResponseEntity.ok(equipmentService.selectAll());

    }





}
