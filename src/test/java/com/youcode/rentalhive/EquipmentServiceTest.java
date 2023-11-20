package com.youcode.rentalhive;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.repository.EquipmentRepository;
import com.youcode.rentalhive.dao.service.EquipmentService;
import com.youcode.rentalhive.dao.service.impl.EquipmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository; // Assuming EquipmentRepository is a dependency of EquipmentService

    @InjectMocks
    private EquipmentServiceImpl equipmentService; // The service to be tested

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    public void testAddEquipment() {
        // Arrange
        Equipment equipmentToAdd = new Equipment();
        equipmentToAdd.setName("Excavator"); // Set necessary details

        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipmentToAdd); // Mock repository behavior

        // Act
        Equipment addedEquipment = equipmentService.addEquipment(equipmentToAdd);

        // Assert
        assertNotNull(addedEquipment); // Ensure equipment was added
        assertEquals("Excavator", addedEquipment.getName()); // Check if added equipment has the correct name

        verify(equipmentRepository, times(1)).save(any(Equipment.class)); // Verify that repository.save() was called once
    }

    @Test
    public void testUpdateEquipment_ValidIdAndName_ShouldReturnUpdatedEquipment() {
        // Arrange
        long equipmentId = 1L;
        Equipment existingEquipment = new Equipment();
        existingEquipment.setId(equipmentId);
        existingEquipment.setName("Old Equipment");

        Equipment updatedEquipment = new Equipment();
        updatedEquipment.setName("New Equipment");

        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(existingEquipment));
        when(equipmentRepository.save(any(Equipment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Equipment> result = equipmentService.updateEquipment(equipmentId, updatedEquipment);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("New Equipment", result.get().getName());
        assertEquals(equipmentId, result.get().getId());

        verify(equipmentRepository, times(1)).findById(equipmentId);
        verify(equipmentRepository, times(1)).save(any(Equipment.class));
    }

}
