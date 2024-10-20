package com.uco.RoomuxApi.RommuxApi.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.uco.RoomuxApi.RommuxApi.controller.response.RoomuxResponse;
import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.SalaDomain;
import com.uco.RoomuxApi.RommuxApi.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class SalaController {

    @Autowired
    private SalaService salaService;


    @GetMapping("/format/sala")
    public ResponseEntity<RoomuxResponse<SalaDomain>> get_dummy(){
        RoomuxResponse<SalaDomain> response = new RoomuxResponse<>();
        try{
            response.setData(SalaDomain.createWithDefaults());
            response.setMessage("OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setMessage("Ocurrió un error inesperado, intente nuevamente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("sala")
    public ResponseEntity<RoomuxResponse<SalaDomain>> consultByName(@RequestBody SalaDomain sala){
        RoomuxResponse<SalaDomain> response = new RoomuxResponse<>();
        try {
            response.setMessage("Exito en la operacion");
            response.setData(salaService.consultByName(sala));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RoomuxApiException r){
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("sala/all")
    public ResponseEntity<RoomuxResponse<List<SalaDomain>>> consultAll(){
        RoomuxResponse<List<SalaDomain>> response = new RoomuxResponse<>();
        try {
            response.setMessage("Exito en la operacion");
            response.setData(salaService.consultAll());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RoomuxApiException r){
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/sala")
    public ResponseEntity<RoomuxResponse<String>> create(@RequestBody SalaDomain sala){
        RoomuxResponse<String> response = new RoomuxResponse<>();
        try {
            response.setMessage("Se creo correctamente");
            salaService.create(sala);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(RoomuxApiException r){
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("sala/name")
    public ResponseEntity<RoomuxResponse<String>> deleteByName(@RequestParam String name){
        RoomuxResponse response = new RoomuxResponse();
        try{
            response.setData("Se elimino correctamente");
            salaService.deleteByName(name);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RoomuxApiException r){
            response.setData(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            response.setData(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @DeleteMapping("sala/id")
    public ResponseEntity<RoomuxResponse<String>> deleteById(@RequestParam UUID uuid){
        RoomuxResponse response = new RoomuxResponse();
        try{
            response.setData("Se elimino correctamente");
            salaService.deleteByUUID(uuid);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RoomuxApiException r){
            response.setData(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            response.setData(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }



}
