package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.repository.EquipmentRepository;
import com.youcode.rentalhive.dao.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository EquipmentRepository;
    @Override
    public List<Equipment> selectAll() {
        return EquipmentRepository.findAll();
    }

    @Override
    public Equipment selectById(Long id ){
        try{
            Equipment equipment =  EquipmentRepository.findById(id).get();
            return equipment;

        }catch(Exception e){
            return null;
        }

    }



    @Override
    public Equipment addEquipment(Equipment equipment){
            try{
                EquipmentRepository.save(equipment);
                //TODO : get the
                return equipment;
            }catch(Exception e){
                System.out.println(e);
                return null;
            }

    }

    @Override
    public Equipment deleteEquipment(Equipment equipment){
        //TODO : write the delete implementation using deleteById()

        return equipment;
    }


    @Override
    public Equipment updateEquipment(Equipment equipment){
        //TODO : write the update implementation
        return equipment;
    }


}
