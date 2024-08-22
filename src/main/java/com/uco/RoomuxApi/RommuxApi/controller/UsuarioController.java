package com.uco.RoomuxApi.RommuxApi.controller;

import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UsuarioController {

    @GetMapping("all/user")
    public ResponseEntity<List<UsuarioDomain>> getAll(){
        return null;
    }
    @GetMapping("/login")
    public ResponseEntity<UsuarioDomain> login(@RequestBody UsuarioDomain domain) {
        return null;
    }
    @GetMapping("/user")
    public ResponseEntity<UsuarioDomain> getByEmail(@RequestParam String email){
        return null;
    }
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteByEmail(@RequestParam String email){
        return null;
    }

}
