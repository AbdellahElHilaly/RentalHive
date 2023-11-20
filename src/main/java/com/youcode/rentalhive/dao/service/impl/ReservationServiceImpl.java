package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.dao.repository.ReservationRepository;
import com.youcode.rentalhive.dao.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;


    @Override
    public List<Reservation> getAllReservations() {
        return (reservationRepository.findAll());
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return Optional.of(findByIdOrThrow(id));
    }

    @Override
    public Optional<Reservation> insert(Reservation reservation) {
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> update(Reservation reservation) {
        return Optional.of(reservationRepository.save(findByIdOrThrow(reservation.getId())));
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.delete(findByIdOrThrow(id));
    }

    @Override
    public Reservation findByIdOrThrow(Long id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> {
                    new IllegalArgumentException("Invalid reservation Id:" + id);
                    return null;
                }
        );
    }
}
