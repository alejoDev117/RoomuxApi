package com.uco.RoomuxApi.RommuxApi.controller;

import com.uco.RoomuxApi.RommuxApi.controller.response.RoomuxResponse;
import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("all/user")
    public ResponseEntity<RoomuxResponse<List<UsuarioDomain>>> getAll(){
        RoomuxResponse<List<UsuarioDomain>> response = new RoomuxResponse<>();
        try{
            response.setData(usuarioService.getAll());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RoomuxApiException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }catch (Exception r){
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<RoomuxResponse<String>> login(@RequestBody UsuarioDomain domain) {
        RoomuxResponse<String> response = new RoomuxResponse<>();
        try{
            usuarioService.login(domain);
            response.setData("Code 1");
            response.setMessage("Usuario loggeado con éxito");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RoomuxApiException r){
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e) {
            response.setData("Code -1");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<RoomuxResponse<UsuarioDomain>> getByEmail(@RequestParam String email){
        RoomuxResponse<UsuarioDomain> response = new RoomuxResponse<>();
        try {
            response.setData(usuarioService.getByEmail(email));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(RoomuxApiException r){
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
