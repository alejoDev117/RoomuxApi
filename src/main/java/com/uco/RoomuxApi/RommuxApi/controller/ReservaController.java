package com.uco.RoomuxApi.RommuxApi.controller;

import com.uco.RoomuxApi.RommuxApi.controller.response.RoomuxResponse;
import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import com.uco.RoomuxApi.RommuxApi.service.ReservaService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1")
@RestController
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

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

    @PostMapping("reserva")
    public ResponseEntity<RoomuxResponse<String>> create(@RequestBody ReservaDomain reserva){
        RoomuxResponse<String> response = new RoomuxResponse<>();
        try {
            response.setMessage("Enviado con exito");
            reservaService.create(reserva);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (RoomuxApiException r) {
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
