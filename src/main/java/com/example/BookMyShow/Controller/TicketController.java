package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dtos.BookTicketRequestDto;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @PostMapping("/add")
    public String addTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){

        try {
            return ticketService.bookTicket(bookTicketRequestDto);
        }catch (Exception e){
            return "Requested Seats not available";
        }

    }
}
