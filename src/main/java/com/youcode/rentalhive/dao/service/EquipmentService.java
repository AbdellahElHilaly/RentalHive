package com.youcode.rentalhive.dao.service;

import com.youcode.rentalhive.controller.EquipmentController;
import com.youcode.rentalhive.dao.model.Equipment;

import java.util.List;


public interface EquipmentService {
      List<Equipment> selectAll();
      Equipment selectById(Long id);
      Equipment addEquipment(Equipment equipment);

      Equipment deleteEquipment(Equipment equipment);
      Equipment updateEquipment(Equipment equipment);
}

