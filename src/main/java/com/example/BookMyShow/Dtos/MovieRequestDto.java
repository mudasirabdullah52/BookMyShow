package com.example.BookMyShow.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequestDto {
    private String name;
    private int duration;
    private Date releaseDate;
}
