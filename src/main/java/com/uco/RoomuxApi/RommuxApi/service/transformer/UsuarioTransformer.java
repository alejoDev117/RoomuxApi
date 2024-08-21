package com.uco.RoomuxApi.RommuxApi.service.transformer;

import com.uco.RoomuxApi.RommuxApi.domain.PersonaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.entity.PersonaEntity;
import com.uco.RoomuxApi.RommuxApi.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioTransformer {

    private  UsuarioTransformer(){

    }

    public static UsuarioEntity domainToEntity(UsuarioDomain usuario){
        return UsuarioEntity.builder().correoElectronico(usuario.getCorreoElectronico()).
                password(usuario.getPassword()).build();
    }

}
