package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.PersonaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.repository.PersonaRepository;
import com.uco.RoomuxApi.RommuxApi.service.transformer.PersonaTransformer;
import com.uco.RoomuxApi.RommuxApi.service.validator.PersonaValidator;
import jakarta.persistence.PersistenceException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Repository
public class PersonaService {

    @Autowired
    private final PersonaRepository personaRepository;
    @Autowired
    private final UsuarioService usuarioService;

    public PersonaService(PersonaRepository personaRepository, UsuarioService usuarioService) {
        this.personaRepository = personaRepository;
        this.usuarioService = usuarioService;
    }


    public  void create(PersonaDomain personaDomain) throws Exception {
        if(!PersonaValidator.nameIsValid(personaDomain.getNombre())){
            throw new RoomuxApiException("Error,el nombre no puede contener caracteres especiales o numeros");
        }
        if(!PersonaValidator.idIsValid(personaDomain.getId())){
            throw new RoomuxApiException("Error,el numero de indentificacion no posee un formato válido");
        }
        if(!PersonaValidator.emailIsValid(personaDomain.getCorreo())){
            throw new RoomuxApiException("Error,el correo electrónico no tiene el formato válido(nombre.apellido4digitos@uco.net.co");
        }
        UsuarioDomain usuarioARegistrar = UsuarioDomain.builder().
                correoElectronico(personaDomain.getCorreo()).
                password(personaDomain.getPassword()).build();

        try {
            usuarioService.create(usuarioARegistrar);
            personaRepository.save(PersonaTransformer.domainToEntity(personaDomain));
        }catch (DataAccessException e) {
            throw new PersistenceException("Error,ya existe un usuario registrado con ese numero de identificacion");
        } catch (Exception ex) {
            throw new Exception("Ocurrió un error inesperado,vuelva a intentarlo. Si el problema persiste contacte a un administrador");
        }

    }
    public  void update(int id){

    }
    public  void delete(String email){

    }
    public  void consult(String email){

    }


}
