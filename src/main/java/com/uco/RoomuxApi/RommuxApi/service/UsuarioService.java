package com.uco.RoomuxApi.RommuxApi.service;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilEmail;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.entity.UsuarioEntity;
import com.uco.RoomuxApi.RommuxApi.messageService.messageUsuario.UsuarioMessageSender;
import com.uco.RoomuxApi.RommuxApi.repository.UsuarioRepository;
import com.uco.RoomuxApi.RommuxApi.service.transformer.UsuarioTransformer;
import com.uco.RoomuxApi.RommuxApi.service.validator.PersonaValidator;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Repository
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final UsuarioMessageSender usuarioMessageSender;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMessageSender usuarioMessageSender) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMessageSender = usuarioMessageSender;
    }

    public void create(UsuarioDomain usuarioDomain) throws Exception {
        try {
            usuarioRepository.save(UsuarioTransformer.domainToEntity(usuarioDomain));
        } catch (DataAccessException dae) {
            throw dae;
        } catch (Exception e) {
            throw e;
        }
        try{
            usuarioMessageSender.sendSalaMessage(usuarioDomain,1);
        }catch (Exception E){
            throw E;
        }
    }

    public void login(UsuarioDomain usuario) throws Exception {
        if (!PersonaValidator.emailIsValid(usuario.getCorreoElectronico())) {
            throw new RoomuxApiException("Error, el correo electrónico no posee un formato válido");
        }
        if (usuario.getCorreoElectronico().equals(UtilText.getDefaultTextValue())) {
            throw new RoomuxApiException("Error, debe ingresarse el correo electrónico");
        }
        if (usuario.getPassword().equals(UtilText.getDefaultTextValue())) {
            throw new RoomuxApiException("Error, debe ingresarse la contraseña");
        }
        try {
            usuarioRepository
                    .login(usuario.getCorreoElectronico(), usuario.getPassword())
                    .orElseThrow(() -> new RoomuxApiException("Error, credenciales incorrectas"));
        } catch (RoomuxApiException r) {
            throw r;
        } catch (Exception e) {
            throw new Exception("Ocurrió un error inesperado, intente nuevamente o contacte con un administrador");
        }
    }

    public UsuarioDomain getByEmail(String email) throws RoomuxApiException {
        if (!PersonaValidator.emailIsValid(email)) {
            throw new RoomuxApiException("Error, el correo electrónico no posee el formato válido");
        }
        if (email.equals(UtilEmail.getDefaultValueMail())) {
            throw new RoomuxApiException("Error, el correo electrónico no puede ser el valor por defecto");
        }
        if (email.equals(UtilText.getDefaultTextValue())) {
            throw new RoomuxApiException("Error, debe ingresar un correo");
        }
        try {
            UsuarioEntity usuario = usuarioRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new RoomuxApiException("Error, no se encontró un usuario registrado con ese correo electrónico"));

            return UsuarioTransformer.entityToDomain(usuario);
        } catch (RoomuxApiException r) {
            throw r;
        } catch (Exception e) {
            throw new RoomuxApiException("Ocurrió un error inesperado, intente nuevamente");
        }
    }

    public List<UsuarioDomain> getAll() throws RoomuxApiException {
        try {
            return UsuarioTransformer.entityListToDomainList(usuarioRepository.findAll());
        } catch (Exception e) {
            throw new RoomuxApiException("Ocurrió un error inesperado, intente nuevamente o contacte con un administrador");
        }
    }

    protected void delete(String email) throws Exception {
        try {
            int rows = usuarioRepository.deleteByEmail(email);
            if (rows == 0) {
                throw new RoomuxApiException("Error, no se encontró un usuario registrado con ese correo electrónico");
            }
        } catch (RoomuxApiException r) {
            throw r;
        } catch (Exception e) {
            throw new Exception("Ocurrió un error inesperado, intente nuevamente");
        }
        try{
            UsuarioDomain usuario = UsuarioDomain.createWithDefaults();
            usuario.setCorreoElectronico(email);
            usuarioMessageSender.sendSalaMessage(usuario,2);
        }catch (Exception e){
            throw e;
        }
    }
}
