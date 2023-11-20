package com.youcode.rentalhive.dao.repository;

import com.youcode.rentalhive.dao.model.Equipment;
//import com.youcode.rentalhive.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
     @Query("Select e From Equipment e where e.name Like %?1% OR e.quantity LIKE %?2%")
     List<Equipment> getsearchedEquipments(@Param("name") String searchByName, @Param("quantity") String searchByQuantity);
}
