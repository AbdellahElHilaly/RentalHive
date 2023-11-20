package com.youcode.rentalhive.validation;

import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.helper.ValidationHelper;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationValidator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ValidationHelper validationHelper = new ValidationHelper();


    public  void validate(Reservation reservation) throws IllegalArgumentException{
        validateStartDate(reservation.getStartDate());
        validateEndDate(reservation.getEndDate());

    }



    // ... other methods ...

    private void validateStartDate(String startDate)  {
        if (startDate == null || startDate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.LENGTH_REQUIRED, "End date is required");

        }

        LocalDate currentDate = LocalDate.now();
        LocalDate reservationStartDate = LocalDate.parse(startDate, DATE_FORMATTER);

        if (!reservationStartDate.isEqual(currentDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date must be greater than today");

        }
    }
    private void validateEndDate(String endDate) throws ValidationException {
        if (endDate == null || endDate.isEmpty()) {
            throw new ValidationException("End date is required");
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate reservationEndDate = LocalDate.parse(endDate, DATE_FORMATTER);

        if (reservationEndDate.isBefore(currentDate)) {
            throw new ValidationException("End date must be greater than the current date");
        }
    }

}
