package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilUUID;
import com.uco.RoomuxApi.RommuxApi.domain.SalaDomain;
import com.uco.RoomuxApi.RommuxApi.messageService.messageSala.SalaMessageSender;
import com.uco.RoomuxApi.RommuxApi.service.validator.SalaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class SalaService {

    @Autowired
    private SalaMessageSender salaMessageSender;

    public void create(SalaDomain domain) throws Exception {
        if(domain.getNombreSala().equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error se debe ingresar el nombre de la sala");
        }
        if(!SalaValidator.nameLengthIsValid(domain.getNombreSala())){
            throw new RoomuxApiException("Error, la longitud del nombre excede los 15 caracteres validos");
        }
        SalaValidator.nameIsValid(domain.getNombreSala());
        if(!domain.getReservas().isEmpty()){
            throw new RoomuxApiException("Error, al crear por primera vez una sala no debe poseer reservas");
        }
        try{
            salaMessageSender.sendSalaMessage(domain,1);
        }catch (RoomuxApiException r){
            throw  r;
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }

    public void consultByName(SalaDomain domain) throws Exception {
        if(domain.getNombreSala().equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error se debe ingresar el nombre de la sala para consultar");
        }
        try{
            salaMessageSender.sendSalaMessage(domain,2);
        }catch (RoomuxApiException r) {
            throw r;
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }

    public  void consultAll(SalaDomain domain) throws Exception {
        domain.setNombreSala("ALL");
        try{
            salaMessageSender.sendSalaMessage(domain,2);
        }catch (RoomuxApiException r){
            throw  r;
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }

    public void deleteByName(String name) throws Exception {
        if(name.equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error se debe ingresar el nombre de la sala para eliminar");
        }
        SalaDomain domain = SalaDomain.createWithDefaults();
        domain.setNombreSala(name);
        try{
            salaMessageSender.sendSalaMessage(domain,3);
        }catch (RoomuxApiException r){
            throw  r;
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }
    public void deleteByUUID(UUID uuid) throws Exception {
        if(uuid.equals(UtilUUID.getUuidDefaultValue())){
            throw new RoomuxApiException("Error se debe ingresar el identificador para eliminar");
        }
        SalaDomain domain = SalaDomain.createWithDefaults();
        domain.setIdentificador(uuid);
        try{
            salaMessageSender.sendSalaMessage(domain,3);
        }catch (RoomuxApiException r){
            throw  r;
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }
}
