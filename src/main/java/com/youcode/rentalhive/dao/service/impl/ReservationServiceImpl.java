package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.repository.EquipmentRepository;
import com.youcode.rentalhive.dao.repository.ReservationRepository;
import com.youcode.rentalhive.dao.service.EquipmentService;
import com.youcode.rentalhive.dao.service.ReservationService;
import com.youcode.rentalhive.dao.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final EquipmentService equipmentService;

    private final UserService userService;

    private final EquipmentRepository equipmentRepository;

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        if (reservations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reservations found");
        }

        return reservations;
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {

        return Optional.of(findByIdOrThrow(id));
    }

    @Override
    public Optional<Reservation> insert(Reservation reservation) {
        // Check equipment availability before adding the reservation
        Equipment equipment = checkEquipmentAvailabilityAndDecrementQuantity(reservation);

        reservation.setEquipment(equipment);

        // Check user availability before adding the reservation
        User user = checkUserAvailability(reservation);

        // Set the user to the reservation
        reservation.setUser(user);

        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> update(Reservation reservation, Long id) {
        // Check if the reservation exists
        Reservation existingReservation = findByIdOrThrow(id);

        // Check user availability before Updating the reservation
        User user = checkUserAvailability(reservation);

        // Set the user to the reservation
        reservation.setUser(user);

        // Check if the equipment ID is different
        if (!existingReservation.getEquipment().getId().equals(reservation.getEquipment().getId())) {

            // Decrement the quantity of the existing equipment by 1
            Equipment existingEquipment = existingReservation.getEquipment();
            int existingQuantity = existingEquipment.getQuantity();

            if (existingQuantity <= 0) {
                // Handle the case where the quantity is already 0
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Equipment quantity is already 0");
            }

            existingEquipment.setQuantity(existingQuantity - 1);
            equipmentRepository.save(existingEquipment);

            // Increment the quantity of the old equipment by 1
            Equipment oldEquipment = equipmentRepository.findById(existingReservation.getEquipment().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Old Equipment not found"));

            oldEquipment.setQuantity(oldEquipment.getQuantity() + 1);
            equipmentRepository.save(oldEquipment);
        }

        // Set the equipment for the reservation
        Equipment updatedEquipment = checkEquipmentAvailability(reservation);
        reservation.setEquipment(updatedEquipment);

        // Save the updated reservation
        return Optional.of(reservationRepository.save(existingReservation));
    }



    @Override
    public void deleteById(Long id) {
        reservationRepository.delete(findByIdOrThrow(id));
    }

    @Override
    public Reservation findByIdOrThrow(Long id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid reservation Id:" + id)
        );
    }

    private Equipment checkEquipmentAvailabilityAndDecrementQuantity(Reservation reservation) {
        Equipment equipment = reservation.getEquipment();

        // Assuming userService.selectById(id) returns the User with the given ID
        Optional<Equipment> existingEquipment = Optional.ofNullable(equipmentService.selectById(equipment.getId()));

        // Check if the user is available
        if (existingEquipment == null || existingEquipment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not available for reservation");
        }
        if (existingEquipment.get().getQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment quantity is already at 0");
        } else {
            existingEquipment.get().setQuantity(existingEquipment.get().getQuantity() - 1);
        }
        return existingEquipment.get();
    }
    private Equipment checkEquipmentAvailability(Reservation reservation) {
        Equipment equipment = reservation.getEquipment();

        // Assuming userService.selectById(id) returns the User with the given ID
        Optional<Equipment> existingEquipment = Optional.ofNullable(equipmentService.selectById(equipment.getId()));

        // Check if the user is available
        if (existingEquipment == null || existingEquipment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not available for reservation");
        }

        return existingEquipment.get();
    }

    private User checkUserAvailability(Reservation reservation) {
        User user = reservation.getUser();

        // Assuming userService.selectById(id) returns the User with the given ID
        Optional<User> existingUser = userService.selectById(user.getId());

        // Check if the user is available
        if (existingUser == null || existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not available for reservation");
        }
        return existingUser.get();
    }



}
