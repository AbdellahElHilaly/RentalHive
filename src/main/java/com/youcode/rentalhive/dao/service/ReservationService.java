package com.youcode.rentalhive.dao.service;

import com.youcode.rentalhive.dao.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    public List<Reservation> getAllReservations();
    public Optional<Reservation> getReservationById(Long id);
    public Optional<Reservation> insert(Reservation reservation);
    public Optional<Reservation> update(Reservation reservation, Long id);
    public void deleteById(Long id);
    public Reservation findByIdOrThrow(Long id);

}
