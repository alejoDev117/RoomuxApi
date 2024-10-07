package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.domain.PersonaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.repository.PersonaRepository;
import com.uco.RoomuxApi.RommuxApi.repository.UsuarioRepository;
import com.uco.RoomuxApi.RommuxApi.service.transformer.PersonaTransformer;
import com.uco.RoomuxApi.RommuxApi.service.validator.PersonaValidator;
import jakarta.persistence.PersistenceException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

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
    public void update(PersonaDomain domain) throws Exception {
        if(!PersonaValidator.nameIsValid(domain.getNombre())){
            throw new RoomuxApiException("Error, el nuevo nombre no posee un formato válido, no puede tener números ni caracteres especiales");
        }
        if(!PersonaValidator.idIsValid(domain.getId())){
            throw new RoomuxApiException("Error, el identificador no posee un formato válido");
        }
        try {
            int rowsUpdated = personaRepository.updateNombre(domain.getNombre(), domain.getId());
            if (rowsUpdated == 0) {
                throw new RoomuxApiException("No fue posible encontrar un usuario registrado con ese identificador");
            }
        }catch (RoomuxApiException r){
            throw r;
        } catch (Exception e) {
            throw new Exception("Ocurrió un error inesperado, intente nuevamente y si el problema persiste contacte a un administrador");
        }
    }


    public  void delete(String email) throws Exception {
        if(!PersonaValidator.emailIsValid(email)){
            throw new RoomuxApiException("Error, el correo electrónico no tiene el formato válido");
        }
        try{
           int rows =  personaRepository.DeleteByEmail(email);
           if(rows == 0){
               throw new RoomuxApiException("Error, no fue posible encontrar un usuario con el correo electronico");
           }
            usuarioService.delete(email);
        }catch (RoomuxApiException r){
            throw r;
        }catch (Exception e){
            throw new Exception("Ocurrió un error inesperado, intente nuevamente y si el problema persiste contacte a un administrador");
        }
    }


    public PersonaDomain consultByEmail(String email) throws Exception {
        if(!PersonaValidator.emailIsValid(email)){
            throw new RoomuxApiException("El correo electrónico no posee un formato válido");
        }
        if(email.equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("El correo debe estar presente para la consulta");
        }
        try {
            var persona = personaRepository.findBycorreoElectronico(email)
                    .orElseThrow(() -> new RoomuxApiException("No se encuentra un usuario registrado con el correo electrónico"));
            return PersonaTransformer.entityToDomain(persona);
        } catch (RoomuxApiException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocurrió un error inesperado, intente nuevamente y si el problema persiste contacte a un administrador\n");
        }
    }


    public List<PersonaDomain> consultAll() throws RoomuxApiException {
        try {
            var personas = personaRepository.findAll();
            if(personas.isEmpty()){
                throw new RoomuxApiException("No se encontraron registros");
            }
            return PersonaTransformer.entityListToDomainList(personas);
        }catch (RoomuxApiException e) {
            throw e;
        }catch(Exception e){
            throw new RoomuxApiException("Ocurrió un error inesperado, por favor intente nuevamente y si el error persiste consulte con un administrador\n");
        }
    }


}
