package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import com.uco.RoomuxApi.RommuxApi.messageService.messageReserva.ReservaMessageSender;
import com.uco.RoomuxApi.RommuxApi.service.validator.ReservaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaMessageSender reservaMessageSender;


    public void create(ReservaDomain reservaDomain) throws Exception {
        ReservaValidator.reservaIsValid(reservaDomain);
        try{
            reservaMessageSender.sendReservaMessage(reservaDomain,1);
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }

    public void getAll() throws Exception {
        try{
            reservaMessageSender.sendReservaMessage(ReservaDomain.createWithDefaults(),2);
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }

    public void consultByAutor(ReservaDomain reservaDomain) throws Exception {
        ReservaValidator.autorValidator(reservaDomain.getAutor());
        try{
            ReservaDomain reserva = ReservaDomain.createWithDefaults();
            reserva.setAutor(reservaDomain.getAutor());
            reservaMessageSender.sendReservaMessage(reserva,2);
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }
    public void consultBySala(ReservaDomain reservaDomain) throws Exception {
        ReservaValidator.salaValidator(reservaDomain.getSala());
        try{
            ReservaDomain reserva = ReservaDomain.createWithDefaults();
            reserva.setSala(reserva.getSala());
            reservaMessageSender.sendReservaMessage(reserva,2);
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }

    public void deleteById(UUID id) throws Exception {
        ReservaValidator.idReservaIsValid(id);
        try{
            ReservaDomain reserva = ReservaDomain.createWithDefaults();
            reserva.setIdentificador(id);
            reservaMessageSender.sendReservaMessage(reserva,3);
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }

    public void update(ReservaDomain domain) throws Exception {
        ReservaValidator.reservaIsValid(domain);
        try{
            reservaMessageSender.sendReservaMessage(domain,4);
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }
}
