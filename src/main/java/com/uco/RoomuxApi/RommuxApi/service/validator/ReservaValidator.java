package com.uco.RoomuxApi.RommuxApi.service.validator;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.*;
import com.uco.RoomuxApi.RommuxApi.domain.DetalleReservaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.SalaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.domain.types.DiaSemana;
import com.uco.RoomuxApi.RommuxApi.domain.types.Frecuencia;
import com.uco.RoomuxApi.RommuxApi.domain.types.TipoReserva;
import com.uco.RoomuxApi.RommuxApi.service.ReservaService;
import jdk.jshell.execution.Util;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

public class ReservaValidator {

    private ReservaValidator(){

    }
    public static void idReservaIsValid(UUID id) throws RoomuxApiException {
        if(!UtilUUID.isValidUUID(String.valueOf(id))){
            throw new RoomuxApiException("Error, el identificador no posee un formato valido");
        }
    }

    public static void reservaIsValid(ReservaDomain reservaDomain) throws Exception {
        try{
            autorValidator(reservaDomain.getAutor());
            datesValidator(reservaDomain);
            typesValidator(reservaDomain);
            salaValidator(reservaDomain.getSala());
            detalleReservaValidator(reservaDomain.getDetalleReserva());
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado intente nuevamente o contacte con un administrador");
        }
    }

    public static void autorValidator(UsuarioDomain usuarioDomain) throws RoomuxApiException {
        if(usuarioDomain == null || usuarioDomain.equals(UsuarioDomain.createWithDefaults())){
            throw new RoomuxApiException("Error, el autor de la reserva debe estar y no puede ser valores por defecto");
        }
        if(!PersonaValidator.emailIsValid(usuarioDomain.getCorreoElectronico())){
            throw new RoomuxApiException("Error el correo electronico no posee un formato válido");
        }
        if(!usuarioDomain.getPassword().equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error solo puede consultarse por el correo electronico");
        }
    }

    public static void datesValidator(ReservaDomain reservaDomain) throws RoomuxApiException {
        if(UtilDateTime.isValidDate(String.valueOf(reservaDomain.getFechaInicio())) || UtilDateTime.isValidDate(String.valueOf(reservaDomain.getFechaFin()))){
            throw new RoomuxApiException("Error, la fecha inicio o fecha fin no posee un formato válido");
        }
        if(UtilDateTime.isValidDateTime(String.valueOf(reservaDomain.getHoraCreacion()))){
            throw new RoomuxApiException("Error, la hora de creacion no posee un formato valido");
        }
        if(reservaDomain.getFechaInicio().equals(UtilDateTime.getDefaultValueDate()) || reservaDomain.getFechaFin().equals(UtilDateTime.getDefaultValueDate())  ){
            throw new RoomuxApiException("la fecha inicio o fin no puede ser por defecto");
        }
        if(reservaDomain.getHoraCreacion().equals(UtilDateTime.getDefaultValueDateTime())){
            throw  new RoomuxApiException("la hora de creacion no debe ser por defecto");
        }
    }

    public static void typesValidator(ReservaDomain reservaDomain) throws RoomuxApiException {
        if(!UtilEnum.isValidEnumValue(TipoReserva.class,reservaDomain.getTipoReserva())){
            throw new RoomuxApiException("Error, el valor del tipo de reserva no es válido");
        }
        if(!UtilEnum.isValidEnumValue(Frecuencia.class,reservaDomain.getFrecuencia())){
            throw new RoomuxApiException("Error, el valor de la frecuencia no es válido");
        }
    }

    public static void salaValidator(SalaDomain salaDomain) throws RoomuxApiException {
        if(salaDomain.getNombreSala().equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error, la informacion minima de la sala debe estar presente");
        }
        if(!SalaValidator.nameLengthIsValid(salaDomain.getNombreSala())){
            throw new RoomuxApiException("Error, el nombre no posee una longitud valida");
        }
        SalaValidator.nameIsValid(salaDomain.getNombreSala());
    }

    public static void detalleReservaValidator(List<DetalleReservaDomain> list) throws RoomuxApiException {
        if(list.isEmpty()){
            throw new RoomuxApiException("Error, debe haber por lo menos 1 detalle reserva");
        }
        for (DetalleReservaDomain detalle: list){
            if(!UtilEnum.isValidEnumValue(DiaSemana.class,detalle.getDiaSemanal())){
                throw new RoomuxApiException("Error, el dia de la semana no posee un formato o valor válido");
            }
            if(UtilDateTime.isValidTime(String.valueOf(detalle.getHoraInicio())) || UtilDateTime.isValidTime(String.valueOf(detalle.getHoraInicio()))){
                throw new RoomuxApiException("Error, el formato o valor de la hora inicio o hora fin no son validos");
            }
            if(detalle.getHoraInicio().isAfter(detalle.getHoraFin())){
                throw new RoomuxApiException("Error, rango de las horas no es válido");
            }
        }
    }

}
