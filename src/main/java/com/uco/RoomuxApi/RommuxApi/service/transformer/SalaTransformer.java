package com.uco.RoomuxApi.RommuxApi.service.transformer;

import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.SalaDomain;
import com.uco.RoomuxApi.RommuxApi.entity.ReservaEntity;
import com.uco.RoomuxApi.RommuxApi.entity.SalaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalaTransformer {

    private SalaTransformer() {

    }

    public static SalaEntity domainToEntity(SalaDomain sala) {
        return SalaEntity.builder()
                .identificador(sala.getIdentificador())
                .nombre(sala.getNombreSala())
                .reservas(domainReservaListToEntityReservaList(sala.getReservas()))
                .build();
    }

    public static SalaDomain entityToDomain(SalaEntity sala) {
        return SalaDomain.builder()
                .identificador(sala.getIdentificador())
                .nombreSala(sala.getNombre())
                .reservas(entityReservaListToDomainReservaList(sala.getReservas()))
                .build();
    }

    public static List<SalaDomain> entityListToDomainList(List<SalaEntity> list) {
        return list.stream()
                .map(SalaTransformer::entityToDomain)
                .collect(Collectors.toList());
    }

    public static List<ReservaEntity> domainReservaListToEntityReservaList(List<ReservaDomain> reservas) {
        return reservas.stream()
                .map(ReservaTransformer::domainToEntity)
                .collect(Collectors.toList());
    }

    public static List<ReservaDomain> entityReservaListToDomainReservaList(List<ReservaEntity> reservas) {
        return reservas.stream()
                .map(ReservaTransformer::entityToDomain)
                .collect(Collectors.toList());
    }
}
