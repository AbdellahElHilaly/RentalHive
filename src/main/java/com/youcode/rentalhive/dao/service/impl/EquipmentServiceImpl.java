package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.repository.EquipmentRepository;
import com.youcode.rentalhive.dao.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public String deleteEquipment(Long id){
        //TODO : write the delete implementation using deleteById()
//        Long equipmentId = equipment.getId(); // Assuming 'getId()' returns the ID

        if (id != null) {
            // Use the equipment repository to delete by ID
            EquipmentRepository.deleteById(id);
        }

        return "equipment";
    }




    @Override
    public Optional<Equipment> updateEquipment(Long id, Equipment updatedEquipment){
        //TODO : write the update implementation

        Optional<Equipment> optionalEntity = EquipmentRepository.findById(id);

        if (optionalEntity.isPresent()) {
            Equipment existingEntity = optionalEntity.get();

            if(updatedEquipment.getName() != null){
                existingEntity.setName(updatedEquipment.getName());

            } else if (toInteger(updatedEquipment.getQuantity()) != null) {
                existingEntity.setQuantity(updatedEquipment.getQuantity());

            }
            Equipment savedEntity = EquipmentRepository.save(existingEntity);
            return Optional.of(savedEntity);
        } else {
            // handle the case where the entity with the given ID is not found
            return Optional.empty();
        }
    }

//i used this so i can compare int with null using != operator
    public Integer toInteger(int value){
        Integer convert = value;
        return convert;
    }


}
