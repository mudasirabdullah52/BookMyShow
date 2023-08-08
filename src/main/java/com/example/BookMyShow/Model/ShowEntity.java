package com.example.BookMyShow.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data

public class ShowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @CreationTimestamp
    private Date createdOn;

    @CreationTimestamp
    private Date updatedOn;

    @ManyToOne
    @JoinColumn
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfSeats;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<TicketEntity> listOfTickets;
}
