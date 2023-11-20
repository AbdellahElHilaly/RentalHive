package com.youcode.rentalhive.validation;

import com.youcode.rentalhive.dao.model.Equipment;
import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.service.UserService;
import com.youcode.rentalhive.helper.ValidationHelper;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ReservationValidator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void validate(Reservation reservation) throws IllegalArgumentException {
        validateStartDate(reservation.getStartDate());
        validateEndDate(reservation.getEndDate());
    }


    private void validateStartDate(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.LENGTH_REQUIRED, "Start date is required");
        }

        try {
            validateDateFormat(startDate);

            LocalDate currentDate = LocalDate.now();
            LocalDate reservationStartDate = LocalDate.parse(startDate, DATE_FORMATTER);

            if (!reservationStartDate.isEqual(currentDate)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be today");
            }
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid start date: " + e.getMessage());
        }
    }

    private void validateEndDate(String endDate) throws ValidationException {
        if (endDate == null || endDate.isEmpty()) {
            throw new ValidationException("End date is required");
        }

        try {
            validateDateFormat(endDate);

            LocalDate currentDate = LocalDate.now();
            LocalDate reservationEndDate = LocalDate.parse(endDate, DATE_FORMATTER);

            if (reservationEndDate.isBefore(currentDate)) {
                throw new ValidationException("End date must be greater than the current date");
            }
        } catch (DateTimeParseException | ValidationException e) {
            throw new ValidationException("Invalid end date: " + e.getMessage());
        }
    }

    private void validateDateFormat(String date) throws DateTimeParseException {
        LocalDate.parse(date, DATE_FORMATTER);
    }


}
