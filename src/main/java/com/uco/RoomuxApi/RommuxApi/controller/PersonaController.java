package com.uco.RoomuxApi.RommuxApi.controller;

import com.uco.RoomuxApi.RommuxApi.controller.response.RoomuxResponse;
import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.PersonaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
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
    public ResponseEntity<RoomuxResponse<PersonaDomain>> getDummy(){
        RoomuxResponse<PersonaDomain> response = new RoomuxResponse<>();
        response.setData(PersonaDomain.createWithDefaults());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("all/person")
    public ResponseEntity<RoomuxResponse<List<PersonaDomain>>> getAll(){
        RoomuxResponse<List<PersonaDomain>> response = new RoomuxResponse<>();
        try {
            response.setData(personaService.consultAll());
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (RoomuxApiException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        } catch (Exception r){
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/person")
    public ResponseEntity<RoomuxResponse<PersonaDomain>> getByEmail(@RequestParam String email){
        RoomuxResponse<PersonaDomain> response = new RoomuxResponse<>();
        try {
            response.setData(personaService.consultByEmail(email));
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (RoomuxApiException re) {
            response.setMessage(re.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<RoomuxResponse<String>> register(@RequestBody PersonaDomain persona){
        RoomuxResponse<String> response = new RoomuxResponse<>();
        try{
            personaService.create(persona);
            response.setMessage("Usuario Creado con exito!!!");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RoomuxApiException re){
            response.setMessage(re.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (PersistenceException pe){
            response.setMessage(pe.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/person")
    public ResponseEntity<RoomuxResponse<String>> update(@RequestBody PersonaDomain persona){
        RoomuxResponse<String> response = new RoomuxResponse<>();
        try {
            personaService.update(persona);
            response.setMessage("Datos del usuario actualizados con exito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (RoomuxApiException re){
            response.setMessage(re.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/person")
    public ResponseEntity<RoomuxResponse<String>> delete(@RequestParam String email){
        RoomuxResponse<String> response = new RoomuxResponse<>();
        try {
            personaService.delete(email);
            response.setMessage("Usuario eliminado con exito");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (RoomuxApiException r) {
            response.setMessage(r.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

}


