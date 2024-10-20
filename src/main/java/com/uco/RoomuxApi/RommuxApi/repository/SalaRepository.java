package com.uco.RoomuxApi.RommuxApi.repository;

import com.uco.RoomuxApi.RommuxApi.entity.ReservaEntity;
import com.uco.RoomuxApi.RommuxApi.entity.SalaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, UUID> {

    @Query(value = "SELECT * FROM sala WHERE nombre = ?1", nativeQuery = true)
    SalaEntity findByNombre(String nombre);

    @Query(value = "SELECT * FROM reserva WHERE sala= ?1",nativeQuery = true)
    List<ReservaEntity> findReservaListByName(String sala);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sala WHERE nombre = ?1", nativeQuery = true)
    int deleteByNombre(String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sala WHERE identificador = ?1", nativeQuery = true)
    void deleteById(UUID id);
}
