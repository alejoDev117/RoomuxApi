package com.uco.RoomuxApi.RommuxApi.repository;

import com.uco.RoomuxApi.RommuxApi.entity.PersonaEntity;
import com.uco.RoomuxApi.RommuxApi.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    @Query(value = "SELECT * FROM usuario WHERE correo_electronico = ?1",nativeQuery = true)
    Optional<UsuarioEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM usuario WHERE (correo_electronico =?1 AND password =?2)",nativeQuery = true)
    Optional<UsuarioEntity> login(String email,String password);

    @Modifying
    @Transactional
    @Query(value = "DELETE  FROM usuario WHERE correo_electronico = ?1",nativeQuery = true)
    int deleteByEmail(String email);

}
