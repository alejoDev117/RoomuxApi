package com.uco.RoomuxApi.RommuxApi.service.transformer;

import com.uco.RoomuxApi.RommuxApi.domain.PersonaDomain;
import com.uco.RoomuxApi.RommuxApi.entity.PersonaEntity;
import com.uco.RoomuxApi.RommuxApi.service.validator.PersonaValidator;
import org.springframework.stereotype.Component;

@Component
public class PersonaTransformer {
    private PersonaTransformer(){

    }
    public static PersonaEntity domainToEntity(PersonaDomain persona){
        return PersonaEntity.builder().identificador(persona.getIdentificador())
                .nombre(persona.getNombre()).id(persona.getId()).correo(persona.getCorreo()).build();
    }
}
