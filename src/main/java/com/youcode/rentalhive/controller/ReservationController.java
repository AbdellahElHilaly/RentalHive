package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.dao.service.ReservationService;
import com.youcode.rentalhive.validation.ReservationValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/api/reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/api/reservations/{id}")
    public Reservation getReservationById(@PathVariable String id) {
        try {
            return reservationService.findByIdOrThrow(Long.valueOf(id));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Id format", e);
        } catch (IllegalArgumentException e) {
            if (e.getMessage() != null && e.getMessage().contains("Invalid reservation Id")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with this id not found", e);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/api/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        try {
            ReservationValidator reservationValidator = new ReservationValidator();
            reservationValidator.validate(reservation);
            return reservationService.insert(reservation).orElse(null);
        } catch (ValidationException ve) {
            // Handle specific validation exception for invalid input
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage(), ve);
        } catch (DataAccessException dae) {
            // Handle data access or database-related exceptions
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create reservation due to a database issue", dae);
        }
    }

    @PutMapping("/api/reservations/{id}")
    public Reservation updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        try {
            return reservationService.update(reservation, Long.valueOf(id)).orElse(null);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid reservation Id format", e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/api/reservation/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable String id) {
        try {
            reservationService.deleteById(Long.valueOf(id));
            return ResponseEntity.ok("Reservation deleted");
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid reservation Id format", e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
