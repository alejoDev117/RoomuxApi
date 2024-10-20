package com.uco.RoomuxApi.RommuxApi.service.transformer;


import com.uco.RoomuxApi.RommuxApi.domain.DetalleReservaDomain;
import com.uco.RoomuxApi.RommuxApi.entity.DetalleReservaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetalleReservaTransformer {

    private DetalleReservaTransformer() {

    }

    public static DetalleReservaEntity domainToEntity(DetalleReservaDomain detalle) {
        return DetalleReservaEntity.builder()
                .identificador(detalle.getIdentificador())
                .diaSemanal(detalle.getDiaSemanal())
                .horaInicio(detalle.getHoraInicio())
                .horaFin(detalle.getHoraFin())
                .build();
    }

    public static DetalleReservaDomain entityToDomain(DetalleReservaEntity detalle) {
        return DetalleReservaDomain.builder()
                .identificador(detalle.getIdentificador())
                .diaSemanal(detalle.getDiaSemanal())
                .horaInicio(detalle.getHoraInicio())
                .horaFin(detalle.getHoraFin())
                .build();
    }

    public static List<DetalleReservaDomain> entityListToDomainList(List<DetalleReservaEntity> detalles) {
        return detalles.stream()
                .map(DetalleReservaTransformer::entityToDomain)
                .collect(Collectors.toList());
    }

    public static List<DetalleReservaEntity> domainListToEntityList(List<DetalleReservaDomain> detalles) {
        return detalles.stream()
                .map(DetalleReservaTransformer::domainToEntity)
                .collect(Collectors.toList());
    }
}
