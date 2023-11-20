package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.service.EquipmentService;
import com.youcode.rentalhive.dao.service.impl.EquipmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EquipmentController {
    @Autowired
    private EquipmentServiceImpl equipmentServiceImpl;

    @GetMapping("/equipments")
    public ResponseEntity<Map<String, Object>> getAllSearchedEquipments(@RequestParam String search) {
        Map<String,Object> response = new HashMap<>();
        List<Equipment> equipments = equipmentServiceImpl.searchedEquipments(search);

        if (equipments.isEmpty()) {
            response.put("status", "error");
            response.put("message", "search is empty");

            return ResponseEntity.badRequest().body(response);
       }
        response.put("status", "sucess");
        response.put("equipments", equipments);

        return ResponseEntity.ok(response);
    }

//    @Autowired
//     public EquipmentController(EquipmentService equipmentService) {
//         this.equipmentService = equipmentService;
//     }


//     @GetMapping("/equipments")
//     public ResponseEntity<List<Equipment>> getAllEquipments() {
//         return ResponseEntity.ok(equipmentService.selectAll());
//     }

//     @PostMapping("/addEquipment")
//     public ResponseEntity<Equipment> add() {
//      try {
//          Equipment equipment = Equipment.builder()
//                  .name("trax")
//                  .id(1L)
//                  .Quantity(44)
//                  .build();
//          Equipment savedEquipment = equipmentService.addEquipment(equipment);

//          return ResponseEntity.ok(savedEquipment);

//      }catch (Exception e) {
//          System.out.println(e);

//          return null;
//      }
//        Widget testWidget = Widget.builder()
//                .name("foo")
//                .id(1)
//                .build();
//     }
}
