package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.MovieRequestDto;
import com.example.BookMyShow.Model.MovieEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){

//        cONVERT THE DTO TO ENTITY FOR SAVING TO DATABASE
        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();

        try{
            movieRepository.save(movie);
        }catch (Exception e){

            return "Movie couldn't be added";
        }
        return "Movie added Successfully";
    }
}
