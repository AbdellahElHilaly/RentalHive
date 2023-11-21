package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.service.EquipmentService;
import com.youcode.rentalhive.dao.service.impl.EquipmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

   @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }


    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.selectAll());
    }

    //getting a user  by id

    @GetMapping("/d/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable("id") Long id){
        return ResponseEntity.ok(equipmentService.selectById(id));
    }

    //getting a user  by id

    @GetMapping("/d/{id}")
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

    @PutMapping("updateEquipment/{id}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable Long id,
            @RequestBody Equipment updatedEquipmentData) {

        Optional<Equipment> updatedEquipment = equipmentService.updateEquipment(id, updatedEquipmentData);

        return updatedEquipment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("deleteEquipment/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.ok("Equipment with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete equipment with ID " + id + ": " + e.getMessage());
        }
    }


}
