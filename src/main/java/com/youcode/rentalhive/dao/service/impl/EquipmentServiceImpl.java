package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.repository.EquipmentRepository;
import com.youcode.rentalhive.dao.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private  EquipmentRepository EquipmentRepository;
    @Override
    public List<Equipment> selectAll() {
        return EquipmentRepository.findAll();
    }

    @Override
    public Equipment addEquipment(Equipment equipment){
            EquipmentRepository.save(equipment);
            return equipment;
    }

    @Override
    public List<Equipment> searchedEquipments(String search) {
        return EquipmentRepository.getsearchedEquipments(search, search);
    }

}
