package com.youcode.rentalhive.dao.repository;

import com.youcode.rentalhive.dao.model.Equipment;
//import com.youcode.rentalhive.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {


}
