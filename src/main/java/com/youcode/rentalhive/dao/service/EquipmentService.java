package com.youcode.rentalhive.dao.service;

import com.youcode.rentalhive.controller.EquipmentController;
import com.youcode.rentalhive.dao.model.Equipment;

import java.util.List;


public interface EquipmentService {
      List<Equipment> selectAll();

      Equipment addEquipment(Equipment equipment);
}

