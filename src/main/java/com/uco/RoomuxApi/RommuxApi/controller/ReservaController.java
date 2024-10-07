package com.uco.RoomuxApi.RommuxApi.controller;

import com.uco.RoomuxApi.RommuxApi.controller.response.RoomuxResponse;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1")
@RestController
public class ReservaController {


    @GetMapping("reserva/format")
    public ResponseEntity<RoomuxResponse<ReservaDomain>> get_dummy(){
        RoomuxResponse<ReservaDomain> response = new RoomuxResponse<>();
        try{
            response.setData(ReservaDomain.createWithDefaults());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
