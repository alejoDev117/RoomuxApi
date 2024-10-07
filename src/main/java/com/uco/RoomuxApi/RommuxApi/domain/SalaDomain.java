package com.uco.RoomuxApi.RommuxApi.domain;

import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilDefaultObject;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilEmail;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilUUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class SalaDomain {
    private UUID identificador;
    private String nombreSala;
    private List<ReservaDomain> reservas;

    public static SalaDomain createWithDefaults() {
        return SalaDomain.builder().identificador(UtilUUID.getUuidDefaultValue()).
                nombreSala(UtilText.getDefaultTextValue()).reservas(new ArrayList<>()).build();
    }
}
