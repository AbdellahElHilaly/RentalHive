package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

//    @Autowired
//    public EquipmentController(EquipmentService equipmentService) {
//        this.equipmentService = equipmentService;
//    }


    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getAllEquipments() {

        return ResponseEntity.ok(equipmentService.selectAll());

    }

    //getting a user  by id

    @GetMapping("/equipments/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable("id") Long id){
        return ResponseEntity.ok(equipmentService.selectById(id));
    }

    @PostMapping("/addEquipment")
    public ResponseEntity<Equipment> add(@RequestParam String equipment_name , @RequestParam int Quantity){
        try{
//TODO : get the data from a form with http request
            Equipment equipment = Equipment.builder()
                    .name(equipment_name)
//                    .id(1L)
                    .Quantity(Quantity)
                    .build();

            Equipment savedEquipment = equipmentService.addEquipment(equipment);

            return ResponseEntity.ok(savedEquipment);

        }catch(Exception e){
            System.out.println(e);
            return null;
        }





    }




}
