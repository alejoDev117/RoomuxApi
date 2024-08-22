package com.uco.RoomuxApi.RommuxApi.service.transformer;

import com.uco.RoomuxApi.RommuxApi.domain.PersonaDomain;
import com.uco.RoomuxApi.RommuxApi.entity.PersonaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonaTransformer {
    private PersonaTransformer(){

    }
    public static PersonaEntity domainToEntity(PersonaDomain persona){
        return PersonaEntity.builder().identificador(persona.getIdentificador())
                .nombre(persona.getNombre()).id(persona.getId()).correo(persona.getCorreo()).build();
    }

    public static PersonaDomain entityToDomain(PersonaEntity entity){
        return PersonaDomain.builder().identificador(entity.getIdentificador())
                .nombre(entity.getNombre()).id(entity.getId()).correo(entity.getCorreo()).build();
    }

    public static List<PersonaDomain> entityListToDomainList(List<PersonaEntity> list) {
        return list.stream()
                .map(entity -> PersonaDomain.builder()
                        .identificador(entity.getIdentificador())
                        .nombre(entity.getNombre())
                        .id(entity.getId())
                        .correo(entity.getCorreo())
                        .build())
                .collect(Collectors.toList());
    }
}
