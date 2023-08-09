package com.example.BookMyShow.Dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {

    LocalDate showDate;
    LocalTime showTime;
    String movieName;
    Integer theaterId;
    double multiplier;
}
