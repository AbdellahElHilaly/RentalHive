package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.dao.service.ReservationService;
import com.youcode.rentalhive.validation.ReservationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/api/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/api/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(reservationService.findByIdOrThrow(Long.valueOf(id)));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().header("Message", "Invalid reservation Id format").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        }
    }

    @PostMapping("/api/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            ReservationValidator reservationValidator = new ReservationValidator();
            reservationValidator.validate(reservation);
            return ResponseEntity.ok(reservationService.insert(reservation).orElse(null));
        } catch (IllegalArgumentException e) {
            // Handle specific validation exception for invalid input
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.badRequest().header("Message", "Failed to create reservation").build();
        }
    }


    @PutMapping("/api/reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        try {
            return ResponseEntity.ok(reservationService.update(reservation, Long.valueOf(id)).orElse(null));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().header("Message", "Invalid reservation Id format").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/api/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String id){
        try {
            reservationService.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().header("Message", "Invalid reservation Id format").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        }

    }


}
