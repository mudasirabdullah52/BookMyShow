package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.ShowRequestDto;
import com.example.BookMyShow.Model.*;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.ShowSeatRepository;
import com.example.BookMyShow.Repository.TheaterRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepsoitory theaterRepsoitory;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){

        ShowEntity showEntity = ShowEntity.builder().showTime(showRequestDto.getShowTime()).showDate(showRequestDto.getShowDate()).multiplier(showRequestDto.getMultiplier()).build();

        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        TheaterEntity theaterEntity = theaterRepsoitory.findById(showRequestDto.getTheaterId()).get();
//        Setting the forign key of the show table
        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);

//        Setting the bi-directional mapping
        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

//        Creating the show seat table
        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);
//      setting the  forrign key for show seat table

        for(ShowSeatEntity showSeat: seatEntityList){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepsoitory.save(theaterEntity);

//         here we don't need to save the child table because we are saving the parient table a
//        all the change will be refledted to the child table byself

//        showRepository.save(showEntity);

        return "show added Sccessfully";

    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){

        List<ShowSeatEntity> seats =  new ArrayList<>();

        for(TheaterSeatEntity theaterSeat: theaterSeatEntityList){

            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }
        showSeatRepository.saveAll(seats);
        return seats;
    }
}
