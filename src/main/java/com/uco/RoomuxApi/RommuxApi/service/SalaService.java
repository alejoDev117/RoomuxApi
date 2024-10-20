package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilDefaultObject;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilUUID;
import com.uco.RoomuxApi.RommuxApi.domain.SalaDomain;
import com.uco.RoomuxApi.RommuxApi.entity.SalaEntity;
import com.uco.RoomuxApi.RommuxApi.repository.SalaRepository;
import com.uco.RoomuxApi.RommuxApi.service.transformer.ReservaTransformer;
import com.uco.RoomuxApi.RommuxApi.service.transformer.SalaTransformer;
import com.uco.RoomuxApi.RommuxApi.service.validator.SalaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SalaService {


    @Autowired
    private final SalaRepository salaRepository;

    @Autowired
    private final ReservaService reservaService;

    public SalaService(SalaRepository salaRepository, ReservaService reservaService) {
        this.salaRepository = salaRepository;
        this.reservaService = reservaService;
    }

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
            salaRepository.save(SalaTransformer.domainToEntity(domain));
        }catch (DataAccessException r){
            throw  new RoomuxApiException("Error, ya existe una sala registrada con ese nombre");
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }

    public SalaDomain consultByName(SalaDomain domain) throws Exception {
        SalaEntity sala = null;
        if(domain.getNombreSala().equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error se debe ingresar el nombre de la sala para consultar");
        }
        try{
           sala = salaRepository.findByNombre(domain.getNombreSala());
           sala.setReservas(ReservaTransformer.domainListToEntityList(reservaService.consultBySala(domain.getNombreSala())));
        }catch (NullPointerException r) {
            throw new RoomuxApiException("Error,no se encontró una sala registrada con ese nombre");
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
        return SalaTransformer.entityToDomain(sala);
    }

    public List<SalaDomain> consultAll() throws Exception {
       List<SalaEntity> lista = new ArrayList<>();
        try {
           lista =  salaRepository.findAll();
           for (SalaEntity sala: lista){
               sala.setReservas(ReservaTransformer.domainListToEntityList(reservaService.consultBySala(sala.getNombre())));
           }
        }catch (DataAccessException r){
            throw  new RoomuxApiException("Error, no hay registros");
        }catch (Exception ie){
            ie.printStackTrace();
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
        if(lista.isEmpty()){
            return new ArrayList<>();
        }else {
            return SalaTransformer.entityListToDomainList(lista);
        }
    }

    public void deleteByName(String name) throws Exception {
        if(name.equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error se debe ingresar el nombre de la sala para eliminar");
        }
        try{
            salaRepository.deleteByNombre(name);
        }catch (DataAccessException r){
            throw new RoomuxApiException("Error, no es posible encontrar una sala registrada con ese nombre");
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }
    public void deleteByUUID(UUID uuid) throws Exception {
        if(uuid.equals(UtilUUID.getUuidDefaultValue())){
            throw new RoomuxApiException("Error se debe ingresar el identificador para eliminar");
        }

        try{
            salaRepository.deleteById(uuid);
        }catch (DataAccessException r){
            throw new RoomuxApiException("Error, no es posible encontrar una sala registrada con ese nombre");
        }catch (Exception ie){
            throw new Exception("Ocurrió un error inesperado intente nuevamente");
        }
    }


}
