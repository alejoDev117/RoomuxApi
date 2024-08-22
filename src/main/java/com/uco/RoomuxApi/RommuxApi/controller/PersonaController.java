package com.uco.RoomuxApi.RommuxApi.controller;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.PersonaDomain;
import com.uco.RoomuxApi.RommuxApi.service.PersonaService;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping("/format/register")
    public ResponseEntity<PersonaDomain> getDummy(){
        return new ResponseEntity<>(PersonaDomain.createWithDefaults(), HttpStatus.OK);
    }

    @GetMapping("all/person")
    public ResponseEntity<List<PersonaDomain>> getAll(){
        try{
            return new ResponseEntity<>(personaService.consultAll(),HttpStatus.OK);
        }catch (RoomuxApiException re){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/person")
    public ResponseEntity<PersonaDomain> getByEmail(@RequestParam String email){
        try {
            return new ResponseEntity<>(personaService.consultByEmail(email),HttpStatus.OK);
        } catch (RoomuxApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PersonaDomain persona){
        try{
            personaService.create(persona);
            return new ResponseEntity<>("Usuario Registrado Con Exito!!!",HttpStatus.OK);
        }catch (RoomuxApiException re){
            return new ResponseEntity<>(re.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (PersistenceException pe){
            return new ResponseEntity<>(pe.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
