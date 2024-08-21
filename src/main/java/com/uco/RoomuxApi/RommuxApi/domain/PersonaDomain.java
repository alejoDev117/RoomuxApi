package com.uco.RoomuxApi.RommuxApi.domain;

import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilEmail;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilUUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PersonaDomain {
    private UUID identificador;
    private String nombre;
    private String id;
    private String correo;
    private String password;

    public static PersonaDomain createWithDefaults() {
        return PersonaDomain.builder()
                .identificador(UtilUUID.getUuidDefaultValue())
                .nombre(UtilText.getDefaultTextValue())
                .id(UtilText.getDefaultTextValue()) // Cambiado de 00000000000 a 0
                .correo(UtilEmail.getDefaultValueMail())
                .password(UtilText.getDefaultTextValue())
                .build();
    }

}
