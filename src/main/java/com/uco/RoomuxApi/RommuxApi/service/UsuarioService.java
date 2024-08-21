package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.repository.UsuarioRepository;
import com.uco.RoomuxApi.RommuxApi.service.transformer.UsuarioTransformer;
import jakarta.persistence.PersistenceException;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void create(UsuarioDomain usuarioDomain) throws Exception {
        try {
            usuarioRepository.save(UsuarioTransformer.domainToEntity(usuarioDomain));
        }catch(DataAccessException dae){
            throw new PersistenceException("Error, ya existe un usuario registrado previamente con el correo electrónico");
        }catch (Exception e){
            throw new Exception();
        }
    }
}

