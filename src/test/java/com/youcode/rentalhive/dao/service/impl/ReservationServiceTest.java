package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.controller.ReservationController;
import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.repository.EquipmentRepository;
import com.youcode.rentalhive.dao.repository.ReservationRepository;
import com.youcode.rentalhive.dao.service.EquipmentService;
import com.youcode.rentalhive.dao.service.UserService;
import com.youcode.rentalhive.validation.ReservationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private EquipmentService equipmentService;

    @Mock
    private UserService userService;

    @Mock
    private EquipmentRepository equipmentRepository;

    @InjectMocks
    private ReservationController reservationController;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() {
        // Arrange
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(new Reservation(), new Reservation()));

        // Act
        List<Reservation> reservations = reservationService.getAllReservations();

        // Assert
        assertEquals(2, reservations.size());
    }

    @Test
    void testGetReservationById() {
        // Arrange
        Long reservationId = 1L;
        Reservation mockReservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(mockReservation));

        // Act
        Optional<Reservation> result = reservationService.getReservationById(reservationId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockReservation, result.get());
    }

//    @Test
//    void testCreateReservation() {
//        // Arrange
//        Reservation inputReservation = new Reservation();
//        Reservation savedReservation = new Reservation(); // You can customize this based on your needs
//
//        // Mock the ReservationValidator
//        ReservationValidator reservationValidator = mock(ReservationValidator.class);
//        doNothing().when(reservationValidator).validate(inputReservation);
//
//        // Mock the ReservationService
//        when(reservationService.insert(any(Reservation.class))).thenReturn(Optional.of(savedReservation));
//
//        // Act
//        Reservation result = reservationController.createReservation(inputReservation);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(savedReservation, result);
//
//        // Verify that the ReservationValidator was called
//        verify(reservationValidator, times(1)).validate(inputReservation);
//
//        // Verify that the ReservationService was called
//        verify(reservationService, times(1)).insert(inputReservation);
//    }



//    @Test
//    void testUpdateReservation() {
//        // Arrange
//        Long reservationId = 1L;
//        Reservation existingReservation = new Reservation();
//        Reservation updatedReservation = new Reservation();
//        updatedReservation.setId(reservationId);
//
//        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));
//        when(equipmentService.selectById(anyLong())).thenReturn(new Equipment());
//        when(userService.selectById(anyLong())).thenReturn(Optional.of(new User()));
//        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.of(new Equipment()));
//
//        // Act
//        Optional<Reservation> result = reservationService.update(updatedReservation, reservationId);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(updatedReservation, result.get());
//    }

//    @Test
//    void testUpdateReservationWithNonExistingEquipment() {
//        // Arrange
//        Long reservationId = 1L;
//        Reservation existingReservation = new Reservation();
//        Reservation updatedReservation = new Reservation();
//        updatedReservation.setId(reservationId);
//
//        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));
//        when(equipmentService.selectById(anyLong())).thenReturn(new Equipment());
//        when(userService.selectById(anyLong())).thenReturn(Optional.of(new User()));
//        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Act and Assert
//        assertThrows(ResponseStatusException.class, () -> reservationService.update(updatedReservation, reservationId));
//    }

    @Test
    void testDeleteReservation() {
        // Arrange
        Long reservationId = 1L;
        Reservation existingReservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));

        // Act
        reservationService.deleteById(reservationId);

        // Assert
        verify(reservationRepository, times(1)).delete(existingReservation);
    }

    @Test
    void testDeleteNonExistingReservation() {
        // Arrange
        Long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reservationService.deleteById(reservationId));
    }
}
