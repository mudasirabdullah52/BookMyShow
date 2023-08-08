package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.TheaterRequestDto;
import com.example.BookMyShow.Enum.SeatType;
import com.example.BookMyShow.Model.TheaterEntity;
import com.example.BookMyShow.Model.TheaterSeatEntity;
import com.example.BookMyShow.Repository.TheaterRepsoitory;
import com.example.BookMyShow.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepsoitory theaterRepsoitory;
    public String CreateTheater(TheaterRequestDto theaterRequestDto){

        TheaterEntity theater = TheaterEntity.builder().name(theaterRequestDto.getName()).city(theaterRequestDto.getCity()).address(theaterRequestDto.getAddress()).build();

        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();

        theater.setTheaterSeatEntityList(theaterSeats);

//        for each theater seat we are setting the theather I,e frogien key
        for(TheaterSeatEntity theaterSeat: theaterSeats){
            theaterSeat.setTheater(theater);
        }
        theaterRepsoitory.save(theater);

        return "Theater successfully added ";

    }
    private List<TheaterSeatEntity> createTheaterSeats(){
        List<TheaterSeatEntity> seats = new ArrayList<>();

        TheaterSeatEntity theaterSeat1 = new TheaterSeatEntity("1A", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeat2= new TheaterSeatEntity("1B", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeat3 = new TheaterSeatEntity("1C", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeat4 = new TheaterSeatEntity("1D", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeat5 = new TheaterSeatEntity("1E", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeat6 = new TheaterSeatEntity("2A", SeatType.CLASSIC,200);
        TheaterSeatEntity theaterSeat7 = new TheaterSeatEntity("2B", SeatType.CLASSIC,200);
        TheaterSeatEntity theaterSeat8 = new TheaterSeatEntity("2C", SeatType.CLASSIC,200);
        TheaterSeatEntity theaterSeat9 = new TheaterSeatEntity("2D", SeatType.CLASSIC,200);
        TheaterSeatEntity theaterSeat10 = new TheaterSeatEntity("2E", SeatType.CLASSIC,200);

        seats.add(theaterSeat1);
        seats.add(theaterSeat2);
        seats.add(theaterSeat3);
        seats.add(theaterSeat4);
        seats.add(theaterSeat5);
        seats.add(theaterSeat6);
        seats.add(theaterSeat7);
        seats.add(theaterSeat8);
        seats.add(theaterSeat9);
        seats.add(theaterSeat10);

     theaterSeatRepository.saveAll(seats);
     return seats;

    }

}
