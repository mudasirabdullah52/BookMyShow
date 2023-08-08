package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.UserRequestDto;
import com.example.BookMyShow.Model.UserEntity;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String createUser(UserRequestDto userRequestDto){


        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();

        try{
            userRepository.save(userEntity);

        }catch (Exception e){

            return "user couldn't be added";
        }
        return "user added Successfully";


    }
}
