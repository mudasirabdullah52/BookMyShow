package com.example.BookMyShow.Model;

import com.example.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "show_seats")
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    private SeatType seatType;

    private boolean booked;

    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;

}
