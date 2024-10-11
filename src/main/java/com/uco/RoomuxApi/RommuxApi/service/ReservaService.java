package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import com.uco.RoomuxApi.RommuxApi.messageService.messageReserva.ReservaMessageSender;
import com.uco.RoomuxApi.RommuxApi.service.validator.ReservaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
