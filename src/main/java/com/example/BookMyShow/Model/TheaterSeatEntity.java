package com.example.BookMyShow.Model;

import com.example.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seats")
@Data
@NoArgsConstructor
public class TheaterSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column( columnDefinition = "seat_no", nullable = false)
    private String seatNo;

    @Enumerated(value= EnumType.STRING)
    private SeatType seatType;

    private int rate;

    public TheaterSeatEntity(String seatNo, SeatType seatType, int rate) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.rate = rate;
    }

    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;
}
