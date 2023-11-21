package com.youcode.rentalhive.dao.service;

import com.youcode.rentalhive.controller.EquipmentController;
import com.youcode.rentalhive.dao.model.Equipment;

import java.util.List;
import java.util.Optional;


public interface EquipmentService {

      List<Equipment> selectAll();
      Equipment selectById(Long id);
      Equipment addEquipment(Equipment equipment);

      List<Equipment> searchedEquipments(String search);
}

