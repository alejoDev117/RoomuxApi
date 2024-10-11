package com.uco.RoomuxApi.RommuxApi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilDateTime;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilEnum;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilUUID;
import com.uco.RoomuxApi.RommuxApi.domain.types.DiaSemana;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Builder
@Getter
@Setter
public class DetalleReservaDomain {

    private UUID identificador;
    private String diaSemanal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaFin;
    public static DetalleReservaDomain createWithDefaults() {
        return DetalleReservaDomain.builder().
                identificador(UtilUUID.getUuidDefaultValue()).
                diaSemanal(UtilEnum.enumToString(DiaSemana.NO_ASINADO)).
                horaInicio(UtilDateTime.getDefaultValueTime()).
                horaFin(UtilDateTime.getDefaultValueTime()).build();
    }
    
}
