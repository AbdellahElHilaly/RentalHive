package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/addEquipment")
    public ResponseEntity<Equipment> add(){
        try{

            Equipment equipment = Equipment.builder()
                    .name("trax")
                    .id(1L)
                    .Quantity(44)
                    .build();

            Equipment savedEquipment = equipmentService.addEquipment(equipment);

            return ResponseEntity.ok(savedEquipment);

        }catch(Exception e){
            System.out.println(e);
            return null;
        }





//        Widget testWidget = Widget.builder()
//                .name("foo")
//                .id(1)
//                .build();




    }




}
