package com.uco.RoomuxApi.RommuxApi.repository;

import com.uco.RoomuxApi.RommuxApi.entity.PersonaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, UUID>{

    @Query(value = "SELECT * FROM persona WHERE correo_usuario = ?1",nativeQuery = true)
    Optional<PersonaEntity> findBycorreoElectronico(String correoElectronico);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.persona  SET nombre=?1 WHERE numero_identificacion=?2", nativeQuery = true)
    int updateNombre(String nombre, String numero_identificacion);


    @Modifying
    @Transactional
    @Query(value = "DELETE  FROM public.persona WHERE correo_usuario =?1",nativeQuery = true)
    int DeleteByEmail(String correo_usuario);

}
