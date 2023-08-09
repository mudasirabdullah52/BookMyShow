package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.BookTicketRequestDto;
import com.example.BookMyShow.Model.ShowEntity;
import com.example.BookMyShow.Model.ShowSeatEntity;
import com.example.BookMyShow.Model.TicketEntity;
import com.example.BookMyShow.Model.UserEntity;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{

        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();

        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatEntity showSeat: showSeats){

            String seatNo = showSeat.getSeatNo();

            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo));
            bookedSeats.add(showSeat);

        }
//        check weather  the size of requested seated is equal to avliable seats
        if(bookedSeats.size()!=requestedSeats.size()){
            throw new Exception("Requested Seats not Available");
        }

        TicketEntity ticketEntity = new TicketEntity();


        double totalAmout = 0;
        double multiplier = showEntity.getMultiplier();

        String allotedSeats  = "";

        int rate = 0;
        //Calculating amount,setting bookedStatus, setting
        for(ShowSeatEntity bookedSeat: bookedSeats){

            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();

            allotedSeats = allotedSeats + seatNo + ",";

            if(seatNo.charAt(0)=='1')
                rate = 100;
            else
                rate = 200;

            totalAmout = totalAmout + multiplier*rate;
        }


        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmout);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);

        //Bidirectional mapping part is pending

        ticketRepository.save(ticketEntity);

        return "Sucessfully created a ticket";

    }
}
