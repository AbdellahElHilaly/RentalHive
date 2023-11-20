package com.youcode.rentalhive.validation;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.helper.ValidationHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationValidatorTest {

    private final ReservationValidator validator = new ReservationValidator();

    @Test
    void validateValidReservation() {
        Reservation reservation = createValidReservation();
        assertDoesNotThrow(() -> validator.validate(reservation));
    }

    @Test
    void validateInvalidQuantity() {
        Reservation reservation = createValidReservation();
        reservation.getEquipment().setQuantity(-55); // Set an invalid quantity
        assertThrows(IllegalArgumentException.class, () -> validator.validate(reservation));
    }

    @Test
    void validateZeroQuantity() {
        Reservation reservation = createValidReservation();
        reservation.getEquipment().setQuantity(0); // Set a quantity of 0
        assertThrows(IllegalArgumentException.class, () -> validator.validate(reservation));
    }

    @Test
    void validateEmptyStartDate() {
        Reservation reservation = createValidReservation();
        reservation.setStartDate(""); // Set an empty start date
        assertThrows(IllegalArgumentException.class, () -> validator.validate(reservation));
    }

    @Test
    void validateTodayStartDate() {
        Reservation reservation = createValidReservation();
        reservation.setStartDate(ValidationHelper.getCurrentDate()); // Set today's date as the start date
        assertThrows(IllegalArgumentException.class, () -> validator.validate(reservation));
    }

    @Test
    void validateEmptyEndDate() {
        Reservation reservation = createValidReservation();
        reservation.setEndDate(""); // Set an empty end date
        assertThrows(IllegalArgumentException.class, () -> validator.validate(reservation));
    }

    @Test
    void validateTodayEndDate() {
        Reservation reservation = createValidReservation();
        reservation.setEndDate(ValidationHelper.getCurrentDate()); // Set today's date as the end date
        assertThrows(IllegalArgumentException.class, () -> validator.validate(reservation));
    }

    @Test
    private Reservation createValidReservation() {
        User user = new User(); // Create a user
        Equipment equipment = new Equipment(); // Create an equipment
        equipment.setQuantity(678); // Set a valid quantity
        Reservation reservation = new Reservation(); // Create a reservation
        reservation.setUser(user);
        reservation.setEquipment(equipment);
        reservation.setStartDate("2023-11-20"); // Set a valid start date
        reservation.setEndDate("2023-11-22"); // Set a valid end date
        return reservation;
    }
}
