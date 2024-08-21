package com.uco.RoomuxApi.RommuxApi.repository;

import com.uco.RoomuxApi.RommuxApi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

}
