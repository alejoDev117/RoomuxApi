package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilDefaultObject;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilEmail;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.entity.UsuarioEntity;
import com.uco.RoomuxApi.RommuxApi.repository.UsuarioRepository;
import com.uco.RoomuxApi.RommuxApi.service.transformer.PersonaTransformer;
import com.uco.RoomuxApi.RommuxApi.service.transformer.UsuarioTransformer;
import com.uco.RoomuxApi.RommuxApi.service.validator.PersonaValidator;
import jakarta.persistence.PersistenceException;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void login(UsuarioDomain usuario) throws Exception {
        if(!PersonaValidator.emailIsValid(usuario.getCorreoElectronico())){
            throw new RoomuxApiException("Error,el correo electronico no posee un formato válido");
        }
        if(usuario.getCorreoElectronico().equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error, debe ingresarse el correo electronico");
        }
        if(usuario.getPassword().equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error, debe ingresarse la contraseña");
        }
        try {
             UsuarioEntity entity =  usuarioRepository.login(usuario.getCorreoElectronico(), usuario.getPassword());
             System.out.println(entity.equals(null));
        }catch (Exception e){
            throw new Exception("Code -1");
        }

    }
    public UsuarioDomain getByEmail(String email) throws RoomuxApiException {
        if(!PersonaValidator.emailIsValid(email)){
            throw new RoomuxApiException("Error el correo electrónico no posee el formato válido");
        }
        if(email.equals(UtilEmail.getDefaultValueMail())){
            throw new RoomuxApiException("Error electrónico no puede ser por defecto");
        }
        if(email.equals(UtilText.getDefaultTextValue())){
            throw new RoomuxApiException("Error debe ingresar un correo");
        }
        try{
            return UsuarioTransformer.entityToDomain(usuarioRepository.findByEmail(email));
        }catch (DataAccessException d){
            throw new RoomuxApiException("Error, no se encontró un usuario registrado con ese correo electrónico");
        }catch (Exception e){
            throw  e;
        }
    }

    public List<UsuarioDomain> get_all() throws RoomuxApiException {
        try {
            return UsuarioTransformer.entityListToDomainList(usuarioRepository.findAll());
        }catch(Exception e){
            throw new RoomuxApiException("Ocurrio un error inesperado, intente nuevamente o contacte con un administrador\n");

        }
    }

    protected void delete(String email) throws Exception {
        try{
            usuarioRepository.deleteByEmail(email);
        }catch (Exception e){
            throw e;
        }
    }
}

