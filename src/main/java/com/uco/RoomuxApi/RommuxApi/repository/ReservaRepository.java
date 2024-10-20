package com.uco.RoomuxApi.RommuxApi.repository;



import com.uco.RoomuxApi.RommuxApi.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaEntity,UUID> {

    @Query(value = "SELECT * FROM reserva WHERE sala = ?1",nativeQuery = true)
    List<ReservaEntity> findBySala(String sala);

    @Query(value = "SELECT r.* FROM reserva r JOIN usuario u ON r.autor_id = u.identificador WHERE u.correo_electronico = ?1",nativeQuery = true)
    List<ReservaEntity> findByAutor(String email);
}
