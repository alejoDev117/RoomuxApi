package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import com.uco.RoomuxApi.RommuxApi.entity.ReservaEntity;
import com.uco.RoomuxApi.RommuxApi.repository.ReservaRepository;
import com.uco.RoomuxApi.RommuxApi.service.transformer.ReservaTransformer;
import com.uco.RoomuxApi.RommuxApi.service.transformer.SalaTransformer;
import com.uco.RoomuxApi.RommuxApi.service.validator.PersonaValidator;
import com.uco.RoomuxApi.RommuxApi.service.validator.ReservaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioService usuarioService;



    public void create(ReservaDomain reservaDomain) throws Exception {
        ReservaValidator.reservaIsValid(reservaDomain);
       var autor =  usuarioService.getByEmail(reservaDomain.getAutor().getCorreoElectronico());
        try{
            reservaDomain.setAutor(autor);
            reservaRepository.save(ReservaTransformer.domainToEntity(reservaDomain));
        }catch (DataAccessException r){
            throw new RoomuxApiException("No fue posible registrar la sala intente nuevamente");
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }

    public List<ReservaDomain> getAll() throws Exception {
        List<ReservaEntity> entities = new ArrayList<>();
        try{
            entities = reservaRepository.findAll();
        }catch (DataAccessException r){
            throw new RoomuxApiException("No fue posible encontrar reservas registradas");
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
        if(entities.isEmpty()){
            return new ArrayList<>();
        }else {
            return ReservaTransformer.entityListToDomainList(entities);
        }
    }

    public List<ReservaDomain> consultByAutor(String email) throws Exception {
        List<ReservaEntity> entities = new ArrayList<>();
        if(!PersonaValidator.emailIsValid(email)){
            throw new RoomuxApiException("Error, correo electronico no valido");
        }
        try{
            entities = reservaRepository.findByAutor(email);
        }catch (NullPointerException r){
            throw new RoomuxApiException("No se encontraron reservas con ese correo electronico");
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
        return ReservaTransformer.entityListToDomainList(entities);
    }
    public List<ReservaDomain> consultBySala(String sala) throws Exception {
        if(sala.equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error, debe ingresar un nombre valido");
        }
        List<ReservaEntity> entities = new ArrayList<>();
        try{
            entities = reservaRepository.findBySala(sala);
        }catch (DataAccessException r){
            throw new RoomuxApiException("Error, no se encontraron reservas con esa sala");
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
        if(entities.isEmpty()){
            return new ArrayList<>();
        }else{
            return ReservaTransformer.entityListToDomainList(entities);
        }
    }

    public void deleteById(UUID id) throws Exception {
        ReservaValidator.idReservaIsValid(id);
        try{
            reservaRepository.deleteById(id);
        }catch (DataAccessException r){
            throw new RoomuxApiException("Error no fue posible encontrar una reserva con el id");
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }



}
