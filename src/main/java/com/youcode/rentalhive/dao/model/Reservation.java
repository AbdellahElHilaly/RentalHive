package com.youcode.rentalhive.dao.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    // many reservations can be made by one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // many reservations can be made for one equipment
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private String startDate;
    private String endDate;

}
