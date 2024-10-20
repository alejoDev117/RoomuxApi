package com.uco.RoomuxApi.RommuxApi.service.transformer;


import com.uco.RoomuxApi.RommuxApi.domain.DetalleReservaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import com.uco.RoomuxApi.RommuxApi.entity.DetalleReservaEntity;
import com.uco.RoomuxApi.RommuxApi.entity.ReservaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservaTransformer {

    private ReservaTransformer() {
    }

    public static ReservaEntity domainToEntity(ReservaDomain reserva) {
        return ReservaEntity.builder()
                .identificador(reserva.getIdentificador())
                .autor(UsuarioTransformer.domainToEntity(reserva.getAutor()))
                .fechaInicio(reserva.getFechaInicio())
                .fechaFin(reserva.getFechaFin())
                .tipoReserva(reserva.getTipoReserva())
                .frecuencia(reserva.getFrecuencia())
                .horaCreacion(reserva.getHoraCreacion())
                .nombreSala(reserva.getNombreSala())
                .detalleReserva(detailDomainListToEntityList(reserva.getDetalleReserva()))
                .build();
    }

    public static ReservaDomain entityToDomain(ReservaEntity reserva) {
        return ReservaDomain.builder()
                .identificador(reserva.getIdentificador())
                .autor(UsuarioTransformer.entityToDomain(reserva.getAutor()))
                .fechaInicio(reserva.getFechaInicio())
                .fechaFin(reserva.getFechaFin())
                .tipoReserva(reserva.getTipoReserva())
                .frecuencia(reserva.getFrecuencia())
                .horaCreacion(reserva.getHoraCreacion())
                .nombreSala(reserva.getNombreSala())
                .detalleReserva(detailEntityListToDomainList(reserva.getDetalleReserva()))
                .build();
    }

    public static List<ReservaDomain> entityListToDomainList(List<ReservaEntity> reservas) {
        return reservas.stream()
                .map(ReservaTransformer::entityToDomain)
                .collect(Collectors.toList());
    }

    public static List<ReservaEntity> domainListToEntityList(List<ReservaDomain> reservas) {
        return reservas.stream()
                .map(ReservaTransformer::domainToEntity)
                .collect(Collectors.toList());
    }

    public static List<DetalleReservaEntity> detailDomainListToEntityList(List<DetalleReservaDomain> detalles) {
        return detalles.stream()
                .map(DetalleReservaTransformer::domainToEntity)
                .collect(Collectors.toList());
    }

    public static List<DetalleReservaDomain> detailEntityListToDomainList(List<DetalleReservaEntity> detalles) {
        return detalles.stream()
                .map(DetalleReservaTransformer::entityToDomain)
                .collect(Collectors.toList());
    }
}
