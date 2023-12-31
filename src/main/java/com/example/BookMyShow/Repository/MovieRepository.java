package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

     MovieEntity findByMovieName(String name);
}
