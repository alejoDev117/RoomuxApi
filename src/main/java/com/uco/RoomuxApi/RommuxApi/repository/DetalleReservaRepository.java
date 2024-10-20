package com.uco.RoomuxApi.RommuxApi.repository;



import com.uco.RoomuxApi.RommuxApi.entity.DetalleReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetalleReservaRepository extends JpaRepository<DetalleReservaEntity, UUID> {

}
